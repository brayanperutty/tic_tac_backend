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

    public String seguimientoActividadProyecto(List<ActividadProyecto> actividadProyecto){

        actividadProyecto.forEach(ap ->{
            Optional<ActividadProyecto> actividad = actividadProyectoRepository.findById(ap.getIdActividad());

            actividad.get().setCumplimiento(ap.getCumplimiento());
            actividad.get().setObservaciones(ap.getObservaciones());
            actividadProyectoRepository.save(actividad.get());
        });

        return "Seguimiento registrado con éxito";
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
