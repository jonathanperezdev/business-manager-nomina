package com.business.manager.nomina.service.services;

import com.business.manager.nomina.api.models.PeriodoPagoModel;

import java.util.List;

public interface NominaService {
   List<PeriodoPagoModel> findAll();
   PeriodoPagoModel liquidarNomina(Long idPeriodoPago);
}
