package com.tictac.demo.service;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    PersonaRepository personaRepository;

    Map<String, Map<String, Object>> rankingDocentes = new HashMap<>();

    public Optional<Docente> getDocente(String id) {
        return docenteRepository.findById(id);
    }

    public Docente saveDocente(Docente docente) {
        if (docente.getNumeroProyectosSociales() == null || docente.getNumeroProyectosSociales().toString().trim().isEmpty() ||
                docente.getNumeroProyectosSexualidad() == null || docente.getNumeroProyectosSexualidad().toString().isEmpty() ||
                docente.getNumeroProyectosAmbiental() == null || docente.getNumeroProyectosAmbiental().toString().trim().isEmpty() ||
                docente.getNumeroProyectosEmprendimiento() == null || docente.getNumeroProyectosEmprendimiento().toString().trim().isEmpty() ||
                docente.getNumeroProyectosTic() == null || docente.getNumeroProyectosTic().toString().trim().isEmpty() ||
                docente.getNumeroContenidosSociales() == null || docente.getNumeroContenidosSociales().toString().trim().isEmpty() ||
                docente.getNumeroContenidosSexualidad() == null || docente.getNumeroContenidosSexualidad().toString().trim().isEmpty() ||
                docente.getNumeroContenidosAmbiental() == null || docente.getNumeroContenidosAmbiental().toString().trim().isEmpty() ||
                docente.getNumeroContenidosEmprendimiento() == null || docente.getNumeroContenidosEmprendimiento().toString().trim().isEmpty() ||
                docente.getNumeroContenidosTic() == null || docente.getNumeroContenidosTic().toString().trim().isEmpty() ||
                docente.getNumeroHerramientasSociales() == null || docente.getNumeroHerramientasSociales().toString().trim().isEmpty() ||
                docente.getNumeroHerramientasSexualidad() == null || docente.getNumeroHerramientasSexualidad().toString().trim().isEmpty() ||
                docente.getNumeroHerramientasAmbiental() == null || docente.getNumeroHerramientasAmbiental().toString().trim().isEmpty() ||
                docente.getNumeroHerramientasEmprendimiento() == null || docente.getNumeroHerramientasEmprendimiento().toString().trim().isEmpty() ||
                docente.getNumeroHerramientasTic() == null || docente.getNumeroHerramientasTic().toString().trim().isEmpty()) {
            return null;
        } else {
            return docenteRepository.save(docente);
        }
    }

    public String deleteDocente(String id) {
        if (docenteRepository.existsById(id)) {
            docenteRepository.deleteById(id);
            return "Docente eliminado con éxito";
        } else {
            return null;
        }
    }

    public String updateDocente(Docente docente) {
        if (docenteRepository.existsById(docente.getIdDocente())) {
            Optional<Docente> d = docenteRepository.findById(docente.getIdDocente());

            d.get().setNumeroProyectosSociales(docente.getNumeroProyectosSociales());
            d.get().setNumeroProyectosSexualidad(docente.getNumeroProyectosSexualidad());
            d.get().setNumeroProyectosAmbiental(docente.getNumeroProyectosAmbiental());
            d.get().setNumeroProyectosEmprendimiento(docente.getNumeroProyectosEmprendimiento());
            d.get().setNumeroProyectosTic(docente.getNumeroProyectosTic());
            d.get().setNumeroContenidosSociales(docente.getNumeroContenidosSociales());
            d.get().setNumeroContenidosSexualidad(docente.getNumeroContenidosSexualidad());
            d.get().setNumeroContenidosAmbiental(docente.getNumeroContenidosAmbiental());
            d.get().setNumeroContenidosEmprendimiento(docente.getNumeroContenidosEmprendimiento());
            d.get().setNumeroContenidosTic(docente.getNumeroContenidosTic());
            d.get().setNumeroHerramientasSociales(docente.getNumeroHerramientasSociales());
            d.get().setNumeroHerramientasSexualidad(docente.getNumeroHerramientasSexualidad());
            d.get().setNumeroHerramientasAmbiental(docente.getNumeroHerramientasAmbiental());
            d.get().setNumeroHerramientasEmprendimiento(docente.getNumeroHerramientasEmprendimiento());
            d.get().setNumeroHerramientasTic(docente.getNumeroHerramientasTic());
            docenteRepository.save(d.get());
            return "Docente actualizado con éxito";
        } else {
            return null;
        }
    }

    public List<Docente> listDocente() {
        return docenteRepository.findAll();
    }

    public Map<String, Map<String, Object>> rankingDocentesProyectosDepartamento() {
        rankingDocentes.clear();
        List<Object[]> results = personaRepository.findProyectosByDepartamento();
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Proyectos realizados", docente[5]);
            datosDocente.put("Institución" , docente[3]);
            datosDocente.put("Municipio" , docente[2]);
            datosDocente.put("Línea", docente[4]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);
        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Proyectos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesProyectosMunicipio(Integer idMunicipio) {
        rankingDocentes.clear();
        List<Object[]> results =  personaRepository.findProyectosByMunicipio(idMunicipio);
           results.forEach(docente -> {
                    Map<String, Object> datosDocente = new HashMap<>();

                       datosDocente.put("Proyectos realizados", docente[4]);
                       datosDocente.put("Institución" , docente[2]);
                       datosDocente.put("Línea", docente[3]);

                       rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);

                });
                rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Proyectos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesProyectosInstitucion(Integer idInstitucion){
        rankingDocentes.clear();
        List<Object[]> results =  personaRepository.findProyectosByInstitucion(idInstitucion);
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Proyectos realizados", docente[4]);
            datosDocente.put("Institución" , docente[2]);
            datosDocente.put("Línea", docente[3]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);

        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Proyectos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesHerramientasDepartamento() {
        rankingDocentes.clear();
        List<Object[]> results = personaRepository.findHerramientasByDepartamento();
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Herramientas realizadas", docente[5]);
            datosDocente.put("Institución" , docente[3]);
            datosDocente.put("Municipio" , docente[2]);
            datosDocente.put("Línea", docente[4]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);
        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Herramientas realizadas"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesHerramientasMunicipio(Integer idMunicipio) {
        rankingDocentes.clear();
        List<Object[]> results =  personaRepository.findHerramientasByMunicipio(idMunicipio);
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Herramientas realizadas", docente[4]);
            datosDocente.put("Institución" , docente[2]);
            datosDocente.put("Línea", docente[3]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);

        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Herramientas realizadas"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesHerramientasInstitucion(Integer idInstitucion){
        rankingDocentes.clear();
        List<Object[]> results =  personaRepository.findHerramientasByInstitucion(idInstitucion);
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Herramientas realizados", docente[4]);
            datosDocente.put("Institución" , docente[2]);
            datosDocente.put("Línea", docente[3]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);

        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Herramientas realizadas"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesContenidosDepartamento() {
        rankingDocentes.clear();
        List<Object[]> results = personaRepository.findContenidosByDepartamento();
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Contenidos realizados", docente[5]);
            datosDocente.put("Institución" , docente[3]);
            datosDocente.put("Municipio" , docente[2]);
            datosDocente.put("Línea", docente[4]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);
        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Contenidos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesContenidosMunicipio(Integer idMunicipio) {
        rankingDocentes.clear();
        List<Object[]> results =  personaRepository.findContenidosByMunicipio(idMunicipio);
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Contenidos realizados", docente[4]);
            datosDocente.put("Institución" , docente[2]);
            datosDocente.put("Línea", docente[3]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);

        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Contenidos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        return rankingDocentes;
    }

    public Map<String, Map<String, Object>> rankingDocentesContenidosInstitucion(Integer idInstitucion){
        rankingDocentes.clear();
        List<Object[]> results =  personaRepository.findContenidosByInstitucion(idInstitucion);
        results.forEach(docente -> {
            Map<String, Object> datosDocente = new HashMap<>();

            datosDocente.put("Contenidos realizados", docente[4]);
            datosDocente.put("Institución" , docente[2]);
            datosDocente.put("Línea", docente[3]);

            rankingDocentes.put(docente[0] + " " + docente[1], datosDocente);

        });
        rankingDocentes = rankingDocentes.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -((Integer) entry.getValue().get("Contenidos realizados"))))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        return rankingDocentes;
    }

}
