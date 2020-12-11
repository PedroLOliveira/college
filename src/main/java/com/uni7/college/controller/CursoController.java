package com.uni7.college.controller;

import java.util.List;
import java.util.Optional;

import com.uni7.college.entity.Curso;
import com.uni7.college.entity.Disciplina;
import com.uni7.college.service.DisciplinaService;
import com.uni7.college.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {
	@Autowired
	private CursoService _cursoService;
	@Autowired
	private DisciplinaService _disciplinaService;
	
	@GetMapping
	public ResponseEntity<List<Curso>> ListarCursos() {
		List<Curso> cursos = _cursoService.readAll();
		if (cursos != null)
			return new ResponseEntity<>(cursos, HttpStatus.OK);
		return new ResponseEntity<List<Curso>>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path="/{id}")
	public ResponseEntity<String> ListarCursoPorId(@RequestParam(value = "id", required = true) Integer id) {
		Optional<Curso> curso = _cursoService.readById(id);
		if (curso.isPresent())
			return new ResponseEntity<>(curso.toString(), HttpStatus.OK);
		return new ResponseEntity<>("Curso não encontrado!", HttpStatus.NO_CONTENT);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> CadastrarCurso(@RequestBody Curso entidade) {
		Curso curso = _cursoService.create(entidade);
		if (curso != null)
			return new ResponseEntity<>(curso.toString(), HttpStatus.OK);
		return new ResponseEntity<>("Não pode existir dois cursos com o mesmo nome!", HttpStatus.NOT_ACCEPTABLE);
	}



	@GetMapping(path="/{cursoId}/disciplinas")
	public ResponseEntity<List<Disciplina>> ListarDisciplinas(@RequestParam(value = "cursoId", required = true) Integer cursoId) {
		Optional<Curso> curso = _cursoService.readById(cursoId);
		if (!curso.isPresent())
			return new ResponseEntity<List<Disciplina>>(HttpStatus.NOT_FOUND);
		List<Disciplina> disciplinas = _disciplinaService.readAllByCurso(cursoId);
		// curso.getDisciplinasNavigation();
		if (disciplinas != null)
			return new ResponseEntity<>(disciplinas, HttpStatus.OK);
		return new ResponseEntity<List<Disciplina>>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path="/{cursoId}/disciplinas/{id}")
	public ResponseEntity<String> ListarDisciplinaPorId(@RequestParam(value = "cursoId", required = true) Integer cursoId, @RequestParam(value = "id", required = true) Integer id) {
		Optional<Curso> curso = _cursoService.readById(cursoId);
		if (!curso.isPresent())
			return new ResponseEntity<>("Curso não encontrado!", HttpStatus.NOT_FOUND);
		Optional<Disciplina> disciplina = _disciplinaService.readById(id);
		if (curso.isPresent())
			return new ResponseEntity<>(disciplina.toString(), HttpStatus.OK);
		return new ResponseEntity<>("Disciplina não encontrado!", HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path="/{cursoId}/disciplinas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> CadastrarDisciplina(@RequestParam(value = "cursoId", required = true) Integer cursoId, @RequestBody Disciplina entidade) {
		Optional<Curso> curso = _cursoService.readById(cursoId);
		if (!curso.isPresent())
			return new ResponseEntity<>("Curso não encontrado!", HttpStatus.NOT_FOUND);
		Disciplina disciplina = _disciplinaService.create(entidade, cursoId);
		if (disciplina != null)
			return new ResponseEntity<>(disciplina.toString(), HttpStatus.OK);
		return new ResponseEntity<>("Não pode existir duas disciplinas com o mesmo nome!", HttpStatus.NOT_ACCEPTABLE);
	}
}