package com.business.manager.nomina.service.componets.implementations.devengados;

import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.service.componets.DevengadoCalculator;
import com.business.manager.nomina.service.componets.ParametroComponent;
import com.business.manager.nomina.daos.entities.Devengado;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;
import com.business.manager.nomina.daos.repositories.EmpleadoRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public abstract class AbstractDevengadoCalculator implements DevengadoCalculator {

    @Autowired
    private ParametroComponent parametroComponent;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Devengado calcularDevengado(PeriodoPago periodoPago, Empleado empleado) {
        Devengado devengado = new Devengado();
        devengado.setConcepto(getConcepto());
        devengado.setIdEmpleado(empleado.getId());
        devengado.setIdPeriodo(periodoPago.getId());
        devengado.setMonto(getMonto(periodoPago, empleado));
        return devengado;
    }

    protected abstract BigDecimal getMonto(PeriodoPago periodo,Empleado empleado);
    protected abstract ConceptoDevengado getConcepto();

    protected BigDecimal getParametroOf(String parametro) {
        return NumberUtils.createBigDecimal(parametroComponent.getValueOfParametro(parametro));
    }
}
