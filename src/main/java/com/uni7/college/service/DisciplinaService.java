package com.uni7.college.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.uni7.college.entity.Disciplina;
import com.uni7.college.repository.DisciplinaRepository;
import com.uni7.college.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository _disciplinaRepository;
    @Autowired
    private CursoRepository _cursoRepository;

    public DisciplinaService() {};

    public Optional<Disciplina> readById(Integer id) {
        Optional<Disciplina> disciplina = _disciplinaRepository.findById(id);
        return disciplina;
        // Optional<Curso> curso = _cursoRepository.findById(idCurso);
        // if (curso.isPresent()) {
        //     Optional<Disciplina> disciplinaOptional;
        //     curso.get().getDisciplinasNavigation().forEach(disciplina -> {
        //         if (disciplina.getId() == idDisciplina) {
        //             disciplinaOptional = disciplina;
        //             break;
        //         }
        //     });
        //     if (disciplina.isPresent())
        //         return disciplina;
        //     else
        //         return "Disciplina não encontrada!";
        // }
        // else 
        //     return "Curso não encontrado!";
    }

    public List<Disciplina> readAllByCurso(Integer id) {
        List<Disciplina> disciplinas = _disciplinaRepository.findByCursoNavigation_Id(id);
        return disciplinas;
    }

    public Disciplina create(Disciplina disciplina, Integer cursoId) {
        disciplina.setCursoNavigation(_cursoRepository.findById(cursoId).get());
        Disciplina retorno = _disciplinaRepository.save(new Disciplina(disciplina.getTitulo(), disciplina.getCargaHoraria(), disciplina.getModalidade(), disciplina.getCursoNavigation()));
        return retorno;
    }
}