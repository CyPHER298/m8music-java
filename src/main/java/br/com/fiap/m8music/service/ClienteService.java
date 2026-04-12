package br.com.fiap.m8music.service;

import br.com.fiap.m8music.config.TokenService;
import br.com.fiap.m8music.domain.Cliente;
import br.com.fiap.m8music.getaway.dto.auth.LoginResponseDTO;
import br.com.fiap.m8music.getaway.dto.cliente.ClienteCreateDTO;
import br.com.fiap.m8music.getaway.dto.cliente.ClienteDTO;
import br.com.fiap.m8music.getaway.dto.cliente.ClienteResponseDTO;
import br.com.fiap.m8music.getaway.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repo;
    private final TokenService tokenService;

    private ClienteResponseDTO toDTO(Cliente c) {
        return ClienteResponseDTO.builder()
                .id(c.getId())
                .nome(c.getNome())
                .build();
    }

    @Transactional
    public ClienteResponseDTO create(ClienteDTO dto) {

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());

        repo.save(cliente);

        return toDTO(cliente);
    }

    public List<ClienteDTO> list() {
        List<Cliente> clientes = repo.findAll();

        ArrayList<ClienteDTO> list = new ArrayList<>();

        clientes.forEach((cliente) -> {
            list.add(ClienteDTO.builder()
                    .nome(cliente.getNome())
                    .build());
        });

        return list;
    }

    public Cliente get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Cliente update(@Valid Long id, @Valid ClienteDTO dto) {
        Cliente cliente = repo.findById(id).orElse(null);
        assert cliente != null;
        cliente.setNome(dto.nome());
        return repo.save(cliente);

    }

    @Transactional
    public Cliente delete(Long id) {
        Cliente cliente = repo.findById(id).orElse(null);

        assert cliente != null;
        repo.delete(cliente);

        return cliente;
    }

    public Cliente getEntity(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional
    public LoginResponseDTO createGuest(ClienteCreateDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        repo.save(cliente);

        String token = tokenService.generateGuestToken(cliente);

        return LoginResponseDTO.builder()
                .token(token)
                .build();
    }
}
