package com.Euflausino.application.usecase;

import java.time.LocalDate;
import java.util.List;

import com.Euflausino.application.domain.exception.filme.FilmeNaoEncontradoException;

import com.Euflausino.application.domain.Filme;
import com.Euflausino.application.port.input.*;
import com.Euflausino.application.port.output.*;

public class FilmeUsecase implements ICadastrarFilmeInput,IAtualizarFilmeInput, IBuscarPorDataInput, IBuscarPorDiretorInput, IBuscarPorGeneroInput, IBuscarPorIdInput, IBuscarPorNotaInput,IBuscarPorTituloInput, IDeletarFilmeInput, IListarFilmesInput {
	
    private final IBuscarFilmePorNotaOutput buscarFilmePorNotaOutput;
    private final IBuscarPorDataOutput buscarFilmePorDataOutput;
    private final IBuscarPorDiretorOutput buscarFilmePorDiretorOutput;
    private final IBuscarPorGeneroOutput buscarFilmePorGeneroOutput;
    private final IBuscarPorIdOutput buscarFilmePorIdOutput;
    private final IBuscarPorTituloOutput buscarFilmePorTituloOutput;
    private final IDeletarFilmeIdOutput deletarFilmeIdOutput;
    private final IListarFilmesOutput listarFilmesOutput;
    private final ISalvarFilmeOutput salvarFilmeOutput;

    public FilmeUsecase(IBuscarFilmePorNotaOutput buscarFilmePorNotaOutput, IBuscarPorDataOutput buscarFilmePorDataOutput, IBuscarPorDiretorOutput buscarFilmePorDiretorOutput, IBuscarPorGeneroOutput buscarFilmePorGeneroOutput, IBuscarPorIdOutput buscarFilmePorIdOutput, IBuscarPorTituloOutput buscarFilmePorTituloOutput, IDeletarFilmeIdOutput deletarFilmeIdOutput, IListarFilmesOutput listarFilmesOutput, ISalvarFilmeOutput salvarFilmeOutput) {
        this.buscarFilmePorNotaOutput = buscarFilmePorNotaOutput;
        this.buscarFilmePorDataOutput = buscarFilmePorDataOutput;
        this.buscarFilmePorDiretorOutput = buscarFilmePorDiretorOutput;
        this.buscarFilmePorGeneroOutput = buscarFilmePorGeneroOutput;
        this.buscarFilmePorIdOutput = buscarFilmePorIdOutput;
        this.buscarFilmePorTituloOutput = buscarFilmePorTituloOutput;
        this.deletarFilmeIdOutput = deletarFilmeIdOutput;
        this.listarFilmesOutput = listarFilmesOutput;
        this.salvarFilmeOutput = salvarFilmeOutput;
    }

    @Override
    public List<Filme> listarFilmes(){
		return listarFilmesOutput.findAll();
	}

    @Override
	public Filme cadastrarUmFilme(Filme filme) {
		return salvarFilmeOutput.save(filme);
	}

    @Override
	public void deletarFilme(String id) {
		validarFilme(id);
        deletarFilmeIdOutput.deleteById(id);
	}

    @Override
	public List<Filme> buscarPorDiretor(String diretor){

        return buscarFilmePorDiretorOutput.findByDiretorContainingIgnoreCase(diretor);
	}

    @Override
	public Filme buscarPorId(String id){
        return validarFilme(id);
	}

    @Override
	public List<Filme> buscarPorNome(String titulo){
		return buscarFilmePorTituloOutput.findByTituloContainingIgnoreCase(titulo);
	}

    @Override
	public List<Filme> buscarPorGenero(String genero){
		return buscarFilmePorGeneroOutput.findByGeneroContainingIgnoreCase(genero);
	}

    @Override
	public List<Filme> buscarPorNota(Integer nota){
		return buscarFilmePorNotaOutput.findByNota(nota);
	}

    @Override
	public List<Filme> buscarPorData(LocalDate dataInicio, LocalDate dataFim){
		return buscarFilmePorDataOutput.findByDataLancamentoBetweenOrderByNotaDesc(dataInicio, dataFim);
	}

    @Override
    public Filme atualizarFilme(String id, Filme filmeAtualizado) {
        Filme filme = validarFilme(id);
        if (filmeAtualizado.getTitulo() != null) {filme.setTitulo(filmeAtualizado.getTitulo());}
        if(filmeAtualizado.getDataLancamento() != null){filme.setDataLancamento(filmeAtualizado.getDataLancamento());}
        if (filmeAtualizado.getNota() != null) {filme.setNota(filmeAtualizado.getNota());}
        if(filmeAtualizado.getGenero() != null) {filme.setGenero(filmeAtualizado.getGenero());}
        if(filmeAtualizado.getDiretor() != null) {filme.setDiretor(filmeAtualizado.getDiretor());}
        return filme;
    }

    private Filme validarFilme(String id) throws FilmeNaoEncontradoException {
        if(buscarFilmePorIdOutput.findById(id) != null) {
            return buscarFilmePorIdOutput.findById(id);
        }
        throw new FilmeNaoEncontradoException("Filme não encontrado!");
    }
	
}
