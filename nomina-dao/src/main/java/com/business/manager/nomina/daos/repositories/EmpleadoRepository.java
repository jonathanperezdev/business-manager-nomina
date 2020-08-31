package com.business.manager.nomina.daos.repositories;

import com.business.manager.nomina.daos.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    @Query("select distinct e " +
            "from Empleado e, " +
            "Recargo r " +
            "where e.id = r.idEmpleado " +
            "and r.idPeriodo = :idPeriodo")
    List<Empleado> findEmpleadoWithRecargosByIdPeriodo(@Param("idPeriodo") Long idPeriodo);
}
