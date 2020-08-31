package com.business.manager.nomina.service.configurations;

import com.business.manager.nomina.service.converters.PeriodoPagoModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfig {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PeriodoPagoModelConverter periodoPagoModelConverter;

    @Bean
    @Qualifier("customConversionService")
    public ConversionService customConversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();

        converters.add(periodoPagoModelConverter);

        factory.setConverters(converters);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
