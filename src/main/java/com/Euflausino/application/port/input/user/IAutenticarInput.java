package com.Euflausino.application.port.input.user;

import com.Euflausino.adapter.input.dto.UserLoginDTO;
import org.springframework.security.core.Authentication;

public interface IAutenticarInput {
    Authentication autenticar(UserLoginDTO user);
}
