package com.business.manager.nomina.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EmpleadoModel {
    @NonNull
    private Long id;

    @NonNull
    private String nombres;

    @NonNull
    private String apellidos;

    @NonNull
    private BigDecimal salario;

    @NonNull
    private String riesgoLaboral;
}
