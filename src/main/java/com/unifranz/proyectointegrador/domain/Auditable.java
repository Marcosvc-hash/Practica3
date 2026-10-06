package com.unifranz.proyectointegrador.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Clase padre de auditoria.
 * Toda entidad que haga "extends Auditable" hereda estos 5 campos
 * y se llenan solos al guardar o actualizar.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Auditable {

    // time Create: cuando se creo el registro (no se vuelve a cambiar)
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    // time Update: cuando se modifico por ultima vez
    private LocalDateTime fechaModificacion;

    // User Create: quien lo creo (no se vuelve a cambiar)
    @Column(updatable = false)
    private String creadoPor;

    // User Update: quien lo modifico por ultima vez
    private String modificadoPor;

    // deleted: eliminado logico (true = borrado, false = activo)
    @Column(nullable = false)
    private Boolean eliminado = false;

    // Se ejecuta automaticamente ANTES del INSERT
    @PrePersist
    public void antesDeCrear() {
        this.fechaCreacion = LocalDateTime.now();
        this.creadoPor = "SISTEMA";
        this.eliminado = false;
    }

    // Se ejecuta automaticamente ANTES de cada UPDATE
    @PreUpdate
    public void antesDeActualizar() {
        this.fechaModificacion = LocalDateTime.now();
        this.modificadoPor = "SISTEMA";
    }
}
