package com.Euflausino.adapter.output.mapper;

import com.Euflausino.adapter.output.JpaFilmeEntity;
import com.Euflausino.application.domain.Filme;

import java.util.List;

public class FilmeOutputMapper {
    public static Filme toEntity(JpaFilmeEntity jpa) {
        return new Filme(
                jpa.getId(),
                jpa.getTitulo(),
                jpa.getDataLancamento(),
                jpa.getNota(),
                jpa.getGenero(),
                jpa.getDiretor()
        );
    }

    public static JpaFilmeEntity toJpa(Filme filme) {
        return new JpaFilmeEntity(
                filme.getTitulo(),
                filme.getDataLancamento(),
                filme.getNota(),
                filme.getGenero(),
                filme.getDiretor()
        );
    }

    public static List<Filme> toEntityList(List<JpaFilmeEntity> filmes) {
        return filmes.stream()
                .map(FilmeOutputMapper::toEntity)
                .toList();
    }

}
