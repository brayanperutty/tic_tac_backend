package com.tictac.demo.service;

import com.tictac.demo.entity.Institucion;
import com.tictac.demo.repository.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitucionService {

    @Autowired
    InstitucionRepository institucionRepository;

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
