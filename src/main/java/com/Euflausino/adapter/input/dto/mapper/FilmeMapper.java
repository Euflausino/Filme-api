package com.Euflausino.adapter.input.dto.mapper;

import java.util.List;

import com.Euflausino.adapter.input.dto.AtualizarFilmeDTO;
import com.Euflausino.adapter.input.dto.FilmeCadastroDTO;
import com.Euflausino.adapter.input.dto.FilmeResponseDTO;
import com.Euflausino.application.domain.Filme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class FilmeMapper {

    public static Filme toEntity(FilmeCadastroDTO dto) {
		return new Filme(
                null,
                dto.titulo(),
                dto.dataLancamento(),
                dto.nota(),
                dto.genero(),
                dto.diretor()
        );
	}
	
	public static FilmeResponseDTO dtoResponse(Filme toEntity) {

        return new FilmeResponseDTO(
				toEntity.getId(),
				toEntity.getTitulo(),
				toEntity.getDataLancamento(),
				toEntity.getNota(),
				toEntity.getGenero(),
				toEntity.getDiretor(),
                switch (toEntity.getNota()){
                    case 1 -> "Esse é ruim demais!";
                    case 2 -> "Ok!";
                    case 3 ->  "Bom!";
                    case 4 ->  "Excelente!";
                    case 5 ->  "Absolute cinema!";
                    default -> null;
                }
        );
	}
	
	public static Page<FilmeResponseDTO> toResponsePageDTO(List<Filme> filmes) {
	    List<FilmeResponseDTO> retorno = filmes.stream()
	                 .map(FilmeMapper::dtoResponse).toList();
        return new PageImpl<>(retorno);
	}

    public static Filme filmeAtt(AtualizarFilmeDTO dto) {
        return new Filme(
                null,
                dto.titulo(),
                dto.dataLancamento(),
                dto.nota(),
                dto.genero(),
                dto.diretor()
        );
    }
	
}
