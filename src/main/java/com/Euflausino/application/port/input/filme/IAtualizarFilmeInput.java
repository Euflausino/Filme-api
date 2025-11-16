package com.Euflausino.application.port.input.filme;

import com.Euflausino.adapter.input.dto.AtualizarFilmeDTO;
import com.Euflausino.application.domain.Filme;

public interface IAtualizarFilmeInput {
    Filme atualizarFilme(String id, Filme filmeAtualizado);
}
