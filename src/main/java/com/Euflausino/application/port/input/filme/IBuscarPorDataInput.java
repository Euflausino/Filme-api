package com.Euflausino.application.port.input.filme;

import com.Euflausino.application.domain.Filme;

import java.time.LocalDate;
import java.util.List;

public interface IBuscarPorDataInput {
    List<Filme> buscarPorData(LocalDate dataInicio, LocalDate dataFim);
}
