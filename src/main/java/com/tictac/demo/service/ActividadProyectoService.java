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

    public String updateActividadProyecto(ActividadProyecto actividadProyecto){
        if(actividadProyectoRepository.existsById(actividadProyecto.getIdActividad())){
            Optional<ActividadProyecto> act = actividadProyectoRepository.findById(actividadProyecto.getIdActividad());

            act.get().setNombre(actividadProyecto.getNombre());
            act.get().setDescripcion(actividadProyecto.getDescripcion());
            act.get().setObservaciones(actividadProyecto.getObservaciones());
            act.get().setCumplimiento(actividadProyecto.getCumplimiento());

            actividadProyectoRepository.save(act.get());
            return "Actividad del proyecto actualizada con éxito";
        }else{
            return null;
        }
    }

    public String deleteActividadProyecto(Integer id){
        if(actividadProyectoRepository.existsById(id)){
            actividadProyectoRepository.deleteById(id);
            return "Actividad eliminada con éxito";
        }else{
            return null;
        }
    }

    public List<ActividadProyecto> listActividadProyecto(){
        return actividadProyectoRepository.findAll();
    }
}
