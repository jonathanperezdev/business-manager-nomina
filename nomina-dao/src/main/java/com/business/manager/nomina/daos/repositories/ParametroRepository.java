package com.business.manager.nomina.daos.repositories;

import com.business.manager.nomina.daos.entities.Parametro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends CrudRepository<Parametro, String> {
}