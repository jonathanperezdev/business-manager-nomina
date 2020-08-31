package com.business.manager.nomina.daos.entities;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.EstadoPago;
import lombok.AccessLevel;
import lombok.Data;
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
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PERIODO_PAGO")
public class PeriodoPago {
    @Id
    private Long id;

    @NonNull
    private LocalDate fechaInicio;

    @NonNull
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago = EstadoPago.PENDIENTE;

    @NonNull
    @Setter(AccessLevel.NONE)
    private Short orden;

    public void setConcepto(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
        this.orden = estadoPago.getOrden();
    }
}

