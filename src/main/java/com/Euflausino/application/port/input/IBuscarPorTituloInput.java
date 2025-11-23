package com.Euflausino.application.port.input;

import com.Euflausino.application.domain.Filme;

import java.util.List;

public interface IBuscarPorTituloInput {
    List<Filme> buscarPorNome(String titulo);
}
