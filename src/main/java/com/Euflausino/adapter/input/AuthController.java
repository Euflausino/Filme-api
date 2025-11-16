package com.Euflausino.adapter.input;


import com.Euflausino.adapter.input.dto.TokenResponseDTO;
import com.Euflausino.adapter.input.dto.UserCadastroDTO;
import com.Euflausino.adapter.input.dto.UserLoginDTO;
import com.Euflausino.adapter.input.dto.UserResponseDTO;
import com.Euflausino.adapter.output.JpaUserEntity;
import com.Euflausino.application.domain.User;
import com.Euflausino.adapter.input.dto.mapper.UserMapper;
import com.Euflausino.application.port.input.filme.ICadastrarFilmeInput;
import com.Euflausino.application.port.input.user.IAutenticarInput;
import com.Euflausino.application.port.input.user.ICadastrarUserInput;
import com.Euflausino.application.usecase.UserUsecase;
import com.Euflausino.application.usecase.TokenService;
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
