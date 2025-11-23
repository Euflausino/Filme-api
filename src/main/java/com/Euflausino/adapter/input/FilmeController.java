package com.Euflausino.adapter.input;

import java.net.URI;
import java.time.LocalDate;

import com.Euflausino.adapter.input.dto.AtualizarFilmeDTO;
import com.Euflausino.adapter.input.dto.mapper.FilmeMapper;
import com.Euflausino.application.domain.Filme;
import com.Euflausino.application.port.input.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Euflausino.adapter.input.dto.FilmeCadastroDTO;
import com.Euflausino.adapter.input.dto.FilmeResponseDTO;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/filmes")
public class FilmeController {

    private final ICadastrarFilmeInput cadastrarFilmeInput;
    private  final IAtualizarFilmeInput atualizarFilmeInput;
    private final IBuscarPorDataInput buscarPorDataInput;
    private final IBuscarPorDiretorInput buscarPorDiretorInput;
    private final IBuscarPorGeneroInput buscarPorGeneroInput;
    private final IBuscarPorIdInput buscarPorIdInput;
    private final IBuscarPorNotaInput buscarPorNotaInput;
    private final IBuscarPorTituloInput buscarPorTituloInput;
    private final IDeletarFilmeInput deletarFilmeInput;
    private final IListarFilmesInput listarFilmesInput;

    public FilmeController(ICadastrarFilmeInput cadastrarFilmeInput, IAtualizarFilmeInput atualizarFilmeInput, IBuscarPorDataInput buscarPorDataInput, IBuscarPorDiretorInput buscarPorDiretorInput, IBuscarPorGeneroInput buscarPorGeneroInput, IBuscarPorIdInput buscarPorIdInput, IBuscarPorNotaInput buscarPorNotaInput, IBuscarPorTituloInput buscarPorTituloInput, IDeletarFilmeInput deletarFilmeInput, IListarFilmesInput listarFilmesInput) {
        this.cadastrarFilmeInput = cadastrarFilmeInput;
        this.atualizarFilmeInput = atualizarFilmeInput;
        this.buscarPorDataInput = buscarPorDataInput;
        this.buscarPorDiretorInput = buscarPorDiretorInput;
        this.buscarPorGeneroInput = buscarPorGeneroInput;
        this.buscarPorIdInput = buscarPorIdInput;
        this.buscarPorNotaInput = buscarPorNotaInput;
        this.buscarPorTituloInput = buscarPorTituloInput;
        this.deletarFilmeInput = deletarFilmeInput;
        this.listarFilmesInput = listarFilmesInput;
    }

    @GetMapping(value="/listar")
	public ResponseEntity<Page<FilmeResponseDTO>> listarTodos(){
		return ResponseEntity.ok(FilmeMapper.toResponsePageDTO(listarFilmesInput.listarFilmes()));
	}
	
	@PostMapping(value="/cadastrar")
	public ResponseEntity<FilmeResponseDTO> cadastrarFilme(@RequestBody @Valid FilmeCadastroDTO dto){
        Filme filmeSaved = FilmeMapper.toEntity(dto);
        FilmeResponseDTO filmeCadastrado = FilmeMapper.dtoResponse(cadastrarFilmeInput.cadastrarUmFilme(filmeSaved));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/filmes/{id}")
                .buildAndExpand(filmeCadastrado.id())
                .toUri();
			return ResponseEntity.created(uri).body(filmeCadastrado);
		
	}
	
	@PutMapping(value="/atualizar/{id}")
	public ResponseEntity<FilmeResponseDTO> atualizarFilme(@PathVariable String id, @RequestBody @Valid AtualizarFilmeDTO filmeAtualizado){
        Filme filmeAtt = FilmeMapper.filmeAtt(filmeAtualizado);
			return ResponseEntity.ok().body(FilmeMapper.dtoResponse(atualizarFilmeInput.atualizarFilme(id, filmeAtt)));
		
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Void> deletarFilme(@PathVariable String id) {
		
		deletarFilmeInput.deletarFilme(id);
        return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping(value="/busca-por-diretor")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorDiretor(@RequestParam String diretor){

			return ResponseEntity.ok().body(FilmeMapper.toResponsePageDTO(buscarPorDiretorInput.buscarPorDiretor(diretor)));
	}
	
	@GetMapping(value="/buscar-por-id")
	public ResponseEntity<FilmeResponseDTO> buscarPorId(@RequestParam String id){
		 return  ResponseEntity.ok().body(FilmeMapper.dtoResponse(buscarPorIdInput.buscarPorId(id)));
	}
	
	@GetMapping(value="/buscar-por-titulo")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorTitulo(@RequestParam String titulo){
		 return ResponseEntity.ok().body(FilmeMapper.toResponsePageDTO(buscarPorTituloInput.buscarPorNome(titulo)));
	}
	
	@GetMapping(value="/buscar-por-genero")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorGenero(@RequestParam String genero){
		return ResponseEntity.ok().body(FilmeMapper.toResponsePageDTO(buscarPorGeneroInput.buscarPorGenero(genero)));
	}
	
	@GetMapping(value="/buscar-por-nota/{nota}")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorNota(@PathVariable Integer nota){
		return ResponseEntity.ok().body(FilmeMapper.toResponsePageDTO(buscarPorNotaInput.buscarPorNota(nota)));
	}
	
	@GetMapping(value="/buscar-por-data")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorData(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
		return ResponseEntity.ok().body(FilmeMapper.toResponsePageDTO(buscarPorDataInput.buscarPorData(inicio, fim)));
	}
}
