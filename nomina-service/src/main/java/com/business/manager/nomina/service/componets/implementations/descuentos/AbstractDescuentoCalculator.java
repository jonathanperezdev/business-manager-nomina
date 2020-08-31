package com.business.manager.nomina.service.componets.implementations.descuentos;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.api.enums.GrupoDescuento;
import com.business.manager.nomina.daos.entities.Descuento;
import com.business.manager.nomina.daos.entities.Devengado;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;
import com.business.manager.nomina.service.componets.DescuentoCalculator;
import com.business.manager.nomina.service.componets.ParametroComponent;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Optional;

public abstract class AbstractDescuentoCalculator implements DescuentoCalculator {

    private ParametroComponent parametroComponent;

    protected Empleado empleado;
    protected PeriodoPago periodoPago;

    @Override
    public Optional<Descuento> calcularDescuesto(EnumMap<ConceptoDevengado, Devengado> devengados, ConceptoDescuento concepto){

        Descuento descuento = null;

        if(isDescuentoAplicable()) {
            descuento =new Descuento();
            descuento.setIdEmpleado(this.empleado.getId());
            descuento.setIdPeriodo(this.periodoPago.getId());
            descuento.setGrupo(GrupoDescuento.getGrupoOf(concepto));
            descuento.setConcepto(concepto);
            descuento.setPorcentaje(getProcentaje(concepto));
            descuento.setBaseGravable(calcularBaseGravable(devengados, concepto));
            descuento.setMonto(descuento.getBaseGravable().multiply(descuento.getPorcentaje()));
        }

        return Optional.ofNullable(descuento);
    }

    private BigDecimal getProcentaje(ConceptoDescuento concepto){
        return NumberUtils.createBigDecimal(parametroComponent.getValueOfParametro(concepto.name()));
    }

    @Override
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public void setPeriodoPago(PeriodoPago periodoPago) {
        this.periodoPago = periodoPago;
    }

    protected abstract boolean isDescuentoAplicable();

    protected abstract BigDecimal calcularBaseGravable(EnumMap<ConceptoDevengado, Devengado> devengados, ConceptoDescuento concepto);

    @Autowired
    public final void setParametroComponent(ParametroComponent parametroComponent) {
        this.parametroComponent = parametroComponent;
    }
}
