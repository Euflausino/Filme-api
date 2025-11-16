package com.Euflausino.adapter.output;

import com.Euflausino.application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<JpaUserEntity, String> {
    Optional<UserDetails> findByUsername(String username);
}
