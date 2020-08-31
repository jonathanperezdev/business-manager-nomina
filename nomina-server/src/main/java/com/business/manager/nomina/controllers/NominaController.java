package com.business.manager.nomina.controllers;

import com.business.manager.nomina.api.models.PeriodoPagoModel;
import com.business.manager.nomina.service.services.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${api.nomina.version}/${api.nomina.path}/periodoPago")
public class NominaController {

    @Autowired
    private NominaService nominaService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    List<PeriodoPagoModel> getPeriodosPago() {
        return nominaService.findAll();
    }

    @PostMapping("/{idPeriodo}/liquidar")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    PeriodoPagoModel createParametro(@PathVariable("idPeriodo") Long idPeriodo) {
        return nominaService.liquidarNomina(idPeriodo);
    }

}
