package com.Euflausino.application.port.output;

import com.Euflausino.application.domain.Filme;

import java.util.List;

public interface IBuscarPorGeneroOutput {
    List<Filme>findByGeneroContainingIgnoreCase(String genero);
}
