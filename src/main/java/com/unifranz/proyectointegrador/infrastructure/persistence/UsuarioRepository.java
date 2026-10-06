package com.unifranz.proyectointegrador.infrastructure.persistence;

import com.unifranz.proyectointegrador.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Solo los que NO estan eliminados
    List<Usuario> findByEliminadoFalse();
    Optional<Usuario> findByIdAndEliminadoFalse(Long id);
}
