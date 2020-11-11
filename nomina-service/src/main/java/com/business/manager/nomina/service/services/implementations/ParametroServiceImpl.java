package com.business.manager.nomina.service.services.implementations;

import com.business.manager.nomina.api.models.ParametroModel;
import com.business.manager.nomina.daos.entities.Parametro;
import com.business.manager.nomina.daos.repositories.ParametroRepository;
import com.business.manager.nomina.service.exceptions.NoDataFoundException;
import com.business.manager.nomina.service.exceptions.errors.ErrorEnum;
import com.business.manager.nomina.service.services.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    @Qualifier("customConversionService")
    private ConversionService conversionService;

    @Override
    public Parametro createParametro(ParametroModel parametro){
        return parametroRepository.save(conversionService.convert(parametro, Parametro.class));
    }

    @Override
    public void deleteParametro(String nombreParametro){
        parametroRepository.deleteById(nombreParametro);
    }

    @Override
    public String getValueOfParametro(String nombreParametro) {
        Parametro parametro = parametroRepository.findById(nombreParametro)
                .orElseThrow(() -> new NoDataFoundException(ErrorEnum.PARAMETRO_NOT_FOUND, nombreParametro));
        return parametro.getValor();
    }

}
