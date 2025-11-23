package com.Euflausino.infra.security.ports.input;

import com.Euflausino.infra.security.entity.User;

public interface ICadastrarUserInput {
    User cadastrarUsuario(User user);
}
