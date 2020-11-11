package com.business.manager.nomina.service.services;

import com.business.manager.nomina.api.models.ParametroModel;
import com.business.manager.nomina.daos.entities.Parametro;

public interface ParametroService {

    Parametro createParametro(ParametroModel parametro);

    void deleteParametro(String nombreParametro);

    String getValueOfParametro(String nombreParametro);
}
