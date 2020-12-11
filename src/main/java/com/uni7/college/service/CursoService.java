package com.uni7.college.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.uni7.college.entity.Curso;
import com.uni7.college.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CursoService {
    @Autowired
    private CursoRepository _cursoRepository;

    public CursoService() {};

    public Optional<Curso> readById(Integer id) {
        Optional<Curso> curso = _cursoRepository.findById(id);
        return curso;
    }

    public List<Curso> readAll() {
        List<Curso> cursos = _cursoRepository.findAll();
        return cursos;
    }

    public Curso create(Curso curso) {
        Curso retorno = _cursoRepository.save(new Curso(curso.getNome(), Calendar.getInstance()));
        return retorno;
    }
}