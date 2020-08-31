package com.business.manager.nomina.api.enums;

public enum EstadoPago {
    PENDIENTE((short)1),
    LIQUIDADO((short)2);

    private Short orden;
    EstadoPago(Short orden){
        this.orden = orden;
    }

    public Short getOrden() {
        return orden;
    }
}
