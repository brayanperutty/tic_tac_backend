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

    public Optional<EstudianteProyecto> getEstudianteProyecto(String id){
        return estudianteProyectoRepository.findById(id);
    }

    public EstudianteProyecto createEstudianteProyecto(EstudianteProyecto estudianteProyecto) {
        if (estudianteProyecto.getIdEstudiante() == null || estudianteProyecto.getIdEstudiante().trim().isEmpty() ||
                estudianteProyecto.getIdProyecto() == null || estudianteProyecto.getIdProyecto().toString().trim().isEmpty()){
            return null;
        }else{
            return estudianteProyectoRepository.save(estudianteProyecto);
        }
    }

    public String updateEstudianteProyecto(EstudianteProyecto estudianteProyecto){
        if(estudianteProyectoRepository.existsById(estudianteProyecto.getIdEstudiante())){
            Optional<EstudianteProyecto> ep = estudianteProyectoRepository.findById(estudianteProyecto.getIdEstudiante());

            ep.get().setIdEstudiante(estudianteProyecto.getIdEstudiante());
            ep.get().setIdProyecto(estudianteProyecto.getIdProyecto());
            estudianteProyectoRepository.save(ep.get());
            return "Estudiante del este proyecto actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteEstudianteProyecto(String id){
        if(estudianteProyectoRepository.existsById(id)){
            estudianteProyectoRepository.deleteById(id);
            return "Estudiante de este proyecto eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<EstudianteProyecto> listEstudianteProyecto(){
        return estudianteProyectoRepository.findAll();
    }

}
