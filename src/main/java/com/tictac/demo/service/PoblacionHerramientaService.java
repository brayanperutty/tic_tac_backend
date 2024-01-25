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

    Map<String, String> poblacion = new HashMap<>();
    List<Map<String, String>> listPoblacion = new ArrayList<>();

    public List<Map<String, String>> getPoblacionHerramienta(Integer idHerramienta){
        poblacion.clear();
        listPoblacion.clear();

        List<PoblacionHerramienta> ph = poblacionHerramientaRepository.findByIdHerramienta(idHerramienta);
        for (PoblacionHerramienta poblacionHerramienta : ph) {
            poblacion.put("nombre", ""+poblacionHerramienta.getNombre());
            listPoblacion.add(poblacion);
        }
        return listPoblacion;
    }

    public String deletePoblacionHerramienta(Integer idPoblacion, Integer idHerramienta){
        Optional<PoblacionHerramienta> ph = poblacionHerramientaRepository.findByIdPoblacionAndIdHerramienta(idPoblacion, idHerramienta);
        poblacionHerramientaRepository.deleteById(ph.get().getIdPoblacion());
        return "Población de la herramienta eliminada con éxito";
    }

    public PoblacionHerramienta createPoblacionHerramienta(PoblacionHerramienta poblacionHerramienta){
        if(poblacionHerramienta.getIdPoblacion() == null || poblacionHerramienta.getIdPoblacion().toString().trim().isEmpty() ||
            poblacionHerramienta.getIdHerramienta() == null || poblacionHerramienta.getIdHerramienta().toString().trim().isEmpty() ||
            poblacionHerramienta.getNombre() == null || poblacionHerramienta.getNombre().trim().isEmpty()){
            return null;
        }else{
            return poblacionHerramientaRepository.save(poblacionHerramienta);
        }
    }
}
