package com.business.manager.nomina.service.componets.implementations;

import com.business.manager.nomina.service.componets.ParametroComponent;
import com.business.manager.nomina.service.exceptions.NoDataFoundException;
import com.business.manager.nomina.service.exceptions.errors.ErrorEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParametroComponentImpl implements ParametroComponent {
    private Map<String, String> mapParametros = new HashMap<>();

    //TODO:change for call the parametros ms to load real information
    @PostConstruct
    private void cargarParametrosNomina() {
        mapParametros.put("HORAS_TRABAJAR_MES", "240");
        mapParametros.put("RECARGO_NOCTURNO","0.35");
        mapParametros.put("RECARGO_EXTRA_DIURNO", "0.25");
        mapParametros.put("RECARGO_EXTRA_NOCTURNO", "0.75");
        mapParametros.put("RECARGO_DOMINICAL_FESTIVO", "0.75");
        mapParametros.put("SALARIO_MINIMO_LEGAL_VIGENTE_2020","877803");
        mapParametros.put("AUXILIO_TRANSPORTE_2020","102854");
        mapParametros.put("SALUD_EMPLEADO","0.04");
        mapParametros.put("PENSION_EMPLEADO","0.04");
        mapParametros.put("SALUD_EMPLEADOR","0.085");
        mapParametros.put("PENSION_EMPLEADOR","0.12");
        mapParametros.put("PRIMA","0.0833");
        mapParametros.put("CESANTIAS","0.0833");
        mapParametros.put("CAJA_COMPENSACION","0.04");
        mapParametros.put("ICBF","0.03");
        mapParametros.put("SENA","0.02");

        mapParametros.put("RIESGO_LABORAL_NIVEL_1","0.00522");
        mapParametros.put("RIESGO_LABORAL_NIVEL_2","0.01044");
        mapParametros.put("RIESGO_LABORAL_NIVEL_3","0.02436");
        mapParametros.put("RIESGO_LABORAL_NIVEL_4","0.0435");
        mapParametros.put("RIESGO_LABORAL_NIVEL_5","0.0696");
    }

    @Override
    public String getValueOfParametro(String parametro) {
        String valor = mapParametros.get(parametro);
        if(StringUtils.isBlank(valor)) {
            throw new NoDataFoundException(ErrorEnum.PARAMETRO_NOT_FOUND, parametro);
        }

        return valor;
    }
}
