package br.com.fiap.m8music.getaway.dto.auth;

public record RegisterResponseDTO(
        String email,
        String password,
        String nome,
        String token
) {
}
