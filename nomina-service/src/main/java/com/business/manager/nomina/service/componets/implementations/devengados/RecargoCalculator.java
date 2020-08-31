package com.business.manager.nomina.service.componets.implementations.devengados;

import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.api.enums.ConceptoRecargo;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;
import com.business.manager.nomina.daos.entities.Recargo;
import com.business.manager.nomina.daos.repositories.RecargoRepository;
import com.business.manager.nomina.service.componets.implementations.devengados.AbstractDevengadoCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Qualifier("RecargoCalculator")
public class RecargoCalculator extends AbstractDevengadoCalculator {

    @Autowired
    private RecargoRepository recargoRepository;

    @Override
    public BigDecimal getMonto(PeriodoPago periodoPago, Empleado empleado) {
        BigDecimal valorOrdinarioHora = empleado.getSalario().divide(getParametroOf("HORAS_TRABAJAR_MES"), RoundingMode.HALF_UP);

        List<Recargo> recargos = recargoRepository.findByIdPeriodoAndIdEmpleadoAndConceptoNot(periodoPago.getId(),
                empleado.getId(),
                ConceptoRecargo.DIURNO);

        return recargos.stream().map(recargo -> calcularMontoRecargo(recargo, valorOrdinarioHora)).reduce((m1, m2) -> m1.add(m2)).get();
    }

    private BigDecimal calcularMontoRecargo(Recargo recargo, BigDecimal valorOrdinarioHora){

        BigDecimal factor = recargo.getConcepto()
                .getFactoresRecargo()
                .stream()
                .map(this::getParametroOf)
                .reduce((f1, f2) -> f1.add(f2))
                .get();

        factor = factor.add(BigDecimal.ONE);

        return valorOrdinarioHora.multiply(factor).multiply(BigDecimal.valueOf(recargo.getHoras()));
    }

    @Override
    public ConceptoDevengado getConcepto() {
        return ConceptoDevengado.RECARGOS;
    }
}
