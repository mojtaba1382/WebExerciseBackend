package com.example.pre.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isPublished;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields = new ArrayList<>();

    private String submitURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublished() {
        return this.isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public List<Field> getFields() {
        return fields;
    }


    public String getSubmitURL() {
        return submitURL;
    }

    public void setSubmitURL(String submitURL) {
        this.submitURL = submitURL;
    }
}
