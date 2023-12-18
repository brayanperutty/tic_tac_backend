package com.tictac.demo.service;

import com.tictac.demo.entity.Recurso;
import com.tictac.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository recursoRepository;

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
