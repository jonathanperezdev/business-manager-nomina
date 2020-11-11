package com.business.manager.nomina.service.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NominaInputStreams {
    String PARAMETRO_INPUT = "parametros-in";

    @Input(PARAMETRO_INPUT)
    SubscribableChannel inboundParametros();

    String EMPLEADO_INPUT = "empleados-in";
    @Input(EMPLEADO_INPUT)
    SubscribableChannel inboundEmpleados();
}
