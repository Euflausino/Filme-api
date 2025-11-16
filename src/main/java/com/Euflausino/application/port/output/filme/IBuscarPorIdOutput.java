package com.Euflausino.application.port.output.filme;

import com.Euflausino.application.domain.Filme;

public interface IBuscarPorIdOutput {
    Filme findById(Long id);
}
