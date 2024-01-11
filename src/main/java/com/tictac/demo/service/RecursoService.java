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
        if(recurso.getTipo() == null || recurso.getTipo().trim().isEmpty() ||
            recurso.getUrl() == null || recurso.getUrl().trim().isEmpty() ||
            recurso.getNombre() == null || recurso.getNombre().trim().isEmpty()){
            return null;
        }else{
            return recursoRepository.save(recurso);
        }
    }

    public String updateRecurso(Recurso recurso){
        if(recursoRepository.existsById(recurso.getIdRecurso())){
            Optional<Recurso> r = recursoRepository.findById(recurso.getIdRecurso());

            r.get().setTipo(recurso.getTipo());
            r.get().setUrl(recurso.getUrl());
            r.get().setNombre(recurso.getNombre());
            recursoRepository.save(r.get());
            return "Recurso actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteRecurso(Integer id){
        if(recursoRepository.existsById(id)){
            recursoRepository.deleteById(id);
            return "Recurso eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<Recurso> listRecurso(){
        return recursoRepository.findAll();
    }
}
