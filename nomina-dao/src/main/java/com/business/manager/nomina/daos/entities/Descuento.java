package com.business.manager.nomina.daos.entities;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.ConceptoDevengado;
import com.business.manager.nomina.api.enums.GrupoDescuento;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = {"concepto"})
@Table(name = "DESCUENTO")
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "id_periodo")
    private Long idPeriodo;

    @NonNull
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @NonNull
    @Enumerated(EnumType.STRING)
    private GrupoDescuento grupo;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ConceptoDescuento concepto;

    @NonNull
    private BigDecimal porcentaje;

    @NonNull
    private BigDecimal baseGravable;

    @NonNull
    private BigDecimal monto;

    @NonNull
    @Setter(AccessLevel.NONE)
    private Short orden;

    public void setConcepto(ConceptoDescuento concepto) {
        this.concepto = concepto;
        this.orden = concepto.getOrden();
    }
}