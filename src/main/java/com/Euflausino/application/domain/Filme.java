package com.Euflausino.application.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Filme {

	private String id;
	
	private String titulo;
	
	private LocalDate dataLancamento;
	
	private Integer nota;
	
	private String genero;
	
	private String diretor;
	
	public Filme() {
		
	}

	public Filme(String id,String titulo,  LocalDate dataLancamento, Integer nota, String genero, String diretor) {
		this.id = id;
        this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.nota = nota;
		this.genero = genero;
		this.diretor = diretor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
