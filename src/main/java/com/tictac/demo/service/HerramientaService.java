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
        infoBasicaHerramienta.put("Tema", herramienta[3]);
        infoBasicaHerramienta.put("Nombre herramienta", herramienta[1]);
        infoBasicaHerramienta.put("Población objetivo", herramienta[2]);
        infoBasicaHerramienta.put("Objetivos", herramienta[4]);
        infoBasicaHerramienta.put("Competencia", herramienta[5]);
        infoBasicaHerramienta.put("visibilidad", herramienta[6]);
        infoBasicaHerramienta.put("Estado", herramienta[7]);
        infoBasicaHerramienta.put("Recomendacion", herramienta[8]);

        //Aquí almacenamos toda la información básica de la herramienta
        List<Object> listInfoBasicaHerramienta = new ArrayList<>();
        listInfoBasicaHerramienta.add(infoBasicaHerramienta);

        //Aquí almacenamos el listado de la información de la herramienta y los momentos de la herramienta
        Map<String, Object> contenidoHerramienta = new LinkedHashMap<>();
        contenidoHerramienta.put("Información general", listInfoBasicaHerramienta);

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
                nombreMomento.put("Nombre", m[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " ") + " " + m[3].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
                contenidoMomento.put("Momento "+m[0], nombreMomento);
            }else{
                    nombreMomento.put("Nombre", m[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " ") + " " + m[3].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
                    //Aquí almacenamos los procesos por separados
                    Map<String, Object> infoCompletaProceso = new LinkedHashMap<>();

                    //Recorremos los procesos del momento correspondiente
                    procesos.forEach(p -> {

                            //Aquí almacenamos el nombre del proceso
                            Map<String, Object> nombreProceso = new HashMap<>();
                            nombreProceso.put("Nombre proceso",  p[2].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));

                            //Aquí almacenamos el tiempo del proceso
                            Map<String, Object> infoDuracion = new HashMap<>();
                            infoDuracion.put("Tiempo", p[3]);

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
                                infoCompletaRecursos.put("Recursos", recursos);
                            }

                            //Aquí alcenamos el listado de la información total del proceso
                            List<Object> listInfoProceso = new ArrayList<>();
                            listInfoProceso.add(nombreProceso);
                            listInfoProceso.add(infoCompletaRecursos);
                            listInfoProceso.add(infoDuracion);

                            infoCompletaProceso.put("Proceso " + p[0], listInfoProceso);

                    });
                    infoCompletaMomento.add(nombreMomento);
                    infoCompletaMomento.add(infoCompletaProceso);
                    contenidoMomento.put("Momento "+m[0], infoCompletaMomento);

                }
            });
            contenidoHerramienta.put("Momentos",contenidoMomento);
            herramientaCompleta.put("Herramienta " + herramienta[0], contenidoHerramienta);

        return herramientaCompleta;
    }

    public List<Object> getHerramientaByLineaTransversal(Integer idLineaTransversal) {
        infoHerramienta.clear();
        herramientaRepository.findHerramientaByLinea(idLineaTransversal).forEach(h -> {

            Map<String, Object> datosHerramienta = new LinkedHashMap<>();

            datosHerramienta.put("Nombre de herramienta", "Herramienta " + h[0] + " - " + h[1]);
            datosHerramienta.put("Población objetivo", h[2]);
            datosHerramienta.put("Tema", h[3]);
            datosHerramienta.put("Objetivos", h[4].toString().replaceAll("\\n", " ").replaceAll("\\s+", " "));
            datosHerramienta.put("Competencia", h[5]);
            datosHerramienta.put("visibilidad", h[6]);
            datosHerramienta.put("Estado", h[7]);
            infoHerramienta.add(datosHerramienta);
        });
        return infoHerramienta;
    }

    public List<Object> getAllHerramientas() {
        infoHerramienta.clear();

        herramientaRepository.findAllHerramientas().forEach(h -> {

            Map<String, Object> datosHerramienta = new HashMap<>();

            datosHerramienta.put("Nombre de herramienta", "Herramienta " + h[0] + " - " + h[1]);
            datosHerramienta.put("Población objetivo", h[2]);
            datosHerramienta.put("Tema", h[3]);
            datosHerramienta.put("Objetivos", h[4]);
            datosHerramienta.put("Competencia", h[5]);
            datosHerramienta.put("visibilidad", h[6]);
            datosHerramienta.put("Estado", h[7]);
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
        System.out.println(obj.toString());

        return herramientaCompleta;
    }
}
