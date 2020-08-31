package com.business.manager.nomina.service.componets.implementations.devengados;

import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;
import com.business.manager.nomina.service.componets.implementations.devengados.AbstractDevengadoCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Qualifier("AuxilioTrasporteCalculator")
public class AuxilioTrasporteCalculator extends AbstractDevengadoCalculator {

    @Override
    public BigDecimal getMonto(PeriodoPago periodoPago, Empleado empleado) {
        Integer anoPerido = periodoPago.getFechaFin().getYear();

        BigDecimal salarioMinimoLegalVigente = getParametroOf(String.format("SALARIO_MINIMO_LEGAL_VIGENTE_%s", anoPerido));
        if(empleado.getSalario().compareTo(salarioMinimoLegalVigente.multiply(BigDecimal.valueOf(2L))) <= 0){
            return getParametroOf(String.format("AUXILIO_TRANSPORTE_%s", anoPerido));
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public ConceptoDevengado getConcepto() {
        return ConceptoDevengado.AUXILIO_TRANSPORTE;
    }

}
