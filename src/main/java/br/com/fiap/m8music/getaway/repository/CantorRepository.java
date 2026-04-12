package br.com.fiap.m8music.getaway.repository;


import br.com.fiap.m8music.domain.Cantor;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CantorRepository extends BaseRepository<Cantor, Long> {
    boolean existsByEmail(String email);
    UserDetails findByEmail(String email);

}
