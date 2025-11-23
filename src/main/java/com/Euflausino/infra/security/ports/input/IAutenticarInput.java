package com.Euflausino.infra.security.ports.input;

import com.Euflausino.infra.security.dto.UserLoginDTO;
import org.springframework.security.core.Authentication;

public interface IAutenticarInput {
    Authentication autenticar(UserLoginDTO user);
}
