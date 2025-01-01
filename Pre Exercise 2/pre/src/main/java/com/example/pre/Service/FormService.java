package com.example.pre.Service;
import com.example.pre.Model.Field;

import com.example.pre.Model.Form;
import com.example.pre.Repository.FormRepository;
import com.example.pre.Repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    private final FormRepository formRepository;
    private final FieldRepository fieldRepository;

    public FormService(FormRepository formRepository, FieldRepository fieldRepository) {
        this.formRepository = formRepository;
        this.fieldRepository = fieldRepository;
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Optional<Form> getFormById(Long id) {
        return formRepository.findById(id);
    }

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public Form updateForm(Long id, Form updatedForm) {
        return formRepository.findById(id).map(form -> {
            form.setName(updatedForm.getName());
            form.setSubmitURL(updatedForm.getSubmitURL());
            return formRepository.save(form);
        }).orElseThrow(() -> new RuntimeException("Form not found"));
    }

    public Form changePublishingMode(Long id) {
        return formRepository.findById(id).map(form -> {
            if (form.isPublished()){
                form.setPublished(false);
            }
            else {
                form.setPublished(true);
            }
            return formRepository.save(form);
        }).orElseThrow(() -> new RuntimeException("Form not found"));
    }

    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    public List<Form> getPublishedForms() {
        return formRepository.findByIsPublishedTrue();
    }

    public void saveForm(Form form) {
        formRepository.save(form);
    }

}
