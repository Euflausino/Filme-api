package com.Euflausino.adapter.output;

import com.Euflausino.adapter.output.mapper.UserOutputMapper;
import com.Euflausino.application.domain.User;
import com.Euflausino.application.port.output.user.IBuscarPorNomeOutput;
import com.Euflausino.application.port.output.user.ISalvarUserOutput;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserImpl implements IBuscarPorNomeOutput, ISalvarUserOutput {

   private final UsuarioRepository usuarioRepository;

    public JpaUserImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails findByUsername(String username) {
        Optional<UserDetails> user = usuarioRepository.findByUsername(username);
        return user.get();
    }

    @Override
    public User save(User user) {
        JpaUserEntity entity = UserOutputMapper.toDto(user);
        return UserOutputMapper.toEntity(usuarioRepository.save(entity));
    }
}
