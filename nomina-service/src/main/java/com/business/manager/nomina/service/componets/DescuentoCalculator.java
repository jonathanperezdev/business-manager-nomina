package com.business.manager.nomina.service.componets;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.daos.entities.Descuento;
import com.business.manager.nomina.daos.entities.Devengado;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;

import java.util.EnumMap;
import java.util.Optional;

public interface DescuentoCalculator {
    Optional<Descuento> calcularDescuesto(EnumMap<ConceptoDevengado, Devengado> devengados, ConceptoDescuento concepto);

    void setEmpleado(Empleado empleado);
    void setPeriodoPago(PeriodoPago periodoPago);
}
