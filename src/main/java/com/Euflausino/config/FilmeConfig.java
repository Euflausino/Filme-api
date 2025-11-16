package com.Euflausino.config;

import java.time.LocalDate;
import java.util.Arrays;

import com.Euflausino.adapter.output.JpaFilmeEntity;
import com.Euflausino.adapter.output.JpaUserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Euflausino.adapter.input.dto.FilmeCadastroDTO;
import com.Euflausino.adapter.input.dto.mapper.FilmeMapper;
import com.Euflausino.application.domain.Filme;
import com.Euflausino.adapter.output.FilmeRepository;

@Configuration // executa a aplicaçao em contexto especifico, conforme no app.properts
@Profile("test") //executa a classe sempre que logado no perfil de teste no .properts
public class FilmeConfig implements CommandLineRunner{

	private final FilmeRepository repository;

    public FilmeConfig(FilmeRepository repository) {
        this.repository = repository;
    }

	@Override //permite mocar dados, para persistirem mesmo com banco de dados em memoria.
	public void run(String... args) {
		// TODO Auto-generated method stub
		
		JpaFilmeEntity filme1 = new JpaFilmeEntity("Como treinar seu dragão", LocalDate.of(2020,2,19), 5, "Aventura", "Robert");
        JpaFilmeEntity filme2 = new JpaFilmeEntity("Velozes e furiosos", LocalDate.of(2018,3,19), 4, "Ação", "Autor um");
        JpaFilmeEntity filme3 = new JpaFilmeEntity("Superman", LocalDate.of(2023,2,8), 3, "Ação", "Autor dois");
        JpaFilmeEntity filme4 = new JpaFilmeEntity("Filme legal01", LocalDate.of(2000,4,16), 2, "Romance", "Autor um");
        JpaFilmeEntity filme5 = new JpaFilmeEntity("Filme Legal", LocalDate.of(2010,2,6), 1, "Comedia", "Robert");
		
		repository.saveAll(Arrays.asList(filme1, filme2, filme3, filme4, filme5));
		
	}
	
}
