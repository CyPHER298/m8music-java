package br.com.fiap.m8music.getaway.repository;

import br.com.fiap.m8music.domain.Musica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaRepository extends BaseRepository<Musica, Long> {

    List<Musica> findByTituloContainingIgnoreCase(String titulo);

    @Query("""
                select m from Musica m
                where lower(m.genero) = lower(:genero)
                order by m.titulo asc
            """)
    List<Musica> findByGeneroOrderByTitulo(String genero);
}
