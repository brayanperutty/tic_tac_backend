package com.tictac.demo.service;

import com.tictac.demo.entity.Estudiante;
import com.tictac.demo.entity.EstudianteProyecto;
import com.tictac.demo.repository.EstudianteProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteProyectoService {

    @Autowired
    EstudianteProyectoRepository estudianteProyectoRepository;

    public List<EstudianteProyecto> listEstudianteProyecto(Integer idActividad){
        return estudianteProyectoRepository.findByIdActividad(idActividad);
    }

    public String deleteEstudianteProyecto(Integer idProyecto, String idEstudiante){
        if(estudianteProyectoRepository.existsByIdActividadAndIdEstudiante(idProyecto, idEstudiante)){
            estudianteProyectoRepository.deleteByIdActividadAndIdEstudiante(idProyecto, idEstudiante);
            return "Estudiante de este proyecto eliminado con éxito";
        }else{
            return null;
        }
    }

    public EstudianteProyecto createEstudianteProyecto(EstudianteProyecto estudianteProyecto){
        if(estudianteProyecto.getIdActividad() == null || estudianteProyecto.getIdActividad().toString().trim().isEmpty() ||
            estudianteProyecto.getIdEstudiante() == null || estudianteProyecto.getIdEstudiante().toString().trim().isEmpty()){
            return null;
        }else{
            return estudianteProyectoRepository.save(estudianteProyecto);
        }
    }

}
