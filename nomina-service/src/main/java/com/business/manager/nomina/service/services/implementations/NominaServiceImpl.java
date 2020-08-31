package com.business.manager.nomina.service.services.implementations;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.api.enums.EstadoPago;
import com.business.manager.nomina.api.enums.GrupoDescuento;
import com.business.manager.nomina.api.models.PeriodoPagoModel;
import com.business.manager.nomina.daos.entities.Devengado;
import com.business.manager.nomina.daos.repositories.DescuentoRepository;
import com.business.manager.nomina.service.componets.DescuentoCalculator;
import com.business.manager.nomina.service.componets.DevengadoCalculator;
import com.business.manager.nomina.daos.entities.Empleado;
import com.business.manager.nomina.daos.entities.PeriodoPago;
import com.business.manager.nomina.daos.repositories.DevengadoRepository;
import com.business.manager.nomina.daos.repositories.EmpleadoRepository;
import com.business.manager.nomina.daos.repositories.PeriodoPagoRepository;
import com.business.manager.nomina.service.exceptions.NoDataFoundException;
import com.business.manager.nomina.service.exceptions.OperationNotPossibleException;
import com.business.manager.nomina.service.exceptions.errors.ErrorEnum;
import com.business.manager.nomina.service.services.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NominaServiceImpl implements NominaService {

    @Autowired
    private PeriodoPagoRepository periodoPagoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    @Qualifier("SalarioCalculator")
    private DevengadoCalculator salarioCalculator;

    @Autowired
    @Qualifier("AuxilioTrasporteCalculator")
    private DevengadoCalculator auxilioTrasporteCalculator;

    @Autowired
    @Qualifier("RecargoCalculator")
    private DevengadoCalculator recargoCalculator;

    @Autowired
    @Qualifier("DescuentoCalculator")
    DescuentoCalculator descuentoCalculator;

    @Autowired
    @Qualifier("InteresesCesantiasCalculator")
    DescuentoCalculator interesesCesantiasCalculator;

    @Autowired
    private DevengadoRepository devengadoRepository;

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    @Qualifier("customConversionService")
    private ConversionService conversionService;

    private List<DevengadoCalculator> conceptosLiquidar;

    @Override
    public List<PeriodoPagoModel> findAll(){
        List<PeriodoPago> periodosPago = periodoPagoRepository.findAllByOrderByOrdenAscIdAsc();

        if(CollectionUtils.isEmpty(periodosPago)){
            throw new NoDataFoundException(ErrorEnum.PERIODOS_PAGO_NOT_FOUND);
        }
        return periodosPago.stream().map(periodo -> conversionService.convert(periodo, PeriodoPagoModel.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PeriodoPagoModel liquidarNomina(Long idPeriodoPago){
        PeriodoPago periodoPago = periodoPagoRepository.findById(idPeriodoPago).get();

        if(EstadoPago.PENDIENTE != periodoPago.getEstadoPago()){
            throw new OperationNotPossibleException(ErrorEnum.PERIODO_PAGO_IS_NOT_PENDING, periodoPago.getId());
        }

        List<Empleado> empleados = empleadoRepository.findEmpleadoWithRecargosByIdPeriodo(idPeriodoPago);

        Devengado devengado;
        EnumMap<ConceptoDevengado, Devengado> devengados = new EnumMap<>(ConceptoDevengado.class);
        for (Empleado empleado:empleados) {
            for (ConceptoDevengado conceptoDevengado : ConceptoDevengado.values()) {
                devengado = getDevengadoCalculatorOf(conceptoDevengado).calcularDevengado(periodoPago, empleado);
                devengados.put(conceptoDevengado, devengado);
                devengadoRepository.save(devengado);
            }

            for (ConceptoDescuento conceptoDescuento : ConceptoDescuento.values()) {
                getDescuentoCalculatorOf(conceptoDescuento, empleado, periodoPago)
                        .ifPresent(calculator -> calculator.calcularDescuesto(devengados, conceptoDescuento)
                        .ifPresent(descuento -> descuentoRepository.save(descuento)));
            }

            devengados.clear();
        }

        periodoPago.setEstadoPago(EstadoPago.LIQUIDADO);
        periodoPago = periodoPagoRepository.save(periodoPago);

        return conversionService.convert(periodoPago, PeriodoPagoModel.class);
    }

    private DevengadoCalculator getDevengadoCalculatorOf(ConceptoDevengado concepto){
        DevengadoCalculator calculator;
        switch(concepto) {
            case SALARIO_BASICO:
                calculator = this.salarioCalculator;
                break;
            case RECARGOS:
                calculator = this.recargoCalculator;
                break;
            default:
                calculator = this.auxilioTrasporteCalculator;
                break;
        }

        return calculator;
    }

    private Optional<DescuentoCalculator> getDescuentoCalculatorOf(ConceptoDescuento concepto,
                                                         Empleado empleado,
                                                         PeriodoPago periodoPago){
        DescuentoCalculator calculator;
        if(GrupoDescuento.getRiesgosLaborales().contains(concepto)){
            calculator = (concepto == empleado.getRiesgoLaboral()) ? this.descuentoCalculator : null;
        }else if(concepto == ConceptoDescuento.INTERESES_CESANTIAS) {
            calculator = this.interesesCesantiasCalculator;
        }else {
            calculator = this.descuentoCalculator;
        }

        if(Objects.nonNull(calculator)) {
            calculator.setEmpleado(empleado);
            calculator.setPeriodoPago(periodoPago);
        }

        return Optional.ofNullable(calculator);
    }
}
