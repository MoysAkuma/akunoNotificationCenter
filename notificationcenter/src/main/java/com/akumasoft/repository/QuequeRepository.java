package com.akumasoft.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akumasoft.model.Emails.Queque;

public interface QuequeRepository extends JpaRepository<Queque, UUID> {
    void createQueque(Queque queque);

    void deleteQueque(long id);

    String getStatusById(long id);
}
