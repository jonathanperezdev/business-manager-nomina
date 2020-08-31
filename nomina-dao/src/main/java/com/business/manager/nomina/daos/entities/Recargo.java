package com.business.manager.nomina.daos.entities;

import com.business.manager.nomina.api.enums.ConceptoRecargo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = {"concepto"})
@Table(name = "RECARGO")
public class Recargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Double horas;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ConceptoRecargo concepto;

    @NonNull
    @Setter(AccessLevel.NONE)
    private Short orden;

    @NonNull
    private Long idPeriodo;

    @NonNull
    private Long idEmpleado;

    public void setConcepto(ConceptoRecargo concepto) {
        this.concepto = concepto;
        this.orden = concepto.getOrden();
    }

}