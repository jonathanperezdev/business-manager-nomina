package com.business.manager.nomina.service.converters;

import com.business.manager.nomina.api.models.PeriodoPagoModel;
import com.business.manager.nomina.daos.entities.PeriodoPago;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class PeriodoPagoModelConverter implements Converter<PeriodoPago, PeriodoPagoModel> {

    @Override
    public PeriodoPagoModel convert(PeriodoPago entity) {
        PeriodoPagoModel model = new PeriodoPagoModel();

        model.setId(entity.getId());
        model.setFechaInicio(entity.getFechaInicio());
        model.setFechaFin(entity.getFechaFin());
        model.setDiasLiquidados(DAYS.between(model.getFechaInicio(), model.getFechaFin()));
        model.setEstadoPago(entity.getEstadoPago().name());

        return model;
    }
}
