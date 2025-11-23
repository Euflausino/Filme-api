package com.Euflausino.application.port.input;

import com.Euflausino.application.domain.Filme;

public interface IBuscarPorIdInput {
    Filme buscarPorId(String id);
}
