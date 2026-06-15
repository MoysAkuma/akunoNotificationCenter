package com.akumasoft.model.Emails;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "plantillas_valores")
@Data
public class Valores {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "plantilla_id", nullable = false)
    private UUID plantillaId;

    @Column(name = "clave", nullable = false, length = 20)
    private String clave;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "es_obligatorio", nullable = false)
    private boolean esObligatorio;
    
    @Column(name = "fecha_creado", nullable = false, updatable = false)
    private LocalDateTime fechaCreado;
}
