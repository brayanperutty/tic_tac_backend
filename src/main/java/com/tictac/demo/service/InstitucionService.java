package com.tictac.demo.service;

import com.tictac.demo.entity.Institucion;
import com.tictac.demo.repository.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Institucion> listInstitucionByCiudad(Integer id){
        return institucionRepository.findByCiudad(id);
    }

    public Map<String, Object> getEstadisticasHerramientasInstitucion(Integer id){
        Optional<Institucion> inst = institucionRepository.findById(id);
        Map<String, Object> datos = new HashMap<>();

        datos.put("ambiental", inst.get().getNumero_herramientas_ambiental());
        datos.put("sociales", inst.get().getNumero_herramientas_sociales());
        datos.put("emprendimiento", inst.get().getNumero_herramientas_emprendimiento());
        datos.put("sexualidad", inst.get().getNumero_herramientas_sexualidad());
        datos.put("tic", inst.get().getNumero_herramientas_tic());

        return datos;
    }

    public Map<String, Object> getEstadisticasProyectosInstitucion(Integer id){
        Optional<Institucion> inst = institucionRepository.findById(id);
        Map<String, Object> datos = new HashMap<>();

        datos.put("ambiental", inst.get().getNumero_proyectos_ambiental());
        datos.put("sociales", inst.get().getNumero_proyectos_sociales());
        datos.put("emprendimiento", inst.get().getNumero_proyectos_emprendimiento());
        datos.put("sexualidad", inst.get().getNumero_proyectos_sexualidad());
        datos.put("tic", inst.get().getNumero_proyectos_tic());

        return datos;
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
