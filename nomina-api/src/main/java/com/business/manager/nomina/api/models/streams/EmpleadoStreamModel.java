package com.business.manager.nomina.api.models.streams;

import com.business.manager.nomina.api.enums.StreamAction;
import com.business.manager.nomina.api.models.EmpleadoModel;
import lombok.Data;

@Data
public class EmpleadoStreamModel {
    StreamAction action;
    EmpleadoModel empleado;
}
