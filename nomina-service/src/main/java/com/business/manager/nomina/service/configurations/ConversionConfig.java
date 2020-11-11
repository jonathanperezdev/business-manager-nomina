package com.business.manager.nomina.service.configurations;

import com.business.manager.nomina.api.models.ParametroModel;
import com.business.manager.nomina.daos.entities.Parametro;
import com.business.manager.nomina.service.converters.EmpleadoEntityConverter;
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

    @Autowired
    private EmpleadoEntityConverter empleadoEntityConverter;

    private Converter<ParametroModel, Parametro> parametroEntityConverter = new Converter<ParametroModel, Parametro>() {

        @Override
        public Parametro convert(ParametroModel parametroModel) {
            return modelMapper.map(parametroModel, Parametro.class);
        }
    };

    @Bean
    @Qualifier("customConversionService")
    public ConversionService customConversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();

        converters.add(periodoPagoModelConverter);
        converters.add(parametroEntityConverter);

        converters.add(empleadoEntityConverter);

        factory.setConverters(converters);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
