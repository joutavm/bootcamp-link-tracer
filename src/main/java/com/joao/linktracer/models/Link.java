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

    public Link(){
    }

    public Link(String url, Status status) {
        this.url = url;
        this.status = status;
    }

    public String openLink(){
        counter += 1;
        return url;
    }
}
