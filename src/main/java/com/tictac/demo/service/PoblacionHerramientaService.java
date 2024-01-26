package com.tictac.demo.service;

import com.tictac.demo.entity.PoblacionHerramienta;
import com.tictac.demo.repository.PoblacionHerramientaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PoblacionHerramientaService {

    @Autowired
    PoblacionHerramientaRepository poblacionHerramientaRepository;


    public List<PoblacionHerramienta> listPoblacionHerramienta(Integer idHerramienta){
        return poblacionHerramientaRepository.findByIdHerramienta(idHerramienta);
    }

    public String deletePoblacionHerramienta(Integer idHerramienta, Integer idPoblacion){
        if(poblacionHerramientaRepository.existsByIdHerramientaAndIdPoblacion(idHerramienta, idPoblacion)){
            poblacionHerramientaRepository.deleteByIdHerramientaAndIdPoblacion(idHerramienta,idPoblacion);
            return "Población eliminada con éxito";
        }else{
            return null;
        }
    }

    public PoblacionHerramienta createPoblacionHerramienta(PoblacionHerramienta poblacionHerramienta){
        if(poblacionHerramienta.getIdPoblacion() == null || poblacionHerramienta.getIdPoblacion().toString().trim().isEmpty() ||
            poblacionHerramienta.getIdHerramienta() == null || poblacionHerramienta.getIdHerramienta().toString().trim().isEmpty()){
            return null;
        }else{
            return poblacionHerramientaRepository.save(poblacionHerramienta);
        }
    }
}
