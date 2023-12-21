package com.tictac.demo.service;

import com.tictac.demo.entity.Institucion;
import com.tictac.demo.repository.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitucionService {

    @Autowired
    InstitucionRepository institucionRepository;

    public Optional<Institucion> getInstitucion(Integer id){
        return institucionRepository.findById(id);
    }

    public Integer getInstitucionByNombre(String nombre){
        Institucion institucion = institucionRepository.findByNombre(nombre);
        return institucion.getId_institucion();
    }

    public Institucion saveInstitucion(Institucion institucion){
        return institucionRepository.save(institucion);
    }

    public void deleteInstitucion(Integer id){
        institucionRepository.deleteById(id);
    }

    public List<Institucion> listInstitucion(){
        return institucionRepository.findAll();
    }
}
