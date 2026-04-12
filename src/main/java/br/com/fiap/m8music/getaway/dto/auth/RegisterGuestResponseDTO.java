package br.com.fiap.m8music.getaway.dto.auth;

import lombok.Builder;

@Builder
public record RegisterGuestResponseDTO(
        Long id,
        String nome
) {
}
