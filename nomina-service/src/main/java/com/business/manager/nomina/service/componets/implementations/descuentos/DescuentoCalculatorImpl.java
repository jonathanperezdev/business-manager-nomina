package com.business.manager.nomina.service.componets.implementations.descuentos;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.daos.entities.Devengado;
import com.business.manager.nomina.service.componets.implementations.descuentos.AbstractDescuentoCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.EnumMap;

@Component
@Qualifier("DescuentoCalculator")
public class DescuentoCalculatorImpl extends AbstractDescuentoCalculator {

    protected BigDecimal calcularBaseGravable(EnumMap<ConceptoDevengado, Devengado> devengados, ConceptoDescuento concepto) {
        return concepto.getBaseGravableConceptos().stream()
                .map(conDevengado -> devengados.get(conDevengado))
                .map(Devengado::getMonto)
                .reduce((m1, m2) -> m1.add(m2))
                .get();
    }

    @Override
    protected boolean isDescuentoAplicable() {
        return true;
    }
}
