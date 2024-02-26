package com.tictac.demo.service;

import com.tictac.demo.DTO.contenidoDigital.update.ContenidoDigitalUpdate;
import com.tictac.demo.DTO.experiencia.ByteArrayMultipartFile;
import com.tictac.demo.DTO.contenidoDigital.ContenidoDigitalArchivoDTO;
import com.tictac.demo.DTO.contenidoDigital.ContenidoDigitalDTO;
import com.tictac.demo.entity.ContenidoDigital;
import com.tictac.demo.entity.PoblacionContenidoDigital;
import com.tictac.demo.repository.ContenidoDigitalRepository;
import com.tictac.demo.repository.PoblacionContenidoDigitalRepository;
import com.tictac.demo.util.CloudinaryService;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        contenido.put("idLinea", obj[1]);
        contenido.put("autor", obj[2]);
        contenido.put("fecha_aprobacion", obj[3]);
        contenido.put("recurso", obj[4]);
        contenido.put("recomendacion", obj[5]);
        contenido.put("idContenido", obj[6]);
        contenido.put("estado", obj[7]);
        contenido.put("poblaObjetivo", obj[8]);
        contenido.put("visibilidad", obj[9]);
        contenido.put("descripcion", obj[10]);
        contenido.put("uso", obj[11]);
        contenido.put("lineaPPT", obj[12]);
        return contenido;
    }

    public String gestionContenidoDigital(Integer idContenido, String estado, String recomendacion){

        Optional<ContenidoDigital> cd = contenidoDigitalRepository.findById(idContenido);

        cd.get().setEstado(estado);
        cd.get().setRecomendacion(recomendacion);
        contenidoDigitalRepository.save(cd.get());
        return "Contenido digital gestionado con éxito";
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
        cd.setUso(0);

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

        try{

            MultipartFile multipartFile = new ByteArrayMultipartFile(filename, filename, "application/octet-stream", decodedBytes);

            cd.setNombreContDigital(contenidoDigital.getTitulo());
            cd.setDescripcion(contenidoDigital.getDescripcion());
            cd.setDocenteAutor(contenidoDigital.getIdDocente());
            cd.setIdLinea(contenidoDigital.getLineaPPT());

            cd.setVisibilidad(contenidoDigital.getVisibilidad());
            cd.setEstado("Pendiente");
            cd.setRecurso(cloudinaryService.upload(multipartFile).get("url").toString());
            cd.setUso(0);
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

    public String updateContenidoDigital(ContenidoDigitalUpdate contenidoDigital) throws IOException {

        Optional<ContenidoDigital> cd = contenidoDigitalRepository.findById(contenidoDigital.getIdContenido());

        cd.get().setNombreContDigital(contenidoDigital.getTitulo());
        cd.get().setVisibilidad(contenidoDigital.getVisibilidad());
        cd.get().setIdLinea(contenidoDigital.getLineaPPT());
        cd.get().setDescripcion(contenidoDigital.getDescripcion());
        cd.get().setRecomendacion(contenidoDigital.getRecomendaciones());
        cd.get().setFechaCreacion(cd.get().getFechaCreacion());
        cd.get().setFechaAprobacion(cd.get().getFechaAprobacion());
        cd.get().setUso(cd.get().getUso());
        if(contenidoDigital.getArchivo().equals("")){
            cd.get().setRecurso(contenidoDigital.getUrl());
        }else{
            byte[] decodedBytes = Base64.getDecoder().decode(contenidoDigital.getArchivo());
            String filename = ""+contenidoDigital.getTitulo();

            MultipartFile multipartFile = new ByteArrayMultipartFile(filename, filename, "application/octet-stream", decodedBytes);
            cd.get().setRecurso(cloudinaryService.upload(multipartFile).get("url").toString());
        }
        contenidoDigitalRepository.save(cd.get());

        PoblacionContenidoDigital pc = poblacionContenidoDigitalRepository.findByIdContenidoDigital(contenidoDigital.getIdContenido());

        pc.setIdPoblacion(contenidoDigital.getPoblaObjetivo());
        poblacionContenidoDigitalRepository.save(pc);

        return "Contenido digital actualizado con éxito";
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
            contenido.put("uso", cd[5]);
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
            contenido.put("uso", cd[7]);
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
                contenido.put("uso", cd[7]);
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
            contenido.put("uso", cd[7]);
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
            contenido.put("uso", cd[7]);
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
                contenido.put("uso", cd[5]);
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
            contenido.put("uso", cd[7]);
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
            contenido.put("uso", cd[7]);
            listContenidos.add(contenido);
        });

        return listContenidos;
    }

    public List<Object> rankingContenidoDepartamentoUso(){
        listContenidos.clear();

        contenidoDigitalRepository.rankingDepartamentoHerramientasByUso().forEach(cd -> {
            Map<String, Object> contenido = new LinkedHashMap<>();
            contenido.put("nombre", cd[0]);
            contenido.put("uso", cd[1]);
            listContenidos.add(contenido);
        });
        return listContenidos;
    }

    public List<Object> rankingContenidoDepartamentoUsoFiltro(Integer idLinea){
        listContenidos.clear();

        contenidoDigitalRepository.rankingDepartamentoHerramientasByUsoFiltro(idLinea).forEach(cd -> {
            Map<String, Object> contenido = new LinkedHashMap<>();
            contenido.put("nombre", cd[0]);
            contenido.put("uso", cd[1]);
            listContenidos.add(contenido);
        });
        return listContenidos;
    }

    public String usoContenidoDigital(Integer idContenido){

        Optional<ContenidoDigital> cd = contenidoDigitalRepository.findById(idContenido);

        cd.get().setUso(cd.get().getUso() + 1 );
        contenidoDigitalRepository.save(cd.get());

        return "Uso aplicado con éxito";
    }

}
