package com.business.manager.nomina.service.services.implementations;

import com.business.manager.nomina.api.models.EmpleadoModel;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.repositories.EmpleadoRepository;
import com.business.manager.nomina.service.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    @Qualifier("customConversionService")
    private ConversionService conversionService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public void upsertEmpleado(EmpleadoModel empleadoModel){
        Empleado empleado = conversionService.convert(empleadoModel, Empleado.class);
        empleadoRepository.save(empleado);
    }

    @Override
    public void deleteEmpleado(Long idEmpleado){
        empleadoRepository.findById(idEmpleado).ifPresent(empleadoRepository::delete);
    }
}
