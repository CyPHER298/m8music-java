package br.com.fiap.m8music.getaway.dto.pedido;

import jakarta.validation.constraints.NotNull;

public record PedidoDTO(
        Long id,
        @NotNull Long clienteId,
        @NotNull Long musicaId
) {
}
