package com.tictac.demo.service;

import com.tictac.demo.entity.ActividadProyecto;
import com.tictac.demo.repository.ActividadProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadProyectoService {

    @Autowired
    ActividadProyectoRepository actividadProyectoRepository;

    public Optional<ActividadProyecto> getActividadProyecto(Integer id){
        return actividadProyectoRepository.findById(id);
    }

    public ActividadProyecto createActividadProyecto(ActividadProyecto actividadProyecto){
        return actividadProyectoRepository.save(actividadProyecto);
    }

    public void deleteActividadProyecto(Integer id){
        actividadProyectoRepository.deleteById(id);
    }

    public List<ActividadProyecto> listActividadProyecto(){
        return actividadProyectoRepository.findAll();
    }
}
