package com.Euflausino.adapter.output;

import com.Euflausino.adapter.output.mapper.FilmeOutputMapper;
import com.Euflausino.application.domain.Filme;
import com.Euflausino.application.port.output.filme.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JpaFilmeImpl implements IBuscarFilmePorNotaOutput, IBuscarPorDataOutput, IBuscarPorDiretorOutput, IBuscarPorGeneroOutput, IBuscarPorIdOutput, IBuscarPorTituloOutput,IDeletarFilmeIdOutput,IListarFilmesOutput,ISalvarFilmeOutput {

   private final FilmeRepository filmeRepository;

    public JpaFilmeImpl(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Override
    public List<Filme> findByNota(Integer nota) {
        List<JpaFilmeEntity> filmes = filmeRepository.findByNota(nota);
        return FilmeOutputMapper.toEntityList(filmes);
    }

    @Override
    public List<Filme> findByDataLancamentoBetweenOrderByNotaDesc(LocalDate dataInicio, LocalDate dataFim) {
       List<JpaFilmeEntity>filmes = filmeRepository.findByDataLancamentoBetweenOrderByNotaDesc(dataInicio, dataFim);
        return FilmeOutputMapper.toEntityList(filmes);
    }

    @Override
    public List<Filme> findByDiretorContainingIgnoreCase(String diretor) {
        List<JpaFilmeEntity> filmes = filmeRepository.findByDiretorContainingIgnoreCase(diretor);
        return FilmeOutputMapper.toEntityList(filmes);
    }

    @Override
    public List<Filme> findByGeneroContainingIgnoreCase(String genero) {
        List<JpaFilmeEntity> filmes = filmeRepository.findByGeneroContainingIgnoreCase(genero);
        return FilmeOutputMapper.toEntityList(filmes);
    }

    @Override
    public Filme findById(Long id) {
        JpaFilmeEntity filmeEntity = filmeRepository.findById(id).orElse(null);
        return FilmeOutputMapper.toEntity(filmeEntity);
    }

    @Override
    public List<Filme> findByTituloContainingIgnoreCase(String titulo) {
        List<JpaFilmeEntity>  filmes = filmeRepository.findByTituloContainingIgnoreCase(titulo);
        return FilmeOutputMapper.toEntityList(filmes);
    }

    @Override
    public void deleteById(Long id) {
        filmeRepository.deleteById(id);
    }

    @Override
    public List<Filme> findAll() {
        List<JpaFilmeEntity> filmes = filmeRepository.findAll();
        return FilmeOutputMapper.toEntityList(filmes);
    }

    @Override
    public Filme save(Filme filme) {
        JpaFilmeEntity save = FilmeOutputMapper.toJpa(filme);
        return FilmeOutputMapper.toEntity(filmeRepository.save(save));
    }
}
