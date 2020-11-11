package com.business.manager.nomina.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ParametroModel {
    @NonNull
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String valor;

    @NonNull
    private String componente;
}
