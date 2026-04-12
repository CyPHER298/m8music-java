package br.com.fiap.m8music.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "CANTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cantor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cantor", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nm_cantor", nullable = false, length = 50)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 50)
    @Column(name = "email_cantor", nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank
    @Size(max = 200)
    @Column(name = "senha_cantor", nullable = false, length = 200)
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CANTOR"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }


}
