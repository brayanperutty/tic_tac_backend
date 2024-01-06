package com.tictac.demo.service;

import com.tictac.demo.entity.CursoProyecto;
import com.tictac.demo.repository.CursoProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoProyectoService {

    @Autowired
    CursoProyectoRepository cursoProyectoRepository;

    public Optional<CursoProyecto> getCursoProyecto(Integer id){
        return cursoProyectoRepository.findById(id);
    }

    public CursoProyecto createCursoProyecto(CursoProyecto cursoProyecto){
        if(cursoProyecto.getIdProyecto() == null || cursoProyecto.getIdProyecto().toString().trim().isEmpty() ||
            cursoProyecto.getIdActividad() == null || cursoProyecto.getIdActividad().toString().trim().isEmpty()){
            return null;
        }else {
            return cursoProyectoRepository.save(cursoProyecto);
        }
    }

    public String updateCursoProyecto(CursoProyecto cursoProyecto){
        if(cursoProyectoRepository.existsById(cursoProyecto.getGrado())){
            Optional<CursoProyecto> cp = cursoProyectoRepository.findById(cursoProyecto.getGrado());

            cp.get().setIdProyecto(cursoProyecto.getIdProyecto());
            cp.get().setIdActividad(cursoProyecto.getIdActividad());

            cursoProyectoRepository.save(cp.get());
            return "Proyecto del curso actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteCursoProyecto(Integer id){
        if(cursoProyectoRepository.existsById(id)){
            cursoProyectoRepository.deleteById(id);
            return "Proyecto del curso eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<CursoProyecto> listCursoProyecto(){
        return cursoProyectoRepository.findAll();
    }
}
