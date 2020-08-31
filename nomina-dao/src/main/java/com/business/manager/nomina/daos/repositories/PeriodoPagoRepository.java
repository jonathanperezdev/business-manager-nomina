package com.business.manager.nomina.daos.repositories;

import com.business.manager.nomina.daos.entities.PeriodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoPagoRepository extends JpaRepository<PeriodoPago, Long> {
    List<PeriodoPago> findAllByOrderByOrdenAscIdAsc();
}
