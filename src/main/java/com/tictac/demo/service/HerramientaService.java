package com.tictac.demo.service;

import com.tictac.demo.DTO.herramienta.HerramientaDTO;
import com.tictac.demo.DTO.herramienta.update.HerramientaUpdate;
import com.tictac.demo.entity.*;
import com.tictac.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class HerramientaService {

    @Autowired
    HerramientaRepository herramientaRepository;

    @Autowired
    TemaRepository temaRepository;

    @Autowired
    MomentoRepository momentoRepository;

    @Autowired
    ProcesoRepository procesoRepository;

    @Autowired
    RecursoProcesoRepository recursoProcesoRepository;

    @Autowired
    PoblacionHerramientaRepository poblacionHerramientaRepository;

    List<Object> infoHerramienta = new ArrayList<>();

    Map<String, Object> herramientaCompleta = new LinkedHashMap<>();
    
    public String createHerramienta(HerramientaDTO herramienta){

        //Creamos primeramente el tema
        Tema t = new Tema();
        t.setNombre(herramienta.getTema());
        t.setIdLinea(herramienta.getLineaPPT());
        t.setIdCompetencia(herramienta.getCompetencias());
        temaRepository.save(t);

        //Segundo: creamos la información básica de la herramienta
        Herramienta h = new Herramienta();
        h.setIdTema(t.getIdTema());
        h.setDocenteAutor(herramienta.getIdDocente());
        h.setNombreHerramienta(herramienta.getNombre());
        h.setObjetivos(herramienta.getObjetivo());
        h.setVisibilidad(herramienta.getVisibilidad());
        h.setEstado("Pendiente");
        h.setRecomendacion(herramienta.getRecomendaciones());
        Date fechaActual = new Date();
        h.setFechaCreacion(fechaActual);
        herramientaRepository.save(h);
        if(herramienta.getObjetivo() != null){
            herramienta.getPoblaObjetivo().forEach(po -> {
                PoblacionHerramienta ph = new PoblacionHerramienta();
                ph.setIdHerramienta(h.getIdHerramienta());
                ph.setIdPoblacion(po);
                poblacionHerramientaRepository.save(ph);
            });
        }

        herramienta.getMomentos().forEach(m ->{
            Momento momento = new Momento();
            momento.setIdHerramienta(h.getIdHerramienta());
            momento.setNombre(m.getNombre());
            momento.setDescripcion(m.getDescripcion());
            momentoRepository.save(momento);

                if(m.getProcesos() != null){

                    m.getProcesos().forEach(p ->{
                        Proceso proceso = new Proceso();
                        proceso.setIdMomento(momento.getIdMomento());
                        proceso.setDescripcion(p.getDescripcion());
                        proceso.setTiempo(p.getTiempo());
                        procesoRepository.save(proceso);

                            if(p.getRecursos() != null){

                                    p.getRecursos().forEach(r ->{
                                        RecursoProceso rp = new RecursoProceso();
                                        rp.setIdProceso(proceso.getIdProceso());
                                        rp.setIdRecurso(r);
                                        recursoProcesoRepository.save(rp);
                                    });

                            }

                    });
                }

        });

        return "Herramienta creada con éxito";
    }

    public String deleteHerramienta(Integer id){
        if(herramientaRepository.existsById(id)){
            herramientaRepository.deleteById(id);
            return "herramienta eliminada con éxito";
        }else {
            return null;
        }
    }

    @Transactional
    public String updateHerramienta(HerramientaUpdate herramienta){
        //Creamos primeramente el tema
        Optional<Tema> t = temaRepository.findById(herramienta.getIdTema());
        t.get().setNombre(herramienta.getTema());
        t.get().setIdLinea(herramienta.getLineaPPT());
        t.get().setIdCompetencia(herramienta.getIdCompetencia());
        temaRepository.save(t.get());

        //Segundo: creamos la información básica de la herramienta
        Optional<Herramienta> h = herramientaRepository.findById(herramienta.getIdHerramienta());
        h.get().setIdTema(t.get().getIdTema());
        h.get().setNombreHerramienta(herramienta.getNombre());
        h.get().setObjetivos(herramienta.getObjetivo());
        h.get().setVisibilidad(herramienta.getVisibilidad());
        h.get().setEstado(h.get().getEstado());
        h.get().setRecomendacion(herramienta.getRecomendaciones());
        Date fechaActual = new Date();
        h.get().setFechaCreacion(fechaActual);
        herramientaRepository.save(h.get());

        herramienta.getPoblaObjetivo().forEach(po -> {
            List<PoblacionHerramienta> ph = poblacionHerramientaRepository.findByIdHerramienta(h.get().getIdHerramienta());
            for (PoblacionHerramienta poblacionHerramienta : ph) {
                poblacionHerramientaRepository.deleteByIdHerramientaAndIdPoblacion(h.get().getIdHerramienta(), poblacionHerramienta.getIdPoblacion());
                PoblacionHerramienta poh = new PoblacionHerramienta();
                poh.setIdHerramienta(h.get().getIdHerramienta());
                poh.setIdPoblacion(po);
                poblacionHerramientaRepository.save(poh);
            }
        });


        herramienta.getMomentos().forEach(m ->{
            Optional<Momento> momento = momentoRepository.findById(m.getIdMomento());
            momento.get().setNombre(m.getNombre());
            momento.get().setDescripcion(m.getDescripcion());
            momentoRepository.save(momento.get());

            if(m.getProcesos() != null){

                m.getProcesos().forEach(p ->{
                    Optional<Proceso> proceso = procesoRepository.findById(p.getIdProceso());
                    proceso.get().setDescripcion(p.getDescripcion());
                    proceso.get().setTiempo(p.getTiempo());
                    procesoRepository.save(proceso.get());

                    if(p.getRecursos() != null){

                        p.getRecursos().forEach(r ->{
                            recursoProcesoRepository.deleteByIdProcesoAndIdRecurso(p.getIdProceso(), r);
                            RecursoProceso rp = new RecursoProceso();
                            rp.setIdProceso(p.getIdProceso());
                            rp.setIdRecurso(r);
                            recursoProcesoRepository.save(rp);
                        });

                    }

                });
            }

        });
        return "Herramienta actualizada con éxito";
    }

    public Map<String, Object> getHerramientaById(Integer idHerramienta) {
        infoHerramienta.clear();
        herramientaCompleta.clear();

        //Traemos el objeto herramienta
        List<Object[]> h = herramientaRepository.findHerramientaById(idHerramienta);
        Object[] herramienta = h.get(0);

        //Aquí almacenamos una a una la información básica de la herramienta
        Map<String, Object> infoBasicaHerramienta = new LinkedHashMap<>();
        Map<String, Object> contenidoInfoBasica = new LinkedHashMap<>();

        infoBasicaHerramienta.put("idHerramienta", herramienta[0]);
        infoBasicaHerramienta.put("nombre_herramienta", herramienta[1]);
        infoBasicaHerramienta.put("poblacion_objetivo", herramienta[2]);
        infoBasicaHerramienta.put("tema", herramienta[3]);
        infoBasicaHerramienta.put("objetivos", herramienta[4]);
        infoBasicaHerramienta.put("competencia", herramienta[5]);
        infoBasicaHerramienta.put("recomendacion", herramienta[6]);
        infoBasicaHerramienta.put("idTema", herramienta[7]);
        infoBasicaHerramienta.put("idPoblacion", herramienta[8]);
        infoBasicaHerramienta.put("lineaPPT", herramienta[9]);
        infoBasicaHerramienta.put("idCompentencia", herramienta[10]);
        infoBasicaHerramienta.put("visibilidad", herramienta[11]);

        contenidoInfoBasica.put("informacion", infoBasicaHerramienta);

        //Aquí almacenamos los momentos de la herramienta
        List<Object[]> momentos = herramientaRepository.findMomentosByHerramienta(Integer.parseInt(herramienta[0].toString()));

        List<Object> infoMomento = new ArrayList<>();

        //Recorremos los momentos de la herramienta
        momentos.forEach(m -> {

            //Aquí almacenamos el listado de los momentos de la herramienta por separado
            List<Object[]> procesos = herramientaRepository.findProcesosByMomento(Integer.parseInt(m[1].toString()));


            List<Object> infoProceso = new ArrayList<>();
            Map<String, Object> momento = new LinkedHashMap<>();
            Map<String, Object> nombreMomento = new LinkedHashMap<>();
            //Aquí validamos si el momento viene sin procesos
            if (procesos.isEmpty()) {
                nombreMomento.put("id_momento", m[1]);
                nombreMomento.put("nombre", m[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " ") + " " + m[3].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
                momento.put("momento_"+m[0], nombreMomento);
                infoMomento.add(momento);
            }else{

                    nombreMomento.put("id_momento", m[1]);
                    nombreMomento.put("nombre_momento", m[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " ") + " " + m[3].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
                    momento.put("momento_"+m[0], nombreMomento);
                    infoMomento.add(momento);

                    //Recorremos los procesos del momento correspondiente
                    procesos.forEach(p -> {

                            Map<String, Object> proceso = new LinkedHashMap<>();

                            //Aquí almacenamos el nombre del proceso
                            Map<String, Object> nombreProceso = new HashMap<>();
                            nombreProceso.put("id_proceso_"+p[0], p[1]);
                            nombreProceso.put("nombre_proceso",  p[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));

                            //Aquí almacenamos el tiempo del proceso
                            Map<String, Object> infoDuracion = new HashMap<>();
                            infoDuracion.put("tiempo", p[3]);

                            //Aquí almacenamos el listado de los recursos del proceso
                            List<Object> recursos = new ArrayList<>();

                            //Aquí separamos los recursos del proceso
                            List<Object[]> rec = herramientaRepository.findRecursosByProceso(Integer.parseInt(p[1].toString()));

                            //Recorremos los recursos del proceso
                            rec.forEach(r -> {
                                Map<String, Object> recurso = new LinkedHashMap<>();
                                recurso.put("idRecurso", r[1]);
                                recurso.put("nombre", r[0]);
                                recursos.add(recurso);
                            });
                            nombreProceso.put("recursos",recursos);
                            nombreProceso.put("duracion", infoDuracion);
                            List<Object> listProcesos = new ArrayList<>();
                            listProcesos.add(nombreProceso);
                            proceso.put("proceso_"+p[0], listProcesos);
                            infoProceso.add(proceso);
                            //Aquí alcenamos el listado de la información total del proceso





                    });
                    nombreMomento.put("procesos", infoProceso);
                }

            });
        contenidoInfoBasica.put("momentos", infoMomento);

        return contenidoInfoBasica;
    }

    public List<Object> getAllHerramientas() {
        infoHerramienta.clear();

        herramientaRepository.findAllHerramientas().forEach(h -> {

            Map<String, Object> datosHerramienta = new HashMap<>();

            datosHerramienta.put("nombre_de_herramienta", "Herramienta " + h[0] + " - " + h[1]);
            datosHerramienta.put("poblacion_objetivo", h[2]);
            datosHerramienta.put("tema", h[3]);
            datosHerramienta.put("objetivos", h[4]);
            datosHerramienta.put("competencia", h[5]);
            datosHerramienta.put("recurso", h[6]);
            infoHerramienta.add(datosHerramienta);
        });
        return infoHerramienta;
    }

    public Map<String, Object> getTotalHerramientas(){
        herramientaCompleta.clear();

        Object[] obj = herramientaRepository.findTotalHerramientas().get(0);

        herramientaCompleta.put("Ambiental", obj[0]);
        herramientaCompleta.put("Sexualidad", obj[1]);
        herramientaCompleta.put("Sociales", obj[2]);
        herramientaCompleta.put("Emprendimiento", obj[3]);
        herramientaCompleta.put("TIC", obj[4]);

        return herramientaCompleta;
    }

    //Servicios de filtrado
    public List<Object> getHerramientasInstitucionPublicoFiltro(Integer idInstitucion, String idLinea, String anio){
        infoHerramienta.clear();
        if(idLinea.equals("null") || idLinea.isEmpty()){
            return getListHerramientaAno(idInstitucion, Integer.parseInt(anio));
        }else if(anio.equals("null") || idLinea.isEmpty()){
            return getListHerramientaLinea(idInstitucion, Integer.parseInt(idLinea));
        }else{
            herramientaRepository.findHerramientasInstitucionPublicoFiltro(idInstitucion, Integer.parseInt(idLinea), Integer.parseInt(anio)).forEach(h -> {
                Map<String, Object> contenido = new LinkedHashMap<>();

                contenido.put("id", h[0]);
                contenido.put("nombre_herramienta", h[1]);
                contenido.put("poblacion_objetivo", h[2]);
                contenido.put("tema", h[3]);
                contenido.put("objetivos", h[4]);
                contenido.put("competencia", h[5]);
                contenido.put("recurso", h[6]);
                infoHerramienta.add(contenido);
            });
        }

        return infoHerramienta;
    }
    public List<Object> getListHerramientaAno(Integer idInstitucion, Integer anio){
        infoHerramienta.clear();

        herramientaRepository.findHerramientasInstitucionPublicoFiltroAno(idInstitucion, anio).forEach(h -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }

    public List<Object> getListHerramientaPrivadoAno(Integer idInstitucion, Integer anio){
        infoHerramienta.clear();

        herramientaRepository.findHerramientasInstitucionPrivadoFiltroAno(idInstitucion, anio).forEach(h -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }
    public List<Object> getListHerramientaLinea(Integer idInstitucion, Integer idLinea){
        infoHerramienta.clear();

        herramientaRepository.findHerramientasInstitucionPublicoFiltroLinea(idInstitucion, idLinea).forEach(h -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }

    public List<Object> getListHerramientaPrivadoLinea(Integer idInstitucion, Integer idLinea){
        infoHerramienta.clear();

        herramientaRepository.findHerramientasInstitucionPrivadoFiltroLinea(idInstitucion, idLinea).forEach(h -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }

    public List<Object> getListHerramientaPrivadoEstado(Integer idInstitucion, String estado){
        infoHerramienta.clear();

        herramientaRepository.findHerramientasInstitucionPublicoFiltroEstado(idInstitucion, estado).forEach(h -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }

    public List<Object> getListHerramientaPrivadoEstadoLinea(Integer idInstitucion, String estado ,Integer idLinea){
        infoHerramienta.clear();

        herramientaRepository.findHerramientasInstitucionPublicoFiltroEstadoLinea(idInstitucion, estado, idLinea).forEach(h -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }

    public List<Object> getListHerramientaPrivadoEstadoAnio(Integer idInstitucion, String estado, Integer anio){
        infoHerramienta.clear();

        herramientaRepository.findHerramientasInstitucionPublicoFiltroEstadoAnio(idInstitucion, estado, anio).forEach(h -> {
            Map<String, java.lang.Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }

    public List<Object> getListHerramientaPrivadoAnioLinea(Integer idInstitucion, Integer idLinea, Integer anio){
        infoHerramienta.clear();
        herramientaRepository.findHerramientasInstitucionPublicoFiltroAnioLinea(idInstitucion, idLinea, anio).forEach(h -> {
            Map<String, java.lang.Object> contenido = new LinkedHashMap<>();

            contenido.put("id", h[0]);
            contenido.put("nombre_herramienta", h[1]);
            contenido.put("poblacion_objetivo", h[2]);
            contenido.put("tema", h[3]);
            contenido.put("objetivos", h[4]);
            contenido.put("competencia", h[5]);
            contenido.put("recurso", h[6]);
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }

    public List<Object> getHerramientaInstitucionPrivadoFiltro(Integer idInstitucion, String idLinea, String anio, String estado){
        infoHerramienta.clear();
        if(idLinea.equals("null") && anio.equals("null")){
            return getListHerramientaPrivadoEstado(idInstitucion, estado);
        }else if(idLinea.equals("null") && estado.equals("null")){
            return getListHerramientaPrivadoAno(idInstitucion, Integer.parseInt(anio));
        }else if(estado.equals("null") && anio.equals("null")){
            return getListHerramientaPrivadoLinea(idInstitucion, Integer.parseInt(idLinea));
        }else if(anio.equals("null")){
            return getListHerramientaPrivadoEstadoLinea(idInstitucion, estado, Integer.parseInt(idLinea));
        }else if(idLinea.equals("null")){
            return getListHerramientaPrivadoEstadoAnio(idInstitucion, estado, Integer.parseInt(anio));
        }else if(estado.equals("null")){
            return getListHerramientaPrivadoAnioLinea(idInstitucion, Integer.parseInt(idLinea), Integer.parseInt(anio));
        }else{
            herramientaRepository.findHerramientasInstitucionPrivadoFiltro(idInstitucion, Integer.parseInt(idLinea), Integer.parseInt(anio), estado).forEach(h -> {
                Map<String, Object> contenido = new LinkedHashMap<>();

                contenido.put("id", h[0]);
                contenido.put("nombre_herramienta", h[1]);
                contenido.put("poblacion_objetivo", h[2]);
                contenido.put("tema", h[3]);
                contenido.put("objetivos", h[4]);
                contenido.put("competencia", h[5]);
                contenido.put("recurso", h[6]);
                infoHerramienta.add(contenido);
            });
        }

        return infoHerramienta;
    }


}
