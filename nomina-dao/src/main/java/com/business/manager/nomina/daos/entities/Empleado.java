package com.business.manager.nomina.daos.entities;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.GrupoDescuento;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Optional;


@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPLEADO")
public class Empleado {
    @Id
    private Long id;

    @NonNull
    private String nombres;
    
    @NonNull
    private String apellidos;

    @NonNull
    private BigDecimal salario;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ConceptoDescuento riesgoLaboral;

    public void setRiesgoLaboral(ConceptoDescuento riesgoLaboral) {

        if(!GrupoDescuento.getRiesgosLaborales().contains(riesgoLaboral)) {
            throw new IllegalArgumentException("Solo concepto de tipo riesgo laboral es permitido ");
        }

        this.riesgoLaboral = riesgoLaboral;
    }
}

