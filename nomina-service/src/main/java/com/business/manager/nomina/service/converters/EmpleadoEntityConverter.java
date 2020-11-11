package com.business.manager.nomina.service.converters;

import com.business.manager.nomina.api.models.EmpleadoModel;
import com.business.manager.nomina.daos.entities.Empleado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoEntityConverter implements Converter<EmpleadoModel, Empleado> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Empleado convert(EmpleadoModel empleadoModel) {
        return modelMapper.map(empleadoModel, Empleado.class);
    }
}
