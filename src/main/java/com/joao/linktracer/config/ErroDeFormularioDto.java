package com.joao.linktracer.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErroDeFormularioDto {

    private final String campo;
    private final String erro;

}
