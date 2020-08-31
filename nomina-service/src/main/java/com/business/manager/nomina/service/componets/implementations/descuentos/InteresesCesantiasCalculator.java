package com.business.manager.nomina.service.componets.implementations.descuentos;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.api.enums.ConceptoRecargo;
import com.business.manager.nomina.daos.entities.Descuento;
import com.business.manager.nomina.daos.entities.Devengado;
import com.business.manager.nomina.daos.repositories.DescuentoRepository;
import com.business.manager.nomina.daos.repositories.RecargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.EnumMap;
import java.util.Optional;

@Component
@Qualifier("InteresesCesantiasCalculator")
public class InteresesCesantiasCalculator extends AbstractDescuentoCalculator{

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private RecargoRepository recargoRepository;

    private static BigDecimal DIAS_AL_ANO = BigDecimal.valueOf(360);

    @Override
    protected BigDecimal calcularBaseGravable(EnumMap<ConceptoDevengado, Devengado> devengados, ConceptoDescuento concepto) {
        Long idEmpleado = devengados.values().stream().findFirst().get().getIdEmpleado();
        Long idPerido = devengados.values().stream().findFirst().get().getIdPeriodo();
        Integer anoAnterior = LocalDate.now().getYear() - 1;

        BigDecimal cesantiasAcumuladas = Optional.ofNullable(
                descuentoRepository.sumDescuentoByConcepto(ConceptoDescuento.CESANTIAS,
                        idEmpleado,
                        idPerido,
                        anoAnterior))
                .orElse(BigDecimal.ZERO);

        BigDecimal diasTrabajados = Optional.ofNullable(
                recargoRepository.countRecargosByConcepto(ConceptoRecargo.DIURNO,
                        idEmpleado,
                        idPerido,
                        anoAnterior))
                .orElse(BigDecimal.ZERO);

        BigDecimal factorDias = BigDecimal.ONE.divide(DIAS_AL_ANO, RoundingMode.HALF_UP);

        return factorDias.multiply(cesantiasAcumuladas).multiply(diasTrabajados);
    }

    @Override
    protected boolean isDescuentoAplicable() {
        LocalDate today = LocalDate.now();
        //If today is between 1 January and 13 February, we should calculate

        LocalDate fechaInicial = LocalDate.of(today.getYear(), Month.JANUARY,1);
        LocalDate fechaFin = LocalDate.of(today.getYear(), Month.FEBRUARY, 13);

        Descuento descuento = null;
        if(today.isAfter(fechaInicial) && today.isBefore(fechaFin)){
            descuento = descuentoRepository.findDescuentoByConceptoBetweenDates(
                    ConceptoDescuento.INTERESES_CESANTIAS,
                    this.empleado.getId(),
                    this.periodoPago.getId(),
                    LocalDate.of(today.getYear()-1, Month.JANUARY, 1),
                    LocalDate.of(today.getYear()-1, Month.DECEMBER, 31));
        }
        return Optional.ofNullable(descuento).isPresent();
    }
}
