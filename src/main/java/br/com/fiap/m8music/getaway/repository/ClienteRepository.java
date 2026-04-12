package br.com.fiap.m8music.getaway.repository;

import br.com.fiap.m8music.domain.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {
}
