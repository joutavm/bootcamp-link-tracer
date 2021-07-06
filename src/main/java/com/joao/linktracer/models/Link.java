package com.joao.linktracer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Link {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long counter = Long.parseLong("0");
    private String senha;

    public Link(){
    }

    public Link(String url, Status status, String senha) {
        this.url = url;
        this.status = status;
        this.senha = senha;
    }

    public String openLink(){
        counter += 1;
        return url;
    }
}
