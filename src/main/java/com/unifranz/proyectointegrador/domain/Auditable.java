package com.unifranz.proyectointegrador.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Auditable {


    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;


    @Column(updatable = false)
    private String creadoPor;


    private String modificadoPor;


    @Column(nullable = false)
    private Boolean eliminado = false;

    @PrePersist
    public void antesDeCrear() {
        this.fechaCreacion = LocalDateTime.now();
        this.creadoPor = "SISTEMA";
        this.eliminado = false;
    }

    @PreUpdate
    public void antesDeActualizar() {
        this.fechaModificacion = LocalDateTime.now();
        this.modificadoPor = "SISTEMA";
    }
}
