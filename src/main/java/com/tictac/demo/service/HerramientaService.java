package com.tictac.demo.service;

import com.tictac.demo.entity.Herramienta;
import com.tictac.demo.repository.HerramientaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HerramientaService {

    @Autowired
    HerramientaRepository herramientaRepository;

    Map<String, Object> herramientaCompleta = new LinkedHashMap<>();

    List<Object> infoHerramienta = new ArrayList<>();
    
    public Herramienta createHerramienta(Herramienta herramienta){
        if(herramienta.getIdTema() == null || herramienta.getIdTema().toString().trim().isEmpty() ||
            herramienta.getDocenteAutor() == null || herramienta.getDocenteAutor().trim().isEmpty() ||
            herramienta.getNombreHerramienta() == null || herramienta.getNombreHerramienta().trim().isEmpty() ||
            herramienta.getObjetivos() == null || herramienta.getObjetivos().trim().isEmpty() ||
            herramienta.getVisibilidad() == null || herramienta.getVisibilidad().toString().trim().isEmpty() ||
            herramienta.getComentarios() == null || herramienta.getComentarios().trim().isEmpty() ||
            herramienta.getEstado() == null || herramienta.getEstado().trim().isEmpty() ||
            herramienta.getRecomendacion() == null || herramienta.getRecomendacion().trim().isEmpty() ||
            herramienta.getFechaAprobacion() == null || herramienta.getFechaAprobacion().toString().trim().isEmpty() ||
            herramienta.getFechaCreacion() == null || herramienta.getFechaCreacion().toString().trim().isEmpty()){
            return null;
        }else{
            return herramientaRepository.save(herramienta);
        }

    }

    public String deleteHerramienta(Integer id){
        if(herramientaRepository.existsById(id)){
            herramientaRepository.deleteById(id);
            return "herramienta eliminada con éxito";
        }else {
            return null;
        }
    }

    public String updateHerramienta(Herramienta herramienta){
        if(herramientaRepository.existsById(herramienta.getIdHerramienta())){
            Optional<Herramienta> h = herramientaRepository.findById(herramienta.getIdHerramienta());

            h.get().setIdTema(herramienta.getIdTema());
            h.get().setDocenteAutor(herramienta.getDocenteAutor());
            h.get().setNombreHerramienta(herramienta.getNombreHerramienta());
            h.get().setObjetivos(herramienta.getObjetivos());
            h.get().setVisibilidad(herramienta.getVisibilidad());
            h.get().setComentarios(herramienta.getComentarios());
            h.get().setEstado(herramienta.getEstado());
            h.get().setRecomendacion(herramienta.getRecomendacion());
            h.get().setFechaAprobacion(herramienta.getFechaAprobacion());
            h.get().setFechaCreacion(herramienta.getFechaCreacion());
            herramientaRepository.save(h.get());
            return "Herramienta actualizada con éxito";
        }else{
            return null;
        }
    }

    public Map<String, Object> getHerramientaById(Integer idHerramienta) {
        herramientaCompleta.clear();


        //Traemos el objeto herramienta
        List<Object[]> h = herramientaRepository.findHerramientaById(idHerramienta);
        Object[] herramienta = h.get(0);

        //Aquí almacenamos una a una la información básica de la herramienta
        Map<String, Object> infoBasicaHerramienta = new HashMap<>();
        infoBasicaHerramienta.put("tema", herramienta[3]);
        infoBasicaHerramienta.put("nombre_herramienta", herramienta[1]);
        infoBasicaHerramienta.put("poblacion_objetivo", herramienta[2]);
        infoBasicaHerramienta.put("objetivos", herramienta[4]);
        infoBasicaHerramienta.put("competencia", herramienta[5]);
        infoBasicaHerramienta.put("visibilidad", herramienta[6]);
        infoBasicaHerramienta.put("estado", herramienta[7]);
        infoBasicaHerramienta.put("recomendacion", herramienta[8]);

        //Aquí almacenamos toda la información básica de la herramienta
        List<Object> listInfoBasicaHerramienta = new ArrayList<>();
        listInfoBasicaHerramienta.add(infoBasicaHerramienta);

        //Aquí almacenamos el listado de la información de la herramienta y los momentos de la herramienta
        Map<String, Object> contenidoHerramienta = new LinkedHashMap<>();
        contenidoHerramienta.put("información_general", listInfoBasicaHerramienta);

        //Aquí almacenamos los momentos de la herramienta
        List<Object[]> momentos = herramientaRepository.findMomentosByHerramienta(Integer.parseInt(herramienta[0].toString()));

        //Aquí almacenamos cada momento de la herramienta
        Map<String, Object> contenidoMomento = new LinkedHashMap<>();

        //Recorremos los momentos de la herramienta
        momentos.forEach(m -> {

            //Aquí almacenamos el listado de los momentos de la herramienta por separado
            List<Object[]> procesos = herramientaRepository.findProcesosByMomento(Integer.parseInt(m[1].toString()));

            //Aquí almacenamos el nombre del proceso
            Map<String, String> nombreMomento = new HashMap<>();

            //Aquí almacenamos la descripción del proceso
            List<Object> infoCompletaMomento = new ArrayList<>();

            //Aquí validamos si el momento viene sin procesos
            if (procesos.isEmpty()) {
                nombreMomento.put("nombre", m[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " ") + " " + m[3].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
                contenidoMomento.put("momento_"+m[0], nombreMomento);
            }else{
                    nombreMomento.put("nombre", m[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " ") + " " + m[3].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
                    //Aquí almacenamos los procesos por separados
                    Map<String, Object> infoCompletaProceso = new LinkedHashMap<>();

                    //Recorremos los procesos del momento correspondiente
                    procesos.forEach(p -> {

                            //Aquí almacenamos el nombre del proceso
                            Map<String, Object> nombreProceso = new HashMap<>();
                            nombreProceso.put("nombre_proceso",  p[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));

                            //Aquí almacenamos el tiempo del proceso
                            Map<String, Object> infoDuracion = new HashMap<>();
                            infoDuracion.put("tiempo", p[3]);

                            //Aquí almacenamos toda la información de los recursos del proceso
                            Map<String, List<Object>> infoCompletaRecursos = new HashMap<>();

                            //Aquí almacenamos el listado de los recursos del proceso
                            List<Object> recursos = new ArrayList<>();

                            //Aquí separamos los recursos del proceso
                            String rec = herramientaRepository.findRecursosByProceso(Integer.parseInt(p[1].toString()));

                            //Recorremos los recursos del proceso
                            if (rec != null) {
                                String[] r = rec.split(", ");
                                for (String s : r) {
                                    recursos.add(s.replaceAll("\\n", " ").replaceAll("\\s+", " "));
                                }
                                infoCompletaRecursos.put("recursos", recursos);
                            }

                            //Aquí alcenamos el listado de la información total del proceso
                            List<Object> listInfoProceso = new ArrayList<>();
                            listInfoProceso.add(nombreProceso);
                            listInfoProceso.add(infoCompletaRecursos);
                            listInfoProceso.add(infoDuracion);

                            infoCompletaProceso.put("proceso_" + p[0], listInfoProceso);

                    });
                    infoCompletaMomento.add(nombreMomento);
                    infoCompletaMomento.add(infoCompletaProceso);
                    contenidoMomento.put("momento_"+m[0], infoCompletaMomento);

                }
            });
            contenidoHerramienta.put("momentos",contenidoMomento);
            herramientaCompleta.put("herramienta_" + herramienta[0], contenidoHerramienta);

        return herramientaCompleta;
    }

    public List<Object> getHerramientaByLineaTransversal(Integer idLineaTransversal) {
        infoHerramienta.clear();
        herramientaRepository.findHerramientaByLinea(idLineaTransversal).forEach(h -> {

            Map<String, Object> datosHerramienta = new LinkedHashMap<>();

            datosHerramienta.put("nombre_de_herramienta", "Herramienta " + h[0] + " - " + h[1]);
            datosHerramienta.put("población_objetivo", h[2]);
            datosHerramienta.put("tema", h[3]);
            datosHerramienta.put("objetivos", h[4].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
            datosHerramienta.put("competencia", h[5]);
            infoHerramienta.add(datosHerramienta);
        });
        return infoHerramienta;
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
        if(idLinea.equals("null")){
            return getListHerramientaAno(idInstitucion, Integer.parseInt(anio));
        }else if(anio.equals("null")){
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
            infoHerramienta.add(contenido);
        });
        return infoHerramienta;
    }
}
