package com.business.manager.nomina.service.streams;

import com.business.manager.nomina.api.enums.StreamAction;
import com.business.manager.nomina.api.models.streams.EmpleadoStreamModel;
import com.business.manager.nomina.api.models.streams.ParametroStreamModel;
import com.business.manager.nomina.service.services.EmpleadoService;
import com.business.manager.nomina.service.services.ParametroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

@Component
public class NominaInputStreamProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(NominaInputStreamProcessor.class);

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private EmpleadoService empleadoService;

    @StreamListener(NominaInputStreams.PARAMETRO_INPUT)
    public void handleParametroStream(@Payload ParametroStreamModel parametroStream) {
        LOGGER.info("Received parametro: {}", parametroStream);
        if("NOMINA".equals(parametroStream.getParametro().getComponente())){
            switch (StreamAction.valueOf(parametroStream.getAction())){
                case UPSERT:
                    parametroService.createParametro(parametroStream.getParametro());
                    break;
                case DELETE:
                    parametroService.deleteParametro(parametroStream.getParametro().getNombre());
                    break;
            }
        }
    }

    @StreamListener(NominaInputStreams.EMPLEADO_INPUT)
    public void handleEmpleadoStream(@Payload EmpleadoStreamModel empleadoStream) {
        LOGGER.info("Received empleado stream: {}", empleadoStream);
        switch (empleadoStream.getAction()){
            case UPSERT:
                empleadoService.upsertEmpleado(empleadoStream.getEmpleado());
                break;
            case DELETE:
                empleadoService.deleteEmpleado(empleadoStream.getEmpleado().getId());
                break;
        }
    }
}
