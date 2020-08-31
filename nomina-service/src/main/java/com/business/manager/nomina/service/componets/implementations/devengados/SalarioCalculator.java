package com.business.manager.nomina.service.componets.implementations.devengados;

import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;
import com.business.manager.nomina.service.componets.implementations.devengados.AbstractDevengadoCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Qualifier("SalarioCalculator")
public class SalarioCalculator extends AbstractDevengadoCalculator {

    public BigDecimal getMonto(PeriodoPago periodoPago, Empleado empleado) {
        return empleado.getSalario();
    }

    public ConceptoDevengado getConcepto() {
        return ConceptoDevengado.SALARIO_BASICO;
    }
}
