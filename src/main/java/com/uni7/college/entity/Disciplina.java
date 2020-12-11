package com.uni7.college.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "titulo")
public class Disciplina {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String titulo;
	private Integer cargaHoraria;
	private String modalidade;

	@ManyToOne(
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "curso_id", referencedColumnName = "id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Curso cursoNavigation;

	public Disciplina(String titulo, Integer cargaHoraria, String modalidade) {
		this.titulo = titulo;
		this.cargaHoraria = cargaHoraria;
		this.modalidade = modalidade;
	}

	public Disciplina(String titulo, Integer cargaHoraria, String modalidade, Curso cursoNavigation) {
		this.titulo = titulo;
		this.cargaHoraria = cargaHoraria;
		this.modalidade = modalidade;
		this.cursoNavigation = cursoNavigation;
	}

	@Override
	public String toString() {
		return String.format(
				"{'id'='%d', 'titulo'='%s', 'cargaHoraria'='%d', 'modalidade'='%s'}",
				id, titulo, cargaHoraria, modalidade);
	}

	public Integer getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public Curso getCursoNavigation() {
        return cursoNavigation;
    }
    public void setCursoNavigation(Curso curso) {
        this.cursoNavigation = curso;
        cursoNavigation.getDisciplinasNavigation().add(this);
    }
}