package com.tictac.demo.service;

import com.tictac.demo.entity.Curso;
import com.tictac.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public Optional<Curso> getCurso(Integer id){
        return cursoRepository.findById(id);
    }

    public Curso createCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public void deleteCurso(Integer id){
        cursoRepository.deleteById(id);
    }

    public List<Curso> listCurso(){
        return cursoRepository.findAll();
    }
}
