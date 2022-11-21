package com.springboot.crud.repository;

import com.springboot.crud.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("SELECT obj FROM Usuario obj ORDER BY obj.id ASC")
	Page<Usuario> listar(Pageable pageable);
}
