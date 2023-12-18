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
        return cursoProyectoRepository.save(cursoProyecto);
    }

    public void deleteCursoProyecto(Integer id){
        cursoProyectoRepository.deleteById(id);
    }

    public List<CursoProyecto> listCursoProyecto(){
        return cursoProyectoRepository.findAll();
    }
}
