package br.com.fiap.m8music.getaway.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ClienteDTO(
        Long id,
        @NotBlank @Size(min = 2, max = 80) String nome
) {
}
