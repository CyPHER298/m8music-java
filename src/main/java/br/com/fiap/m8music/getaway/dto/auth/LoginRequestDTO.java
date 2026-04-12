package br.com.fiap.m8music.getaway.dto.auth;

import lombok.Builder;

@Builder
public record LoginRequestDTO(
        String email,
        String senha
) {
}
