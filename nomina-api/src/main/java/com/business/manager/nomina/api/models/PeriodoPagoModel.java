package com.business.manager.nomina.api.models;

import com.business.manager.nomina.api.serializers.TemporalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.temporal.Temporal;

@Data
@NoArgsConstructor
public class PeriodoPagoModel {
    private Long id;

    @NonNull
    @JsonSerialize(using = TemporalSerializer.class)
    private Temporal fechaInicio;

    @NonNull
    @JsonSerialize(using = TemporalSerializer.class)
    private Temporal fechaFin;

    private String estadoPago;
    private Long diasLiquidados;
}
