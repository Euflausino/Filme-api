package com.Euflausino.application.port.output.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface IBuscarPorNomeOutput {
    UserDetails findByUsername(String username);
}
