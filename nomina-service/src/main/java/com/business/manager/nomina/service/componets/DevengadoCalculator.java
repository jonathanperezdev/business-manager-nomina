package com.business.manager.nomina.service.componets;

import com.business.manager.nomina.daos.entities.Devengado;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;

public interface DevengadoCalculator {
    Devengado calcularDevengado(PeriodoPago periodo, Empleado empleado);
}
