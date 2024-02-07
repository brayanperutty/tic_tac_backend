package com.tictac.demo.service;

import com.tictac.demo.entity.Experiencia;
import com.tictac.demo.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExperienciaService {

    @Autowired
    ExperienciaRepository experienciaRepository;

    List<Object> listExperiencias = new ArrayList<>();

    public Experiencia createExperiencia(Experiencia experiencia){
        if(experiencia.getNombre() == null || experiencia.getNombre().trim().isEmpty() ||
            experiencia.getIdLinea() == null || experiencia.getIdLinea().toString().trim().isEmpty() ||
            experiencia.getDescripcion() == null || experiencia.getDescripcion().trim().isEmpty()){
            return null;
        }else{
            return experienciaRepository.save(experiencia);
        }
    }

    public List<Object> listExperiencias(){
        listExperiencias.clear();

        experienciaRepository.listExperiencia().forEach(e -> {
            Map<String, Object> experiencia = new LinkedHashMap<>();

            List<Object> listEvidencias = new ArrayList<>();
            experiencia.put("nombre_experiencia", e[1]);
            experiencia.put("descripcion", e[2]);

            experienciaRepository.listExperienciaEvidencias(Integer.parseInt(e[0].toString())).forEach(ev ->{
                listEvidencias.add(ev[0]);
            });
            experiencia.put("evidencias", listEvidencias);
            listExperiencias.add(experiencia);
        });
        return listExperiencias;
    }

}
