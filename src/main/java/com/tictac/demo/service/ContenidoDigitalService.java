package com.tictac.demo.service;

import com.tictac.demo.entity.ContenidoDigital;
import com.tictac.demo.repository.ContenidoDigitalRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.JobKOctets;
import java.util.*;

@Service
public class ContenidoDigitalService {

    @Autowired
    ContenidoDigitalRepository contenidoDigitalRepository;

    List<Object> listContenidos = new ArrayList<>();

    public Map<String, Object> getContenidoDigital(Integer id){
        Map<String, Object> contenido = new LinkedHashMap<>();
        Object[] obj = contenidoDigitalRepository.getContenidoDigital(id).get(0);
        contenido.put("nombre_contenido", obj[0]);
        contenido.put("linea", obj[1]);
        contenido.put("autor", obj[2]);
        contenido.put("fecha_aprobacion", obj[3]);
        contenido.put("recurso", obj[4]);
        contenido.put("recomendacion", obj[5]);

        return contenido;
    }

    public ContenidoDigital createContenidoDigital(ContenidoDigital contenidoDigital){
        if(contenidoDigital.getDocenteAutor() == null || contenidoDigital.getDocenteAutor().trim().isEmpty() ||
            contenidoDigital.getNombreContDigital() == null || contenidoDigital.getNombreContDigital().trim().isEmpty() ||
            contenidoDigital.getVisibilidad() == null || contenidoDigital.getVisibilidad().toString().trim().isEmpty() ||
            contenidoDigital.getIdLinea() == null || contenidoDigital.getIdLinea().toString().trim().isEmpty() ||
            contenidoDigital.getEstado() == null || contenidoDigital.getEstado().trim().isEmpty() ||
            contenidoDigital.getRecomendacion() == null || contenidoDigital.getRecomendacion().trim().isEmpty() ||
            contenidoDigital.getFechaAprobacion() == null || contenidoDigital.getFechaAprobacion().toString().trim().isEmpty() ||
            contenidoDigital.getFechaCreacion() == null || contenidoDigital.getFechaCreacion().toString().trim().isEmpty()){
            return null;
        }else {
            return contenidoDigitalRepository.save(contenidoDigital);
        }
    }

    public String deleteContenidoDigital(Integer id){
        if(contenidoDigitalRepository.existsById(id)){
            contenidoDigitalRepository.deleteById(id);
            return "Contenido digital eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<ContenidoDigital> listContenidoDigital(){
        return contenidoDigitalRepository.findAll();
    }

    public String updateContenidoDigital(ContenidoDigital contenidoDigital){
        if(contenidoDigitalRepository.existsById(contenidoDigital.getIdContenidoDigital())){
            Optional<ContenidoDigital> conte = contenidoDigitalRepository.findById(contenidoDigital.getIdContenidoDigital());

            conte.get().setDocenteAutor(contenidoDigital.getDocenteAutor());
            conte.get().setNombreContDigital(contenidoDigital.getNombreContDigital());
            conte.get().setVisibilidad(contenidoDigital.getVisibilidad());
            conte.get().setIdLinea(contenidoDigital.getIdLinea());
            conte.get().setEstado(contenidoDigital.getEstado());
            conte.get().setRecomendacion(contenidoDigital.getRecomendacion());
            conte.get().setFechaAprobacion(contenidoDigital.getFechaAprobacion());
            conte.get().setFechaCreacion(contenidoDigital.getFechaCreacion());
            contenidoDigitalRepository.save(conte.get());
            return "Contenido digital actualizado con éxito";
        }else{
            return null;
        }
    }

    public List<Object> getContenidosObservatorio(){
        listContenidos.clear();

        contenidoDigitalRepository.findContenidosObservatorio().forEach(cd ->{

            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", cd[1]);
            contenido.put("autor", cd[0]);
            contenido.put("recomendacion", cd[2]);
            contenido.put("fecha_aprobacion", cd[3]);
            contenido.put("recurso", cd[4]);
            listContenidos.add(contenido);
        });

        return listContenidos;
    }

    public List<Object> getContenidosInstitucionPublico(Integer idInstitucion){
        listContenidos.clear();

        contenidoDigitalRepository.findContenidosInstitucionPublico(idInstitucion).forEach(cd -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", cd[1]);
            contenido.put("autor", cd[0]);
            contenido.put("recomendacion", cd[2]);
            contenido.put("fecha_aprobacion", cd[3]);
            contenido.put("recurso", cd[4]);
            contenido.put("nombre_contenido", cd[5]);
            contenido.put("linea", cd[6]);
            listContenidos.add(contenido);
        });

        return listContenidos;
    }

    //Servicios de filtrado
    public List<Object> getListContenidosInstitucionPublicoFiltro(Integer idInstitucion, String idLinea, String anio){
        listContenidos.clear();
        if(idLinea.equals("null")){
            return getListContenidosAno(idInstitucion, anio);
        }else if(anio.equals("null")){
            return getListContenidosLinea(idInstitucion, idLinea);
        }else{
            contenidoDigitalRepository.findContenidosInstitucionPublicoFiltro(idInstitucion, Integer.parseInt(idLinea), Integer.parseInt(anio)).forEach(cd -> {

                Map<String, Object> contenido = new LinkedHashMap<>();

                contenido.put("id", cd[1]);
                contenido.put("autor", cd[0]);
                contenido.put("recomendacion", cd[2]);
                contenido.put("fecha_aprobacion", cd[3]);
                contenido.put("recurso", cd[4]);
                contenido.put("nombre_contenido", cd[5]);
                contenido.put("linea", cd[6]);
                listContenidos.add(contenido);
            });
        }
        return listContenidos;
    }
    public List<Object> getListContenidosAno(Integer idInstitucion, String anio){
        listContenidos.clear();

        contenidoDigitalRepository.findContenidosInstitucionPublicoFiltroAno(idInstitucion, Integer.parseInt(anio)).forEach(cd -> {

            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", cd[1]);
            contenido.put("autor", cd[0]);
            contenido.put("recomendacion", cd[2]);
            contenido.put("fecha_aprobacion", cd[3]);
            contenido.put("recurso", cd[4]);
            contenido.put("nombre_contenido", cd[5]);
            contenido.put("linea", cd[6]);
            listContenidos.add(contenido);
        });

        return listContenidos;
    }
    public List<Object> getListContenidosLinea(Integer idInstitucion, String idLinea){
        listContenidos.clear();

        contenidoDigitalRepository.findContenidosInstitucionPublicoFiltroLinea(idInstitucion, Integer.parseInt(idLinea)).forEach(cd -> {

            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", cd[1]);
            contenido.put("autor", cd[0]);
            contenido.put("recomendacion", cd[2]);
            contenido.put("fecha_aprobacion", cd[3]);
            contenido.put("recurso", cd[4]);
            contenido.put("nombre_contenido", cd[5]);
            contenido.put("linea", cd[6]);
            listContenidos.add(contenido);
        });

        return listContenidos;
    }



}
