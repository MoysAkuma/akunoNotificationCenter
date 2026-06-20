package com.akumasoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akumasoft.model.Emails.Programados;

public interface ProgramadosRepository extends JpaRepository<Programados, Long> {
    void createProgramados(Programados programados);

}
