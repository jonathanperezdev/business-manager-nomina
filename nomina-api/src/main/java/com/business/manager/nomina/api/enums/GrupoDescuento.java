package com.business.manager.nomina.api.enums;

import java.util.Arrays;
import java.util.EnumSet;

import static com.business.manager.nomina.api.enums.ConceptoDescuento.*;

public enum GrupoDescuento {
    DEDUCCION(SALUD_EMPLEADO, PENSION_EMPLEADO),
    SEGURIDAD_SOCIAL(SALUD_EMPLEADOR, PENSION_EMPLEADOR, RIESGO_LABORAL_NIVEL_1, RIESGO_LABORAL_NIVEL_2, RIESGO_LABORAL_NIVEL_3, RIESGO_LABORAL_NIVEL_4, RIESGO_LABORAL_NIVEL_5),
    PRETANCIONES_SOCIALES(PRIMA, CESANTIAS, INTERESES_CESANTIAS),
    PARAFISCALES(CAJA_COMPENSACION, ICBF, SENA);

    private EnumSet<ConceptoDescuento> conceptoDescuentos = EnumSet.noneOf(ConceptoDescuento.class);
    GrupoDescuento(ConceptoDescuento ... conceptoDescuentos) {
        this.conceptoDescuentos.addAll(Arrays.asList(conceptoDescuentos));
    }

    public static GrupoDescuento getGrupoOf(ConceptoDescuento descuento){
        return Arrays.stream(values()).filter(grupo -> grupo.conceptoDescuentos.contains(descuento)).findFirst().get();
    }

    public static EnumSet getRiesgosLaborales() {
        return EnumSet.of(
                ConceptoDescuento.RIESGO_LABORAL_NIVEL_1,
                ConceptoDescuento.RIESGO_LABORAL_NIVEL_2,
                ConceptoDescuento.RIESGO_LABORAL_NIVEL_3,
                ConceptoDescuento.RIESGO_LABORAL_NIVEL_4,
                ConceptoDescuento.RIESGO_LABORAL_NIVEL_5);
    }


}
