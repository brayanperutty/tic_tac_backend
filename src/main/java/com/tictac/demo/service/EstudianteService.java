package com.tictac.demo.service;

import com.tictac.demo.entity.Estudiante;
import com.tictac.demo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EstudianteService {

  @Autowired
  EstudianteRepository estudianteRepository;

  public Optional<Estudiante> getEstudiante(String id){
    return estudianteRepository.findById(id);
  }

  public Estudiante createEstudiante(Estudiante estudiante){
    if(estudiante.getIdEstudiante() == null || estudiante.getIdEstudiante().trim().isEmpty() ||
            estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty() ||
            estudiante.getGrado() == null || estudiante.getGrado().toString().trim().isEmpty()){
      return null;
    }else{
      return estudianteRepository.save(estudiante);
    }
  }

  public String updateEstudiante(Estudiante estudiante){
    if(estudianteRepository.existsById(estudiante.getIdEstudiante())){
      Optional<Estudiante> e = estudianteRepository.findById(estudiante.getIdEstudiante());

      e.get().setNombre(estudiante.getNombre());
      e.get().setGrado(estudiante.getGrado());
      estudianteRepository.save(e.get());
      return "Estudiante actualizado con éxito";
    }else{
      return null;
    }
  }

  public String deleteEstudiante(String id){
    if(estudianteRepository.existsById(id)){
      estudianteRepository.deleteById(id);
      return "Estudiante eliminado con éxito";
    }else{
      return null;
    }
  }

  public List<Estudiante> listEstudiante(){
    return estudianteRepository.findAll();
  }

  public List<Object> listEstudiantesByGrado(Integer grado){

    List<Object> estudiantes = new ArrayList<>();
    estudianteRepository.listEstudiantesByCurso(grado).forEach(e ->{
      Map<String, Object> estudiante = new LinkedHashMap<>();

      estudiante.put("id", e[0]);
      estudiante.put("nombre", e[1]);
      estudiantes.add(estudiante);

    });

    return estudiantes;
  }

}
