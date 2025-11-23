package com.Euflausino.infra.security.controller;


import com.Euflausino.infra.security.dto.TokenResponseDTO;
import com.Euflausino.infra.security.dto.UserCadastroDTO;
import com.Euflausino.infra.security.dto.UserLoginDTO;
import com.Euflausino.infra.security.dto.UserResponseDTO;
import com.Euflausino.infra.security.repository.JpaUserEntity;
import com.Euflausino.infra.security.entity.User;
import com.Euflausino.infra.security.dto.UserMapper;
import com.Euflausino.infra.security.ports.input.IAutenticarInput;
import com.Euflausino.infra.security.ports.input.ICadastrarUserInput;
import com.Euflausino.infra.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final IAutenticarInput autenticarInput;
    private final ICadastrarUserInput cadastrarUserInput;

    private final TokenService tokenService;
    public AuthController(IAutenticarInput autenticarInput, ICadastrarUserInput cadastrarUserInput, TokenService tokenService) {
        this.autenticarInput = autenticarInput;
        this.cadastrarUserInput = cadastrarUserInput;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody UserLoginDTO user) {
        return ResponseEntity.ok( UserMapper.mapToken(
                    tokenService.gerarToken(
                            (JpaUserEntity) autenticarInput.autenticar(user).getPrincipal()
                    )
                )
        );
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UserResponseDTO> cadastro(@Valid @RequestBody UserCadastroDTO user) {
        User userMapped = UserMapper.mapToEntity(user);
        User userSaved = cadastrarUserInput.cadastrarUsuario(userMapped);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.mapToDTO(userSaved));
    }

}
