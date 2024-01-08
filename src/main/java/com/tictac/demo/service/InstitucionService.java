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

    Map<String, Integer> datos = new HashMap<>();

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
        if(institucionRepository.findByIdCiudad(id).isEmpty()){
            return null;
        }else{
            return institucionRepository.findByIdCiudad(id);
        }
    }

    public Map<String, Integer> getEstadisticasHerramientasInstitucion(Integer id){
        datos.clear();
        Optional<Institucion> inst = institucionRepository.findById(id);
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

    public Map<String, Integer> getEstadisticasProyectosInstitucion(Integer id){
        datos.clear();
        if(institucionRepository.existsById(id)){
            Optional<Institucion> inst = institucionRepository.findById(id);
                datos.put("ambiental", inst.get().getNumeroProyectosAmbiental());
                datos.put("sociales", inst.get().getNumeroProyectosSociales());
                datos.put("emprendimiento", inst.get().getNumeroProyectosEmprendimiento());
                datos.put("sexualidad", inst.get().getNumeroProyectosSexualidad());
                datos.put("tic", inst.get().getNumeroProyectosTic());

                return datos;
        }else{
            return null;
       }
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
        if(institucion.getNombre() == null || institucion.getNombre().trim().isEmpty() ||
            institucion.getIdCiudad() == null || institucion.getIdCiudad().toString().trim().isEmpty() ||
                institucion.getNumeroProyectosSociales() == null || institucion.getNumeroProyectosSociales().toString().trim().isEmpty() ||
                institucion.getNumeroProyectosSexualidad() == null || institucion.getNumeroProyectosSexualidad().toString().trim().isEmpty() ||
                institucion.getNumeroProyectosAmbiental() == null || institucion.getNumeroProyectosAmbiental().toString().trim().isEmpty() ||
                institucion.getNumeroProyectosEmprendimiento() == null || institucion.getNumeroProyectosEmprendimiento().toString().trim().isEmpty() ||
                institucion.getNumeroProyectosTic() == null || institucion.getNumeroProyectosTic().toString().trim().isEmpty() ||
                    institucion.getNumeroHerramientasSociales() == null || institucion.getNumeroHerramientasSociales().toString().trim().isEmpty() ||
                    institucion.getNumeroHerramientasSexualidad() == null || institucion.getNumeroHerramientasSexualidad().toString().trim().isEmpty() ||
                    institucion.getNumeroHerramientasAmbiental() == null || institucion.getNumeroHerramientasAmbiental().toString().trim().isEmpty() ||
                    institucion.getNumeroHerramientasEmprendimiento() == null || institucion.getNumeroHerramientasEmprendimiento().toString().trim().isEmpty() ||
                    institucion.getNumeroHerramientasTic() == null || institucion.getNumeroHerramientasTic().toString().trim().isEmpty()) {
            return null;
        }
        return institucionRepository.save(institucion);
    }

    public String updateInstitucion(Institucion institucion){
        if(institucionRepository.existsById(institucion.getIdInstitucion())){
            Optional<Institucion> inst = institucionRepository.findById(institucion.getIdInstitucion());

            inst.get().setNombre(institucion.getNombre());
            inst.get().setIdCiudad(institucion.getIdCiudad());
                inst.get().setNumeroProyectosSociales(institucion.getNumeroProyectosSociales());
                inst.get().setNumeroProyectosSexualidad(institucion.getNumeroProyectosSexualidad());
                inst.get().setNumeroProyectosAmbiental(institucion.getNumeroProyectosAmbiental());
                inst.get().setNumeroProyectosEmprendimiento(institucion.getNumeroProyectosEmprendimiento());
                inst.get().setNumeroProyectosTic(institucion.getNumeroProyectosTic());
                    inst.get().setNumeroHerramientasSociales(institucion.getNumeroHerramientasTic());
                    inst.get().setNumeroHerramientasSexualidad(institucion.getNumeroHerramientasTic());
                    inst.get().setNumeroHerramientasAmbiental(institucion.getNumeroHerramientasTic());
                    inst.get().setNumeroHerramientasEmprendimiento(institucion.getNumeroHerramientasTic());
                    inst.get().setNumeroHerramientasTic(institucion.getNumeroHerramientasTic());
            institucionRepository.save(inst.get());
            return "Institución actualizada con éxito";
        }else{
            return null;
        }
    }

    public String deleteInstitucion(Integer id){
        if(institucionRepository.existsById(id)){
            institucionRepository.deleteById(id);
            return "Institución eliminada con éxito";
        }else{
            return null;
        }
    }

    public List<Institucion> listInstitucion(){
        return institucionRepository.findAll();
    }
}
