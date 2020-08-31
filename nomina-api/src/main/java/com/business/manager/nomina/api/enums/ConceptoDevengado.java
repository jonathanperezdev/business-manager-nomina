package com.business.manager.nomina.api.enums;

import java.util.EnumSet;

public enum ConceptoDevengado {
    SALARIO_BASICO((short)1),
    RECARGOS((short)2),
    AUXILIO_TRANSPORTE((short)3);

    public static final EnumSet<ConceptoDevengado> SALARIO_RECARGOS = EnumSet.of(SALARIO_BASICO, RECARGOS);
    public static final EnumSet<ConceptoDevengado> SALARIO = EnumSet.of(SALARIO_BASICO, RECARGOS,AUXILIO_TRANSPORTE);

    private Short orden;
    ConceptoDevengado(Short orden){
        this.orden = orden;
    }

    public Short getOrden() {
        return orden;
    }
}
