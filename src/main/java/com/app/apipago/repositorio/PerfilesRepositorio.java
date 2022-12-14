package com.app.apipago.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.apipago.modelo.Perfiles;


@Repository
public interface PerfilesRepositorio extends JpaRepository<Perfiles, Integer> {

}