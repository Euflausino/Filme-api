package com.Euflausino.application.usecase;

import com.Euflausino.adapter.input.dto.UserLoginDTO;
import com.Euflausino.application.domain.User;
import com.Euflausino.application.port.input.user.IAutenticarInput;
import com.Euflausino.application.port.input.user.ICadastrarUserInput;
import com.Euflausino.application.port.output.user.IBuscarPorNomeOutput;
import com.Euflausino.application.port.output.user.ISalvarUserOutput;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUsecase implements UserDetailsService, IAutenticarInput, ICadastrarUserInput {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IBuscarPorNomeOutput buscarPorNomeOutput;
    private final ISalvarUserOutput salvarUserOutput;

    public UserUsecase(IBuscarPorNomeOutput buscarPorNomeOutput, ISalvarUserOutput salvarUserOutput, @Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.buscarPorNomeOutput = buscarPorNomeOutput;
        this.salvarUserOutput = salvarUserOutput;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override //spring chama esse método automaticamente durante o login
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(buscarPorNomeOutput.findByUsername(username) != null){
            return buscarPorNomeOutput.findByUsername(username);
        }
        throw new UsernameNotFoundException("Nome não encontrado.");
    }

    @Override
    public User cadastrarUsuario(User user) {
        String rawPassword = user.getPassword();
        String encoded = passwordEncoder.encode(rawPassword);
        user.setPassword(encoded);
        return salvarUserOutput.save(user);
    }

    @Override
    public Authentication autenticar(UserLoginDTO user) {
        var token = new UsernamePasswordAuthenticationToken(user.username(), user.senha());  //gera um token de autenticacao
        return authenticationManager.authenticate(token); //autentica o token, chamando a classe loadByUsername
        //Compara a senha informada com a senha codificada no banco.
        //Se tudo estiver certo, retorna um objeto Authentication autenticado (com o usuário dentro dele).
    }
}
