package com.tictac.demo.service;

import com.tictac.demo.entity.Poblacion;
import com.tictac.demo.repository.PoblacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoblacionService {

    @Autowired
    PoblacionRepository poblacionRepository;

    public Optional<Poblacion> getPoblacion(Integer idPoblacion){
        return poblacionRepository.findById(idPoblacion);
    }

    public Poblacion createPoblacion(Poblacion poblacion){
        if(poblacion.getNombre() == null || poblacion.getNombre().trim().isEmpty()){
            return null;
        }else{
            return poblacionRepository.save(poblacion);
        }
    }

    public String updatePoblacion(Poblacion poblacion){
        if(poblacionRepository.existsById(poblacion.getIdPoblacion())){
            Optional<Poblacion> p = poblacionRepository.findById(poblacion.getIdPoblacion());

            p.get().setNombre(poblacion.getNombre());
            poblacionRepository.save(p.get());
            return "Población actualizada con éxito";
        }else{
            return null;
        }
    }

    public String deletePoblacion(Integer idPoblacion){
        if(poblacionRepository.existsById(idPoblacion)){
            poblacionRepository.deleteById(idPoblacion);
            return "Poblacion elimanada con éxito";
        }else{
            return null;
        }
    }

    public List<Poblacion> listPoblacion(){
        return poblacionRepository.findAll();
    }
}
