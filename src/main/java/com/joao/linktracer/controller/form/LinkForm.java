package com.joao.linktracer.controller.form;

import com.joao.linktracer.models.Link;
import com.joao.linktracer.models.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
public class LinkForm {

    @NotNull @NotEmpty
    private String url;

    public Link convert(){
        return new Link(url, Status.VALIDO);
    }

    public LinkForm(){}

}
