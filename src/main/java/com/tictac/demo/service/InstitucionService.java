package com.tictac.demo.service;

import com.tictac.demo.entity.Ciudad;
import com.tictac.demo.entity.Institucion;
import com.tictac.demo.repository.CiudadRepository;
import com.tictac.demo.repository.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstitucionService {

    @Autowired
    InstitucionRepository institucionRepository;

    @Autowired
    CiudadRepository ciudadRepository;

    Map<String, Integer> datos = new HashMap<>();

    Map<String, Map<String, Object>> rankingInstitucion = new HashMap<>();

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
        if(institucionRepository.findByIdCiudad(id) == null){
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
            Optional<Institucion> inst = institucionRepository.findById(id);
                datos.put("ambiental", inst.get().getNumeroProyectosAmbiental());
                datos.put("sociales", inst.get().getNumeroProyectosSociales());
                datos.put("emprendimiento", inst.get().getNumeroProyectosEmprendimiento());
                datos.put("sexualidad", inst.get().getNumeroProyectosSexualidad());
                datos.put("tic", inst.get().getNumeroProyectosTic());

                return datos;

    }

    public Map<String, Integer> getEstadisticasProyectosMunicipio(Integer id){
        datos.clear();
            listInstitucionByCiudad(id).forEach(inst -> {
                datos.merge("ambiental", inst.getNumeroProyectosAmbiental(), Integer::sum);
                datos.merge("sociales", inst.getNumeroProyectosSociales(), Integer::sum);
                datos.merge("emprendimiento", inst.getNumeroProyectosEmprendimiento(), Integer::sum);
                datos.merge("sexualidad", inst.getNumeroProyectosSexualidad(), Integer::sum);
                datos.merge("tic", inst.getNumeroProyectosTic(), Integer::sum);
            });
            return datos;
    }

    public Map<String, Integer> getEstadisticasHerramientasMunicipio(Integer id){
        datos.clear();
            List<Institucion> instituciones = listInstitucionByCiudad(id);
            instituciones.forEach(inst -> {
                datos.merge("ambiental", inst.getNumeroHerramientasAmbiental(), Integer::sum);
                datos.merge("sociales", inst.getNumeroHerramientasSociales(), Integer::sum);
                datos.merge("emprendimiento", inst.getNumeroHerramientasEmprendimiento(), Integer::sum);
                datos.merge("sexualidad", inst.getNumeroHerramientasSexualidad(), Integer::sum);
                datos.merge("tic", inst.getNumeroHerramientasTic(), Integer::sum);
            });
            return datos;
    }

    public Map<String, Integer> getEstadisticasProyectosDepartamento(){
        datos.clear();
        institucionRepository.findAll().forEach(inst -> {
            datos.merge("ambiental", inst.getNumeroProyectosAmbiental(), Integer::sum);
            datos.merge("sociales", inst.getNumeroProyectosSociales(), Integer::sum);
            datos.merge("emprendimiento", inst.getNumeroProyectosEmprendimiento(), Integer::sum);
            datos.merge("sexualidad", inst.getNumeroProyectosSexualidad(), Integer::sum);
            datos.merge("tic", inst.getNumeroProyectosTic(), Integer::sum);
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

    public Map<String, Map<String, Object>> rankingProyectosInstitucionMunicipio(Integer idMunicipio){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findProyectosByMunicipio(idMunicipio);
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new HashMap<>();

            datosInstitucion.put("Proyectos realizados", inst[2]);
            datosInstitucion.put("Municipio", inst[1]);

            rankingInstitucion.put((String)inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Proyectos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }

    public Map<String, Map<String, Object>> rankingProyectosInstitucionDepartamento(){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findProyectosByDepartamento();
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new HashMap<>();

            datosInstitucion.put("Proyectos realizados", inst[2]);
            datosInstitucion.put("Municipio", inst[1]);

            rankingInstitucion.put((String)inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Proyectos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }

    public Map<String, Map<String, Object>> rankingHerramientasInstitucionMunicipio(Integer idMunicipio){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findProyectosByMunicipio(idMunicipio);
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new HashMap<>();

            datosInstitucion.put("Herramientas realizadas", inst[2]);
            datosInstitucion.put("Municipio", inst[1]);

            rankingInstitucion.put((String)inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Herramientas realizadas"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }

    public Map<String, Map<String, Object>> rankingHerramientasInstitucionDepartamento(){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findProyectosByDepartamento();
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new HashMap<>();

            datosInstitucion.put("Herramientas realizadas", inst[2]);
            datosInstitucion.put("Municipio", inst[1]);

            rankingInstitucion.put((String)inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Herramientas realizadas"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }
}
