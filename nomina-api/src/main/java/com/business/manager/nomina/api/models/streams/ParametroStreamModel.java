package com.business.manager.nomina.api.models.streams;

import com.business.manager.nomina.api.models.ParametroModel;
import lombok.Data;

@Data
public class ParametroStreamModel {
    String action;
    ParametroModel parametro;
}
