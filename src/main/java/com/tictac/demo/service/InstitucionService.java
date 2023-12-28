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
        if (institucion != null) {
            return institucion.getIdInstitucion();
        }

        return null;
    }

    public List<Institucion> listInstitucionByCiudad(Integer id){
        return institucionRepository.findByIdCiudad(id);
    }

    public Map<String, Object> getEstadisticasHerramientasInstitucion(Integer id){
        Optional<Institucion> inst = institucionRepository.findById(id);
        Map<String, Object> datos = new HashMap<>();
        if (inst.isPresent()) {

            datos.put("ambiental", inst.get().getNumeroHerramientasAmbiental());
            datos.put("sociales", inst.get().getNumeroHerramientasSociales());
            datos.put("emprendimiento", inst.get().getNumeroHerramientasEmprendimiento());
            datos.put("sexualidad", inst.get().getNumeroHerramientasSexualidad());
            datos.put("tic", inst.get().getNumeroHerramientasTic());

            return datos;
        }else
            return null;
    }

    public Map<String, Object> getEstadisticasProyectosInstitucion(Integer id){
        Optional<Institucion> inst = institucionRepository.findById(id);
        Map<String, Object> datos = new HashMap<>();
        if (inst.isPresent()) {
            datos.put("ambiental", inst.get().getNumeroProyectosAmbiental());
            datos.put("sociales", inst.get().getNumeroProyectosSociales());
            datos.put("emprendimiento", inst.get().getNumeroProyectosEmprendimiento());
            datos.put("sexualidad", inst.get().getNumeroProyectosSexualidad());
            datos.put("tic", inst.get().getNumeroProyectosTic());

            return datos;
        }else
            return null;
    }

    public Map<String, Integer> getEstadisticasProyectosMunicipio(Integer id){
        List<Institucion> instituciones = listInstitucionByCiudad(id);
        Map<String, Integer> datos = new HashMap<>();
        instituciones.forEach(inst -> {
            datos.merge("ambiental", inst.getNumeroProyectosAmbiental(), Integer::sum);
            datos.merge("sociales", inst.getNumeroProyectosSociales(), Integer::sum);
            datos.merge("emprendimiento", inst.getNumeroProyectosEmprendimiento(), Integer::sum);
            datos.merge("sexualidad", inst.getNumeroProyectosSexualidad(), Integer::sum);
            datos.merge("tic", inst.getNumeroProyectosTic(), Integer::sum);
        });

        return datos;
    }

    public Map<String, Integer> getEstadisticasHerramientasMunicipio(Integer id){
        List<Institucion> instituciones = listInstitucionByCiudad(id);
        Map<String, Integer> datos = new HashMap<>();
        instituciones.forEach(inst -> {
            datos.merge("ambiental", inst.getNumeroHerramientasAmbiental(), Integer::sum);
            datos.merge("sociales", inst.getNumeroHerramientasSociales(), Integer::sum);
            datos.merge("emprendimiento", inst.getNumeroHerramientasEmprendimiento(), Integer::sum);
            datos.merge("sexualidad", inst.getNumeroHerramientasSexualidad(), Integer::sum);
            datos.merge("tic", inst.getNumeroHerramientasTic(), Integer::sum);
        });

        return datos;
    }

    public Institucion saveInstitucion(Institucion institucion){
        if(getInstitucionByNombre(institucion.getNombre()) == null) {
            return institucionRepository.save(institucion);
        }

        return null;
    }

    public void deleteInstitucion(Integer id){
        institucionRepository.deleteById(id);
    }

    public List<Institucion> listInstitucion(){
        return institucionRepository.findAll();
    }
}
