package com.Euflausino.adapter.output;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Euflausino.application.domain.Filme;

public interface FilmeRepository extends JpaRepository<JpaFilmeEntity, Long>{
	
	List<JpaFilmeEntity> findByTituloContainingIgnoreCase(String nome);
	List<JpaFilmeEntity> findByDiretorContainingIgnoreCase(String diretor);
	List<JpaFilmeEntity> findByGeneroContainingIgnoreCase(String genero);
	List<JpaFilmeEntity> findByNota(Integer nota);
	List<JpaFilmeEntity> findByDataLancamentoBetweenOrderByNotaDesc(LocalDate dataInicio, LocalDate dataFim);
}
