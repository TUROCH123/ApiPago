package com.app.apipago.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.apipago.modelo.Recibo;

@Repository
public interface ReciboRepositorio extends JpaRepository<Recibo, Long> {
	@Query(value = "select max(recibos_id) from recibos", nativeQuery = true)
	Integer verficarRecibos();
	@Query(value = "select * from recibos p WHERE p.user_id LIKE %:id%", nativeQuery = true)
	public Recibo findByUserId(String id);
}
