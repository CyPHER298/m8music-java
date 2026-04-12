package br.com.fiap.m8music.service;


import br.com.fiap.m8music.config.SecurityConfig;
import br.com.fiap.m8music.config.TokenService;
import br.com.fiap.m8music.domain.Cantor;
import br.com.fiap.m8music.exception.BusinessException;
import br.com.fiap.m8music.getaway.dto.auth.RegisterResponseDTO;
import br.com.fiap.m8music.getaway.dto.cantor.CantorCreateDTO;
import br.com.fiap.m8music.getaway.dto.cantor.CantorDTO;
import br.com.fiap.m8music.getaway.repository.CantorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CantorService {
    private final CantorRepository cantorRepository;
    private final SecurityConfig securityConfig;
    private final TokenService tokenService;

    private void apply(Cantor c, CantorDTO d) {
        c.setNome(d.nome());
        c.setEmail(d.email());
        c.setSenha(d.senha());
    }

    @Transactional
    public CantorDTO create(CantorCreateDTO dto) {
        if (cantorRepository.existsByEmail(dto.email())) throw new BusinessException("Email já cadastrado");

        String encrypetedPassword = securityConfig.passwordEncoder().encode(dto.senha());

        Cantor cantor = new Cantor();
        cantor.setNome(dto.nome());
        cantor.setEmail(dto.email());
        cantor.setSenha(encrypetedPassword);

        Cantor cantorSaved = cantorRepository.save(cantor);
        System.out.println(cantorSaved);

        return CantorDTO.builder()
                .id(cantor.getId())
                .nome(cantor.getNome())
                .email(cantor.getEmail())
                .build();
    }

    public List<CantorDTO> list() {
        List<Cantor> cantores = cantorRepository.findAll();

        ArrayList<CantorDTO> list = new ArrayList<>();

        cantores.forEach(cantor -> {
            list.add(CantorDTO.builder()
                    .id(cantor.getId())
                    .nome(cantor.getNome())
                    .email(cantor.getEmail())
                    .build());
        });

        return list;
    }

    public Cantor get(Long id) {
        return cantorRepository.findById(id).orElse(null);
    }

    public Cantor update(Long id, CantorDTO dto) {
        Cantor cantor = cantorRepository.findById(id).orElse(null);
        assert cantor != null;
        cantor.setNome(dto.nome());
        cantor.setEmail(dto.email());
        cantor.setSenha(dto.senha());
        return cantorRepository.save(cantor);
    }

    public void delete(Long id) {
        cantorRepository.delete(getEntity(id));
    }

    public Cantor getEntity(Long id) {
        return cantorRepository.findById(id).orElseThrow(() -> new RuntimeException("Cantor não encontrado"));
    }

    public Cantor registerCantor(RegisterResponseDTO body) {
        Cantor cantor = new Cantor();

        String encrypetedPassword = securityConfig.passwordEncoder().encode(body.password());

        cantor.setSenha(encrypetedPassword);
        

        cantorRepository.save(cantor);

        return cantor;
    }
}
