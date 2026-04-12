package br.com.fiap.m8music.getaway.dto.cantor;

import lombok.Builder;

@Builder
public record CantorCreateDTO(
        String email,
        String nome,
        String senha
) {
}
