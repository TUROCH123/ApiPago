package com.app.apipago.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.apipago.modelo.Planes;

@Repository
public interface PlanesRepositorio extends JpaRepository<Planes, Integer>{
	@Query(value = "select max(planes_id) from planes", nativeQuery = true)
	Integer verficarPlanes();
}
