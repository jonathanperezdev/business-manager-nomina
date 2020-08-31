package com.business.manager.nomina.api.enums;

import java.util.EnumSet;
import static com.business.manager.nomina.api.enums.ConceptoDevengado.*;

public enum ConceptoDescuento {
    SALUD_EMPLEADO((short)1, SALARIO_RECARGOS),
    PENSION_EMPLEADO((short)2, SALARIO_RECARGOS),
    SALUD_EMPLEADOR((short)3, SALARIO_RECARGOS),
    PENSION_EMPLEADOR((short)4 , SALARIO_RECARGOS),
    RIESGO_LABORAL_NIVEL_1((short)5, SALARIO_RECARGOS),
    RIESGO_LABORAL_NIVEL_2((short)6, SALARIO_RECARGOS),
    RIESGO_LABORAL_NIVEL_3((short)7, SALARIO_RECARGOS),
    RIESGO_LABORAL_NIVEL_4((short)8, SALARIO_RECARGOS),
    RIESGO_LABORAL_NIVEL_5((short)9, SALARIO_RECARGOS),
    PRIMA((short)10 , SALARIO),
    CESANTIAS((short)11 , SALARIO),
    INTERESES_CESANTIAS((short)12, null),
    CAJA_COMPENSACION((short)13, SALARIO_RECARGOS),
    ICBF((short)14, SALARIO_RECARGOS),
    SENA((short)15, SALARIO_RECARGOS);

    private short orden;
    private EnumSet<ConceptoDevengado> baseGravableConceptos;
    ConceptoDescuento(short orden, EnumSet<ConceptoDevengado> baseGravableConceptos){
        this.orden = orden;
        this.baseGravableConceptos = baseGravableConceptos;
    }

    public short getOrden() {
        return orden;
    }

    public EnumSet<ConceptoDevengado> getBaseGravableConceptos() {
        return baseGravableConceptos;
    }
}
