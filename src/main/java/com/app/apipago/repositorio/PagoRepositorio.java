package com.app.apipago.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.apipago.modelo.Pago;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Long>{
	@Query(value = "select max(pagos_id) from pagos", nativeQuery = true)
	Integer verficarPagos();
}
