package com.uni7.college.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "nome")
public class Curso {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String nome;
    private Calendar dataCriacao;

	@OneToMany(
        mappedBy = "cursoNavigation",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
	)
	@JsonIgnore
	private Set<Disciplina> disciplinasNavigation;

	public Curso(String nome, Calendar dataCriacao) {
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.disciplinasNavigation = new HashSet<>();
	}

	public Curso(String nome, Calendar dataCriacao, Set<Disciplina> disciplinasNavigation) {
		this.nome = nome;
        this.dataCriacao = dataCriacao;
		this.disciplinasNavigation = disciplinasNavigation;
	}

	@Override
	public String toString() {
		return String.format(
				"{'id'='%d', 'nome'='%s', 'dataCriacao'='%s'}",
				id, nome, dataCriacao);
	}
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

    public Set<Disciplina> getDisciplinasNavigation() {
        return disciplinasNavigation;
    }
    public void setDisciplinasNavigation(Set<Disciplina> disciplinas) {
        this.disciplinasNavigation = disciplinas;
		for (Disciplina disciplina : disciplinas) {
            disciplina.setCursoNavigation(this);
        }
    }
}