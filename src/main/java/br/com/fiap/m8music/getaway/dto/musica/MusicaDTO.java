package br.com.fiap.m8music.getaway.dto.musica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MusicaDTO(
        Long id,
        @NotBlank @Size(min = 2, max = 100) String titulo,
        @NotBlank @Size(min = 2, max = 100) String artista,
        @NotBlank @Size(min = 2, max = 50) String genero,
        @NotNull Long cantorId
) {
}
