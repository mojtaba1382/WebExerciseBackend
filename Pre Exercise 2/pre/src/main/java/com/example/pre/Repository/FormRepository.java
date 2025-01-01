package com.example.pre.Repository;

import com.example.pre.Model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByIsPublishedTrue();
}