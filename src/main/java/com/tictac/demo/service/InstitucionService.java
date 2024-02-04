package com.tictac.demo.service;

import com.tictac.demo.entity.Ciudad;
import com.tictac.demo.entity.Institucion;
import com.tictac.demo.repository.CiudadRepository;
import com.tictac.demo.repository.HerramientaRepository;
import com.tictac.demo.repository.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.JobKOctets;
import java.util.*;

@Service
public class InstitucionService {

    @Autowired
    InstitucionRepository institucionRepository;

    @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    HerramientaRepository herramientaRepository;

    @Autowired
    DocenteService docenteService;

    Map<String, Integer> datos = new HashMap<>();

    Map<String, Object> datosTodo = new LinkedHashMap<>();

    Map<String, Object> rankingInstitucion = new LinkedHashMap<>();

    List<Object> listHerramientas = new ArrayList<>();

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


    //Servicios personalizados
    public Map<String, Object> listInstitucionHerramientasByCiudad(Integer id){
        datosTodo.clear();
        datos.clear();
        List<Object> listDatos = new ArrayList<>();
        Map<String, Object> instituciones = new LinkedHashMap<>();

        institucionRepository.findByIdCiudad(id).forEach(inst -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            datos.merge("ambiental", Integer.parseInt(inst[5].toString()), Integer::sum);
            datos.merge("sociales", Integer.parseInt(inst[7].toString()), Integer::sum);
            datos.merge("emprendimiento", Integer.parseInt(inst[8].toString()), Integer::sum);
            datos.merge("sexualidad", Integer.parseInt(inst[6].toString()), Integer::sum);
            datos.merge("tic", Integer.parseInt(inst[9].toString()), Integer::sum);
            contenido.put("id", inst[0]);
            contenido.put("nombre_institucion", inst[1]);
            contenido.put("herramientas", inst[3]);
            contenido.put("proyectos", inst[4]);
            datosTodo.put("municipio", inst[2]);
            listDatos.add(contenido);
        });
        instituciones.put("listado", listDatos);
        datosTodo.put("estadisticas", datos);
        datosTodo.put("instituciones", listDatos);
        datosTodo.put("ranking_docentes", docenteService.rankingDocentesHerramientasMunicipio(id));
        datosTodo.put("ranking_instituciones", rankingHerramientasInstitucionMunicipio(id));

        return datosTodo;
    }
    public Map<String, Object> listInstitucionProyectosByCiudad(Integer id){
        datosTodo.clear();
        datos.clear();

        List<Object> listDatos = new ArrayList<>();
        Map<String, Object> instituciones = new LinkedHashMap<>();

        institucionRepository.findByIdCiudad(id).forEach(inst -> {

            Map<String, Object> contenido = new LinkedHashMap<>();

            datos.merge("ambiental", Integer.parseInt(inst[5].toString()), Integer::sum);
            datos.merge("sociales", Integer.parseInt(inst[7].toString()), Integer::sum);
            datos.merge("emprendimiento", Integer.parseInt(inst[8].toString()), Integer::sum);
            datos.merge("sexualidad", Integer.parseInt(inst[6].toString()), Integer::sum);
            datos.merge("tic", Integer.parseInt(inst[9].toString()), Integer::sum);
            contenido.put("id", inst[0]);
            contenido.put("nombre_institucion", inst[1]);
            contenido.put("herramientas", inst[3]);
            contenido.put("proyectos", inst[4]);
            datosTodo.put("municipio", inst[2]);
            listDatos.add(contenido);
        });
        instituciones.put("listado", listDatos);
        datosTodo.put("estadisticas", datos);
        datosTodo.put("instituciones", listDatos);
        datosTodo.put("ranking_docentes", docenteService.rankingDocentesProyectosMunicipio(id));
        datosTodo.put("ranking_instituciones", rankingProyectosInstitucionMunicipio(id));

        return datosTodo;
    }

    //Estadísticas
    public Map<String, Object> getEstadisticasHerramientasInstitucion(Integer id){

        Optional<Institucion> inst = institucionRepository.findById(id);
        if (inst.isPresent()) {
            Map<String, Object> datosHerramientas = new HashMap<>();
            datosHerramientas.put("estadisticas", "herramientas");
            datosHerramientas.put("ambiental", inst.get().getNumeroHerramientasAmbiental());
            datosHerramientas.put("sociales", inst.get().getNumeroHerramientasSociales());
            datosHerramientas.put("emprendimiento", inst.get().getNumeroHerramientasEmprendimiento());
            datosHerramientas.put("sexualidad", inst.get().getNumeroHerramientasSexualidad());
            datosHerramientas.put("tic", inst.get().getNumeroHerramientasTic());

            return datosHerramientas;
        }else
            return null;
    }
    public Map<String, Object> getEstadisticasProyectosInstitucion(Integer id){

            Optional<Institucion> inst = institucionRepository.findById(id);

                Map<String, Object> datosProyectos = new HashMap<>();
                datosProyectos.put("estadisticas", "proyectos");
                datosProyectos.put("ambiental", inst.get().getNumeroProyectosAmbiental());
                datosProyectos.put("sociales", inst.get().getNumeroProyectosSociales());
                datosProyectos.put("emprendimiento", inst.get().getNumeroProyectosEmprendimiento());
                datosProyectos.put("sexualidad", inst.get().getNumeroProyectosSexualidad());
                datosProyectos.put("tic", inst.get().getNumeroProyectosTic());

                return datosProyectos;
    }
    public Map<String, Object> getEstadisticasContenidosInstitucion(Integer id){

        Object[] obj = institucionRepository.findEstadisticasContenidos(id).get(0);

            Map<String, Object> datosContenidos = new HashMap<>();
            datosContenidos.put("estadisticas", "contenidos");
            datosContenidos.put("ambiental", Integer.parseInt(obj[0].toString()));
            datosContenidos.put("sexualidad", Integer.parseInt(obj[1].toString()));
            datosContenidos.put("sociales", Integer.parseInt(obj[2].toString()));
            datosContenidos.put("emprendimiento", Integer.parseInt(obj[3].toString()));
            datosContenidos.put("tic", Integer.parseInt(obj[4].toString()));

        return datosContenidos;
    }
    public Map<String, Integer> getEstadisticasProyectosDepartamento(){

        institucionRepository.findAll().forEach(inst -> {
            datos.merge("ambiental", inst.getNumeroProyectosAmbiental(), Integer::sum);
            datos.merge("sociales", inst.getNumeroProyectosSociales(), Integer::sum);
            datos.merge("emprendimiento", inst.getNumeroProyectosEmprendimiento(), Integer::sum);
            datos.merge("sexualidad", inst.getNumeroProyectosSexualidad(), Integer::sum);
            datos.merge("tic", inst.getNumeroProyectosTic(), Integer::sum);
        });

        return datos;
    }
    public List<Object> getEstadisticasInstitucion(Integer id){
        listHerramientas.clear();
        listHerramientas.add(getEstadisticasHerramientasInstitucion(id));
        listHerramientas.add(getEstadisticasProyectosInstitucion(id));
        listHerramientas.add(getEstadisticasContenidosInstitucion(id));
        Map<String, Object> docentesHerramientas = new LinkedHashMap<>();
            docentesHerramientas.put("docentes_herramientas", docenteService.rankingDocentesHerramientasInstitucion(id));
        Map<String, Object> docentesProyectos = new LinkedHashMap<>();
            docentesProyectos.put("docentes_proyectos", docenteService.rankingDocentesProyectosInstitucion(id));
        Map<String, Object> docentesContenidos = new LinkedHashMap<>();
            docentesContenidos.put("docentes_contenidos", docenteService.rankingDocentesContenidosInstitucion(id));
        listHerramientas.add(docentesHerramientas);
        listHerramientas.add(docentesContenidos);
        listHerramientas.add(docentesProyectos);

        return listHerramientas;
    }


    //Servicios de ranking
    public Map<String, Object> rankingProyectosInstitucionMunicipio(Integer idMunicipio){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findProyectosByMunicipio(idMunicipio);
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new LinkedHashMap<>();
            datosInstitucion.put("nombre_institucion", inst[1]);
            datosInstitucion.put("proyectos_realizados", inst[2]);

            rankingInstitucion.put("puesto_" + inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }
    public Map<String, Object> rankingProyectosInstitucionDepartamento(){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findProyectosByDepartamento();
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new LinkedHashMap<>();
            datosInstitucion.put("nombre_institucion", inst[1]);
            datosInstitucion.put("municipio", inst[2]);
            datosInstitucion.put("proyectos_realizados", inst[3]);

            rankingInstitucion.put("puesto_" + inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }
    public Map<String, Object> rankingHerramientasInstitucionMunicipio(Integer idMunicipio){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findHerramientasByMunicipio(idMunicipio);
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new LinkedHashMap<>();
            datosInstitucion.put("nombre_institucion", inst[1]);
            datosInstitucion.put("herramientas_realizadas", inst[2]);

            rankingInstitucion.put("puesto_" + inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }
    public Map<String, Object> rankingHerramientasInstitucionDepartamento(){
        rankingInstitucion.clear();
        List<Object[]> results = institucionRepository.findHerramientasByDepartamento();
        results.forEach(inst -> {
            Map<String, Object> datosInstitucion = new LinkedHashMap<>();
            datosInstitucion.put("nombre_institucion", inst[1]);
            datosInstitucion.put("municipio", inst[2]);
            datosInstitucion.put("herramientas_realizadas", inst[3]);


            rankingInstitucion.put("puesto_" + inst[0], datosInstitucion);
        });
        rankingInstitucion = rankingInstitucion.entrySet().stream()
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingInstitucion;
    }
    public Map<String, Object> getHerramientasByInstitucion(Integer idInstitucion){
        listHerramientas.clear();
        datosTodo.clear();

        institucionRepository.findHerramientasByInstitucion(idInstitucion).forEach(h -> {

            Map<String, Object> herramienta = new LinkedHashMap<>();
            herramienta.put("id", h[0]);
            herramienta.put("nombre_herramienta", h[1]);
            herramienta.put("poblacion_objetivo", h[2]);
            herramienta.put("tema", h[3]);
            herramienta.put("objetivos", h[4]);
            herramienta.put("competencia", h[5]);
            datosTodo.put("nombre_institucion", h[6]);
            listHerramientas.add(herramienta);
        });

        datosTodo.put("herramientas", listHerramientas);

        return datosTodo;
    }
}
