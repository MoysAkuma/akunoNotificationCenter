package com.akumasoft.model.Emails;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "emails_bloqueados")
@Data
public class EmailsBloqueados {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String email;
    private LocalDateTime fechaCreado;
    private boolean activo;
}
