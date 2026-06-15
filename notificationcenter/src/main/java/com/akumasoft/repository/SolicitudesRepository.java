package com.akumasoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akumasoft.model.Emails.Solicitudes;

public interface SolicitudesRepository extends JpaRepository<Solicitudes, Long> { 
    void createSolicitud(Solicitudes solicitud);

    void setEstadoSolicitud(Long id, String estado);
}
