package com.business.manager.nomina.api.enums;

import java.util.Arrays;
import java.util.List;

public enum ConceptoRecargo {
    DIURNO((short)1),
    NOCTURNO((short)2, "RECARGO_NOCTURNO"),
    EXTRA_DIURNO((short)3, "RECARGO_EXTRA_DIURNO"),
    EXTRA_NOCTURNO((short)4, "RECARGO_EXTRA_NOCTURNO"),
    FESTIVO_DIURNO((short)5, "RECARGO_DOMINICAL_FESTIVO"),
    FESTIVO_NOCTURNO((short)6,"RECARGO_DOMINICAL_FESTIVO"),
    FESTIVO_EXTRA_DIURNO((short)7, "RECARGO_DOMINICAL_FESTIVO","RECARGO_EXTRA_DIURNO"),
    FESTIVO_EXTRA_NOCTURNO((short)8, "RECARGO_DOMINICAL_FESTIVO", "RECARGO_EXTRA_NOCTURNO");

    private Short orden;
    private List<String> factoresRecargo;
    ConceptoRecargo(Short orden, String ... factoresRecargo){
        this.orden = orden;
        this.factoresRecargo = Arrays.asList(factoresRecargo);
    }

    public Short getOrden() {
        return orden;
    }

    public List<String> getFactoresRecargo() {
        return factoresRecargo;
    }
}
