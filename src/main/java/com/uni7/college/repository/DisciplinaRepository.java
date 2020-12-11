package com.uni7.college.repository;

import java.util.List;

import com.uni7.college.entity.Disciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    // find all Disciplinas in curso
    List<Disciplina> findByCursoNavigation_Id(@Param("id") Integer id);
}