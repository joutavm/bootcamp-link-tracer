package com.joao.linktracer.controller.form;

import com.joao.linktracer.models.Link;
import com.joao.linktracer.models.Status;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
public class LinkForm {

    @NotNull @NotEmpty
    private String url;
    @NotEmpty @NotNull @Size(min = 5)
    private String senha;

    public Link convert(){
        return new Link(url, Status.VALIDO, senha);
    }

    public LinkForm(){}

}
