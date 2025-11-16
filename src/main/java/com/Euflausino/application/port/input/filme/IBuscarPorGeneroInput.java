package com.Euflausino.application.port.input.filme;

import com.Euflausino.application.domain.Filme;

import java.util.List;

public interface IBuscarPorGeneroInput {
    List<Filme> buscarPorGenero(String genero);
}
