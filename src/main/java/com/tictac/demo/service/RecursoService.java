package com.tictac.demo.service;

import com.tictac.demo.entity.Recurso;
import com.tictac.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository recursoRepository;

    public Optional<Recurso> getRecurso(Integer id){
        return recursoRepository.findById(id);
    }

    public Recurso saveRecurso(Recurso recurso){
        return recursoRepository.save(recurso);
    }

    public void deleteRecurso(Integer id){
        recursoRepository.deleteById(id);
    }

    public List<Recurso> listRecurso(){
        return recursoRepository.findAll();
    }
}
