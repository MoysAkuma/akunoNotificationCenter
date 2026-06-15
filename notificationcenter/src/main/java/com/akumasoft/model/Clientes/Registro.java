package com.akumasoft.model.Clientes;
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
@Table(name = "registros")
@Data
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(name = "cliente_id", nullable = false)
    private UUID clienteID;

    @Column(name = "apikey", nullable = false, unique = true)
    private String apiKey;

    @Column(name = "correo", nullable = false)
    private String correo;
    
    @Column(name = "telefono", nullable = false)
    private String telefono;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
}
