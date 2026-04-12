package br.com.fiap.m8music.service;

import br.com.fiap.m8music.domain.Musica;
import br.com.fiap.m8music.getaway.dto.musica.MusicaDTO;
import br.com.fiap.m8music.getaway.repository.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicaService {
    private final MusicaRepository repo;

    private MusicaDTO toDTO(Musica m) {
        return new MusicaDTO(m.getId(), m.getTitulo(), m.getArtista(), m.getGenero(), m.getId());
    }

    private void apply(Musica m, MusicaDTO d) {
        m.setTitulo(d.titulo());
        m.setArtista(d.artista());
        m.setGenero(d.genero());

    }

    public MusicaDTO create(MusicaDTO dto) {
        Musica m = new Musica();
        apply(m, dto);
        return toDTO(repo.save(m));
    }

    public List<MusicaDTO> list() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public MusicaDTO get(Long id) {
        return toDTO(getEntity(id));
    }

    public MusicaDTO update(Long id, MusicaDTO dto) {
        Musica m = getEntity(id);
        apply(m, dto);
        return toDTO(repo.save(m));
    }

    public void delete(Long id) {
        repo.delete(getEntity(id));
    }

    public List<MusicaDTO> searchTitulo(String q) {
        return repo.findByTituloContainingIgnoreCase(q).stream().map(this::toDTO).toList();
    }

    public Musica getEntity(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Música não encontrada"));
    }
}
