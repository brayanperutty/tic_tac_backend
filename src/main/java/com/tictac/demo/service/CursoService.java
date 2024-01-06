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
        if(curso.getJornada() == null || curso.getJornada().trim().isEmpty()){
            return null;
        }else{
            return cursoRepository.save(curso);
        }
    }

    public String updateCurso(Curso curso){
        if(cursoRepository.existsById(curso.getGrado())){
            Optional<Curso> c = cursoRepository.findById(curso.getGrado());

            c.get().setJornada(curso.getJornada());
            cursoRepository.save(c.get());
            return "Curso actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteCurso(Integer id){
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return "Curso eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<Curso> listCurso(){
        return cursoRepository.findAll();
    }
}
