package com.business.manager.nomina.service.services;

import com.business.manager.nomina.api.models.EmpleadoModel;

public interface EmpleadoService {
    void upsertEmpleado(EmpleadoModel empleadoModel);
    void deleteEmpleado(Long idEmpleado);
}
