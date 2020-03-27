package com.example.openapi.bean;

import javax.persistence.*;

@MappedSuperclass
public class IdEntity {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

}
