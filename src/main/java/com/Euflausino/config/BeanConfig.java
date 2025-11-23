package com.Euflausino.config;

import com.Euflausino.application.port.output.*;
import com.Euflausino.application.usecase.FilmeUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public FilmeUsecase filmeUsecase(IBuscarFilmePorNotaOutput buscarFilmePorNotaOutput, IBuscarPorDataOutput buscarFilmePorDataOutput, IBuscarPorDiretorOutput buscarFilmePorDiretorOutput, IBuscarPorGeneroOutput buscarFilmePorGeneroOutput, IBuscarPorIdOutput buscarFilmePorIdOutput, IBuscarPorTituloOutput buscarFilmePorTituloOutput, IDeletarFilmeIdOutput deletarFilmeIdOutput, IListarFilmesOutput listarFilmesOutput, ISalvarFilmeOutput salvarFilmeOutput) {
        return new FilmeUsecase(buscarFilmePorNotaOutput, buscarFilmePorDataOutput, buscarFilmePorDiretorOutput,buscarFilmePorGeneroOutput,buscarFilmePorIdOutput,buscarFilmePorTituloOutput,deletarFilmeIdOutput,listarFilmesOutput,salvarFilmeOutput);
    }
}
