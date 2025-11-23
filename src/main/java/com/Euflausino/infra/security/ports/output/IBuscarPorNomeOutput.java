package com.Euflausino.infra.security.ports.output;

import org.springframework.security.core.userdetails.UserDetails;

public interface IBuscarPorNomeOutput {
    UserDetails findByUsername(String username);
}
