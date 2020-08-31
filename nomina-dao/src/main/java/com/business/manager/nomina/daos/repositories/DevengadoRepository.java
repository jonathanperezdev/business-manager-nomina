package com.business.manager.nomina.daos.repositories;

import com.business.manager.nomina.daos.entities.Devengado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevengadoRepository extends JpaRepository<Devengado, Long> {
}
