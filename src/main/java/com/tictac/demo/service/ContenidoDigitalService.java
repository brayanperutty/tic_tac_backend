package com.tictac.demo.service;

import com.tictac.demo.DTO.ContenidoDigitalArchivoDTO;
import com.tictac.demo.DTO.ContenidoDigitalDTO;
import com.tictac.demo.DTO.ProyectoDTO;
import com.tictac.demo.entity.ContenidoDigital;
import com.tictac.demo.entity.PoblacionContenidoDigital;
import com.tictac.demo.repository.ContenidoDigitalRepository;
import com.tictac.demo.repository.PoblacionContenidoDigitalRepository;
import com.tictac.demo.util.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ContenidoDigitalService {

    @Autowired
    ContenidoDigitalRepository contenidoDigitalRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    PoblacionContenidoDigitalRepository poblacionContenidoDigitalRepository;

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

    public String createContenidoDigitalUrl(ContenidoDigitalDTO contenidoDigital){

        ContenidoDigital cd = new ContenidoDigital();

        cd.setNombreContDigital(contenidoDigital.getTitulo());
        cd.setDescripcion(contenidoDigital.getDescripcion());
        cd.setDocenteAutor(contenidoDigital.getIdDocente());
        cd.setIdLinea(contenidoDigital.getLineaPPT());
        cd.setRecurso(contenidoDigital.getUrl());
        cd.setVisibilidad(contenidoDigital.getVisibilidad());
        cd.setEstado("Pendiente");

        contenidoDigitalRepository.save(cd);

        PoblacionContenidoDigital pcd = new PoblacionContenidoDigital();
        pcd.setIdContenidoDigital(cd.getIdContenidoDigital());
        pcd.setIdPoblacion(contenidoDigital.getPoblaObjetivo());
        poblacionContenidoDigitalRepository.save(pcd);

        return "Contenido Digital creado con éxito";

    }

    public String createContenidoDigitalArchivo(ContenidoDigitalArchivoDTO contenidoDigital){

        ContenidoDigital cd = new ContenidoDigital();

        byte[] decodedBytes = Base64.getDecoder().decode(contenidoDigital.getArchivo());
        String filename = ""+contenidoDigital.getTitulo();

        try(FileOutputStream fos = new FileOutputStream(filename)){
            fos.write(decodedBytes);
            cd.setNombreContDigital(contenidoDigital.getTitulo());
            cd.setDescripcion(contenidoDigital.getDescripcion());
            cd.setDocenteAutor(contenidoDigital.getIdDocente());
            cd.setIdLinea(contenidoDigital.getLineaPPT());

            cd.setVisibilidad(contenidoDigital.getVisibilidad());
            cd.setEstado("Pendiente");
            cd.setRecurso(cloudinaryService.upload((MultipartFile) fos).get("url").toString());
            contenidoDigitalRepository.save(cd);

            PoblacionContenidoDigital pcd = new PoblacionContenidoDigital();
            pcd.setIdContenidoDigital(cd.getIdContenidoDigital());
            pcd.setIdPoblacion(contenidoDigital.getPoblaObjetivo());
            poblacionContenidoDigitalRepository.save(pcd);

            return "Contenido Digital creado con éxito";

        }catch (IOException e){
            e.printStackTrace();
            return "Error al guardar el archivo decodificado";
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

    public List<Object>getListContenidosObservatorioFiltro(String idLinea, String anio) {
        listContenidos.clear();

        if (idLinea.equals("null")) {
            return getListObservatorioAno(anio);
        } else if (anio.equals("null")) {
            return getListObservatorioLinea(idLinea);
        } else {
            contenidoDigitalRepository.getContenidosObservatorioFiltro(Integer.parseInt(idLinea), Integer.parseInt(anio)).forEach(cd -> {

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
    }

    public List<Object> getListObservatorioAno(String anio){
        listContenidos.clear();

        contenidoDigitalRepository.getContenidosObservatorioFiltroAno(Integer.parseInt(anio)).forEach(cd -> {

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
    public List<Object> getListObservatorioLinea(String idLinea){
        listContenidos.clear();

        contenidoDigitalRepository.getContenidosObservatorioFiltroLinea(Integer.parseInt(idLinea)).forEach(cd -> {

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
