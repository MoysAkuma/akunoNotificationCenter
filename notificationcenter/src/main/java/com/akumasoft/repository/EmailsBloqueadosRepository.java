package com.akumasoft.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.akumasoft.model.Emails.EmailsBloqueados;

public interface EmailsBloqueadosRepository extends JpaRepository<EmailsBloqueados, Long> {
    boolean existsByEmail(String email);
    List<EmailsBloqueados> findAll();
}
