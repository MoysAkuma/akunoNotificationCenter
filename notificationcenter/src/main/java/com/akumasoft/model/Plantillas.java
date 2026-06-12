package com.akumasoft.model;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "plantillas")
@Data
public class Plantillas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private UUID template_id;
    private String asunto;
    private String contentHTML;
}
