package com.business.manager.nomina.service.configurations;

import com.business.manager.nomina.service.streams.NominaInputStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(NominaInputStreams.class)
public class StreamsConfig {
}
