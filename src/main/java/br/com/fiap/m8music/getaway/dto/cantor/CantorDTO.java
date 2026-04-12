package br.com.fiap.m8music.getaway.dto.cantor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CantorDTO(
        Long id,
        @NotBlank @Size(min = 2, max = 80) String nome,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 4, max = 60) String senha
) {
}
