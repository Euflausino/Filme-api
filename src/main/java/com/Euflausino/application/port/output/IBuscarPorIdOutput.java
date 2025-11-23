package com.Euflausino.application.port.output;

import com.Euflausino.application.domain.Filme;

public interface IBuscarPorIdOutput {
    Filme findById(String id);
}
