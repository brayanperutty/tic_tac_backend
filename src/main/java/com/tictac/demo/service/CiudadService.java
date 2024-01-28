package com.tictac.demo.service;

import com.tictac.demo.entity.Ciudad;
import com.tictac.demo.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    CiudadRepository ciudadRepository;

    Map<String, Integer> data = new HashMap<>();

    public Optional<Ciudad> getCiudad(Integer id) {
        return ciudadRepository.findById(id);
    }

    public Ciudad saveCiudad(Ciudad ciudad){
        if (ciudad.getNombre() == null || ciudad.getNombre().trim().isEmpty()) {
            return null;
        }else{
            return ciudadRepository.save(ciudad);
        }
    }

    public String deleteCiudad(Integer id){
        if (ciudadRepository.existsById(id)) {
            ciudadRepository.deleteById(id);
            return "Ciudad eliminada con éxito";
        } else {
            return null;
        }
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

    public String updateCiudad(Ciudad ciudad) {
        if (ciudadRepository.existsById(ciudad.getIdCiudad())) {
            Optional<Ciudad> c = ciudadRepository.findById(ciudad.getIdCiudad());

            c.get().setNombre(ciudad.getNombre());
            ciudadRepository.save(c.get());
            return "Ciudad actualizada con éxito";
        } else
            return null;
    }
}
