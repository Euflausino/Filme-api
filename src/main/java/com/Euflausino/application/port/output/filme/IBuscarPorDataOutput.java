package com.Euflausino.application.port.output.filme;

import com.Euflausino.application.domain.Filme;

import java.time.LocalDate;
import java.util.List;

public interface IBuscarPorDataOutput {
    List<Filme>findByDataLancamentoBetweenOrderByNotaDesc(LocalDate dataInicio, LocalDate dataFim);
}
