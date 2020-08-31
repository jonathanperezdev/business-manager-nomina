package com.business.manager.nomina.daos.repositories;

import com.business.manager.nomina.api.enums.ConceptoDescuento;
import com.business.manager.nomina.api.enums.ConceptoRecargo;
import com.business.manager.nomina.daos.entities.Recargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RecargoRepository extends JpaRepository<Recargo, Long> {
    List<Recargo> findByIdPeriodoAndIdEmpleadoAndConceptoNot(Long idPeriodo, Long idEmpleado, ConceptoRecargo concepto);

    @Query("select count(*) " +
            "from Recargo r, " +
            "PeriodoPago p " +
            "where (r.idPeriodo = p.id) " +
            "and r.concepto = :concepto " +
            "and r.idEmpleado = :idEmpleado " +
            "and r.idPeriodo = :idPeriodo " +
            "and extract(year from p.fechaInicio) = :ano ")
    BigDecimal countRecargosByConcepto(@Param("concepto") ConceptoRecargo concepto,
                                      @Param("idEmpleado") Long idEmpleado,
                                      @Param("idPeriodo") Long idPeriodo,
                                      @Param("ano") Integer ano);
}
