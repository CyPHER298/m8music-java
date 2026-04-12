package br.com.fiap.m8music.getaway.dto.cliente;

import lombok.Builder;

@Builder
public record ClienteResponseDTO(
        Long id,
        String nome
) {
}
