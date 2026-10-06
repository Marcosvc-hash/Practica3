package com.unifranz.proyectointegrador.application.dto;

import com.unifranz.proyectointegrador.domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String email;

    // Campos de auditoria (solo para mostrarlos en la respuesta)
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String creadoPor;
    private String modificadoPor;
    private Boolean eliminado;

    // Convierte la entidad Usuario a DTO
    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.fechaCreacion = usuario.getFechaCreacion();
        this.fechaModificacion = usuario.getFechaModificacion();
        this.creadoPor = usuario.getCreadoPor();
        this.modificadoPor = usuario.getModificadoPor();
        this.eliminado = usuario.getEliminado();
    }
}
