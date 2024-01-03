package com.tictac.demo.service;

import com.tictac.demo.entity.Ciudad;
import com.tictac.demo.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    CiudadRepository ciudadRepository;

    public Optional<Ciudad> getCiudad(Integer id) {
        return ciudadRepository.findById(id);
    }
    public Ciudad saveCiudad(Ciudad ciudad){
        return ciudadRepository.save(ciudad);
    }
    public void deleteCiudad(Integer id){
        ciudadRepository.deleteById(id);
    }

    public List<Ciudad> listCiudad(){
        return ciudadRepository.findAll();
    }

    public Integer getIdCiudadByNombre(String nombre){
        Ciudad ciudad = ciudadRepository.findByNombre(nombre);

        if(ciudad != null){
            return ciudad.getIdCiudad();
        }
        return null;
    }
}
