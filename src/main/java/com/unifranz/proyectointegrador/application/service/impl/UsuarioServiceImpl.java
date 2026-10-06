package com.unifranz.proyectointegrador.application.service.impl;

import com.unifranz.proyectointegrador.application.dto.UsuarioDto;
import com.unifranz.proyectointegrador.application.service.UsuarioService;
import com.unifranz.proyectointegrador.domain.Usuario;
import com.unifranz.proyectointegrador.infrastructure.persistence.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto guardar(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        // fechaCreacion, creadoPor y eliminado los llena @PrePersist
        Usuario guardado = usuarioRepository.save(usuario);
        return new UsuarioDto(guardado);
    }

    @Override
    public List<UsuarioDto> listar() {
        return usuarioRepository.findByEliminadoFalse()
                .stream()
                .map(UsuarioDto::new)
                .toList();
    }

    @Override
    public UsuarioDto actualizar(Long id, UsuarioDto usuarioDto) {
        Usuario usuario = buscarActivo(id);
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        // fechaModificacion y modificadoPor los llena @PreUpdate
        Usuario actualizado = usuarioRepository.saveAndFlush(usuario);
        return new UsuarioDto(actualizado);
    }

    @Override
    public void eliminarLogico(Long id) {
        Usuario usuario = buscarActivo(id);
        usuario.setEliminado(true);   // no se borra de la BD, solo se marca
        usuarioRepository.save(usuario);
    }

    private Usuario buscarActivo(Long id) {
        return usuarioRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado o ya eliminado"));
    }
}
