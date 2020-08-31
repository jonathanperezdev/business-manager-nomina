package com.business.manager.nomina.daos.repositories;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.daos.entities.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    @Query("select d "+
            "from Descuento d, "+
            "PeriodoPago p "+
            "where (d.idPeriodo = p.id) "+
            "and d.idEmpleado = :idEmpleado " +
            "and d.idPeriodo = :idPeriodo " +
            "and d.concepto = :concepto "+
            "and p.fechaInicio between :fechaInicial and :fechaFinal")
    Descuento findDescuentoByConceptoBetweenDates(@Param("concepto") ConceptoDescuento concepto,
                                                  @Param("idEmpleado") Long idEmpleado,
                                                  @Param("idPeriodo") Long idPeriodo,
                                                  @Param("fechaInicial") LocalDate fechaInicial,
                                                  @Param("fechaFinal") LocalDate fechaFinal);

    @Query("select sum(d.monto) " +
            "from Descuento d, " +
            "PeriodoPago p " +
            "where (d.idPeriodo = p.id) " +
            "and d.concepto = :concepto " +
            "and d.idEmpleado = :idEmpleado " +
            "and d.idPeriodo = :idPeriodo " +
            "and extract(year from p.fechaInicio) = :ano")
    BigDecimal sumDescuentoByConcepto(@Param("concepto") ConceptoDescuento concepto,
                                      @Param("idEmpleado") Long idEmpleado,
                                      @Param("idPeriodo") Long idPeriodo,
                                      @Param("ano") Integer ano);
}
