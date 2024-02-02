package com.tictac.demo.service;

import com.tictac.demo.entity.ProyectoAula;
import com.tictac.demo.repository.ProyectoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProyectoAulaService {

    @Autowired
    ProyectoAulaRepository proyectoAulaRepository;

    Map<String, Object> datosProyectos = new LinkedHashMap<>();

    public Optional<ProyectoAula> getProyectoAula(Integer id){
        return proyectoAulaRepository.findById(id);
    }

    public ProyectoAula createProyectoAula(ProyectoAula proyectoAula){
        if(proyectoAula.getGrado() == null || proyectoAula.getGrado().toString().trim().isEmpty() ||
            proyectoAula.getIdTema() == null || proyectoAula.getIdTema().toString().trim().isEmpty() ||
            proyectoAula.getDocenteLider() == null || proyectoAula.getDocenteLider().trim().isEmpty() ||
            proyectoAula.getEstado() == null || proyectoAula.getEstado().trim().isEmpty() ||
            proyectoAula.getFechaInicio() == null || proyectoAula.getFechaInicio().toString().trim().isEmpty() ||
            proyectoAula.getFechaFin() == null || proyectoAula.getFechaFin().toString().trim().isEmpty() ||
            proyectoAula.getLeccionesAprendidas() == null || proyectoAula.getLeccionesAprendidas().trim().isEmpty() ||
            proyectoAula.getNombre() == null || proyectoAula.getNombre().trim().isEmpty()){
            return null;
        }else{
            return proyectoAulaRepository.save(proyectoAula);
        }
    }

    public String updateProyectoAula(ProyectoAula proyectoAula){
        if(proyectoAulaRepository.existsById(proyectoAula.getIdProyecto())){
            Optional<ProyectoAula> pa = proyectoAulaRepository.findById(proyectoAula.getIdProyecto());

            pa.get().setGrado(proyectoAula.getGrado());
            pa.get().setIdTema(proyectoAula.getIdTema());
            pa.get().setDocenteLider(proyectoAula.getDocenteLider());
            pa.get().setFechaInicio(proyectoAula.getFechaInicio());
            pa.get().setEstado(proyectoAula.getEstado());
            pa.get().setFechaFin(proyectoAula.getFechaFin());
            pa.get().setLeccionesAprendidas(proyectoAula.getLeccionesAprendidas());
            pa.get().setNombre(proyectoAula.getNombre());
            proyectoAulaRepository.save(pa.get());
            return "Proyecto de aula actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteProyectoAula(Integer id){
        if(proyectoAulaRepository.existsById(id)){
            proyectoAulaRepository.deleteById(id);
            return "Proyecto de aula eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<ProyectoAula> listProyectoAula(){
        return proyectoAulaRepository.findAll();
    }

    public Map<String, Object> getTotalProyectos(){
        datosProyectos.clear();

        Object[] obj = proyectoAulaRepository.findTotalProyectosDeAula().get(0);

        datosProyectos.put("Ambiental", obj[0]);
        datosProyectos.put("Sexualidad", obj[1]);
        datosProyectos.put("Sociales", obj[2]);
        datosProyectos.put("Emprendimiento", obj[3]);
        datosProyectos.put("TIC", obj[4]);

        return datosProyectos;
    }
}
