package com.example.pre.Controller;

import com.example.pre.Model.Form;
import com.example.pre.Model.Field;
import com.example.pre.Service.FormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forms")
        public class FormController {
        private final FormService formService;

        public FormController(FormService formService) {
        this.formService = formService;
        }

@GetMapping
public List<Form> getAllForms() {
        return formService.getAllForms();
        }

@GetMapping("/{id}")
        public ResponseEntity<Form> getFormById(@PathVariable Long id) {
        return formService.getFormById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }

@PostMapping
public Form createForm(@RequestBody Form form) {
        return formService.createForm(form);
        }

@PutMapping("/{id}")
        public Form updateForm(@PathVariable Long id, @RequestBody Form updatedForm) {
        return formService.updateForm(id, updatedForm);
    }

@PostMapping("/{id}/publish")
        public Form changePublishingMode(@PathVariable Long id, @RequestBody Form form) {
        return formService.changePublishingMode(id);
    }
@DeleteMapping("/{id}")
        public void deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
        }

@GetMapping("/published")
        public List<Form> getPublishedForms() {
        return formService.getPublishedForms();
        }

@GetMapping("/{id}/fields")
        public ResponseEntity<List<Field>> getFieldsByFormId(@PathVariable Long id) {
        Optional<Form> form = formService.getFormById(id);

        if (form.isPresent()) {
                return ResponseEntity.ok(form.get().getFields());
        } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        }
@PutMapping("/{id}/fields")
        public ResponseEntity<List<Field>> updateFieldsByFormId(
                @PathVariable Long id,
                @RequestBody List<Field> updatedFields) {

                Optional<Form> formOptional = formService.getFormById(id);

                if (formOptional.isPresent()) {
                        Form form = formOptional.get();
                        form.getFields().clear();
                        for (Field field : updatedFields) {
                                field.setForm(form);
                                form.getFields().add(field);
                        }

                        formService.saveForm(form);
                        return ResponseEntity.ok(form.getFields());
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
        }
        }
