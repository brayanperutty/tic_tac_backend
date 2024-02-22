package com.tictac.demo.service;

import com.tictac.demo.DTO.experiencia.ByteArrayMultipartFile;
import com.tictac.demo.DTO.proyectoAula.ActividadesProyectoDTO;
import com.tictac.demo.DTO.proyectoAula.ProyectoDTO;
import com.tictac.demo.DTO.proyectoAula.update.ActividadesProyectoUpdate;
import com.tictac.demo.DTO.proyectoAula.update.InfoProyectoUpdate;
import com.tictac.demo.entity.*;
import com.tictac.demo.repository.*;
import com.tictac.demo.util.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
public class ProyectoAulaService {

    @Autowired
    ProyectoAulaRepository proyectoAulaRepository;

    @Autowired
    ActividadProyectoRepository actividadProyectoRepository;

    @Autowired
    CursoProyectoRepository cursoProyectoRepository;

    @Autowired
    EstudianteProyectoRepository estudianteProyectoRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    EvidenciaProyectoAulaRepository evidenciaProyectoAulaRepository;
    Map<String, Object> datosProyectos = new LinkedHashMap<>();

    List<Object> listProyectos = new ArrayList<>();

    public List<Object> getProyectoAula(Integer idProyecto) {
        datosProyectos.clear();
        listProyectos.clear();
        Object[] obj = proyectoAulaRepository.findProyecto(idProyecto).get(0);

            Map<String, Object> contenido = new LinkedHashMap<>();
            List<Object> listActividades = new ArrayList<>();

            contenido.put("id", obj[0]);
            contenido.put("nombre_proyecto", obj[1]);
            contenido.put("docente_lider", obj[2]);
            contenido.put("tema", obj[3]);
            contenido.put("linea_transversal", obj[4]);
            contenido.put("grado", obj[5]);
            contenido.put("lecciones_aprendidas", obj[6]);
            contenido.put("fecha_inicio", obj[7]);
            contenido.put("fecha_fin", obj[8]);
            contenido.put("estado", obj[9]);
            contenido.put("id_linea", obj[11]);
            contenido.put("id_tema", obj[12]);
            contenido.put("id_grado", obj[13]);
            contenido.put("visibilidad", obj[10]);

            actividadProyectoRepository.findActividadesProyecto(idProyecto).forEach(ap -> {
                Map<String, Object> datosActividades = new LinkedHashMap<>();
                datosActividades.put("id", ap.getIdActividad());
                datosActividades.put("nombre_actividad", ap.getNombre());
                datosActividades.put("descripcion", ap.getDescripcion());
                datosActividades.put("cumplimiento", ap.getCumplimiento());
                datosActividades.put("observaciones", ap.getObservaciones());

                List<String> idEstudiantes = actividadProyectoRepository.listIdEstudiantesActividadProyecto(ap.getIdActividad());
                datosActividades.put("id_estudiantes", idEstudiantes);
                listActividades.add(datosActividades);
            });
            contenido.put("actividades", listActividades);
            listProyectos.add(contenido);

        return listProyectos;
    }

    public String createProyectoAula(ProyectoDTO proyectoAula){

        ProyectoAula pa = new ProyectoAula();
        ProyectoAula infoProyecto = proyectoAula.getInfoActividadProyectoPPT();
        List<ActividadesProyectoDTO> actividades = proyectoAula.getActividades();

        pa.setGrado(infoProyecto.getGrado());
        pa.setNombre(infoProyecto.getNombre());
        pa.setEstado("Pendiente");
        pa.setFechaInicio(infoProyecto.getFechaInicio());
        pa.setFechaFin(infoProyecto.getFechaFin());
        pa.setDocenteLider(infoProyecto.getDocenteLider());
        pa.setIdTema(infoProyecto.getIdTema());
        pa.setVisibilidad(infoProyecto.getVisibilidad());
        proyectoAulaRepository.save(pa);

        for (ActividadesProyectoDTO actividad : actividades){

                ActividadProyecto ap = new ActividadProyecto();
                ap.setNombre(actividad.getNombre());
                ap.setDescripcion(actividad.getDescripcion());
                actividadProyectoRepository.save(ap);

                if(actividad.getIdEstudiante() != null){
                    actividad.getIdEstudiante().forEach(e -> {
                        EstudianteProyecto ep = new EstudianteProyecto();
                        ep.setIdActividad(ap.getIdActividad());
                        ep.setIdEstudiante(e);
                        estudianteProyectoRepository.save(ep);
                    });
                }

                CursoProyecto cp = new CursoProyecto();
                cp.setIdProyecto(pa.getIdProyecto());
                cp.setIdActividad(ap.getIdActividad());

                cursoProyectoRepository.save(cp);

        }
        return "Actividad de apoyo creada con éxito";
    }

    @Transactional
    public String updateProyectoAula(InfoProyectoUpdate proyectoAula) throws IOException {

        ProyectoAula infoProyecto = proyectoAula.getInfoActividadProyectoPPT();
        List<ActividadesProyectoUpdate> actividades = proyectoAula.getActividades();
        Optional<ProyectoAula> pa = proyectoAulaRepository.findById(infoProyecto.getIdProyecto());

        pa.get().setGrado(infoProyecto.getGrado());
        pa.get().setIdTema(infoProyecto.getIdTema());
        pa.get().setDocenteLider(infoProyecto.getDocenteLider());
        pa.get().setFechaInicio(infoProyecto.getFechaInicio());
        pa.get().setEstado(infoProyecto.getEstado());
        pa.get().setFechaFin(infoProyecto.getFechaFin());
        pa.get().setNombre(infoProyecto.getNombre());
        pa.get().setEstado(pa.get().getEstado());

        if(!proyectoAula.getImages().isEmpty() || proyectoAula.getImages() != null){
            for(int i = 0; i < proyectoAula.getImages().size(); i++){
                byte[] decodedBytes = Base64.getDecoder().decode(proyectoAula.getImages().get(i));
                String filename = "evidencia plan trabajo "+i;

                MultipartFile multipartFile = new ByteArrayMultipartFile(filename, filename, "application/octet-stream", decodedBytes);

                EvidenciaProyectoAula epa = new EvidenciaProyectoAula();
                epa.setIdProyecto(infoProyecto.getIdProyecto());
                epa.setRecurso(cloudinaryService.upload(multipartFile).get("url").toString());

                evidenciaProyectoAulaRepository.save(epa);
            }
        }
        proyectoAulaRepository.save(pa.get());

            for (ActividadesProyectoUpdate actividad : actividades) {
                Optional<ActividadProyecto> act = actividadProyectoRepository.findById(actividad.getIdActividad());

                    act.get().setNombre(actividad.getNombre());
                    act.get().setDescripcion(actividad.getDescripcion());
                    actividadProyectoRepository.save(act.get());

                    estudianteProyectoRepository.deleteByIdActividad(actividad.getIdActividad());


                for (String idEstudiante : actividad.getIdEstudiantes()) {
                    EstudianteProyecto est = new EstudianteProyecto();
                    est.setIdActividad(actividad.getIdActividad());
                    est.setIdEstudiante(idEstudiante);
                    estudianteProyectoRepository.save(est);
                }
            }

        return "Proyecto de aula actualizado con éxito";

    }

    public String deleteProyectoAula(Integer id){
        if(proyectoAulaRepository.existsById(id)){
            proyectoAulaRepository.deleteById(id);
            return "Proyecto de aula eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<ProyectoAula> listProyectoAula(){
        return proyectoAulaRepository.findAll();
    }

    public List<Object> listProyectosDocente(String idDocente){
        listProyectos.clear();

        proyectoAulaRepository.listProyectosDocente(idDocente).forEach(p -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", p[0]);
            contenido.put("nombre_proyecto", p[1]);
            contenido.put("docente_lider", p[2]);
            contenido.put("tema", p[3]);
            contenido.put("linea_transversal", p[4]);
            contenido.put("grado", p[5]);

            listProyectos.add(contenido);
        });
        return listProyectos;
    }

    public Map<String, Object> getTotalProyectos(){
        datosProyectos.clear();

        Object[] obj = proyectoAulaRepository.findTotalProyectosDeAula().get(0);

        datosProyectos.put("Ambiental", obj[0]);
        datosProyectos.put("Sexualidad", obj[1]);
        datosProyectos.put("Sociales", obj[2]);
        datosProyectos.put("Emprendimiento", obj[3]);
        datosProyectos.put("TIC", obj[4]);

        return datosProyectos;
    }

    public List<Object> getProyectosInstitucionPublico(Integer idInstitucion){
        listProyectos.clear();

        proyectoAulaRepository.findContenidosInstitucionPublico(idInstitucion).forEach(p-> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", p[0]);
            contenido.put("nombre_proyecto", p[1]);
            contenido.put("docente_lider", p[2]);
            contenido.put("tema", p[3]);
            contenido.put("linea_transversal", p[4]);
            contenido.put("grado", p[5]);

            listProyectos.add(contenido);
        });

        return listProyectos;
    }

    //Servicio de filtrado
    public List<Object> getProyectosInstitucionPublicoFiltro(Integer idInstitucion, String idLinea, String anio){
        listProyectos.clear();

        if(idLinea.trim().isEmpty() || idLinea.equals("null")){
            return getListProyectosAnio(idInstitucion, Integer.parseInt(anio));
        }else if(anio.trim().isEmpty() || anio.equals("null")){
            return getListProyectosLinea(idInstitucion, Integer.parseInt(idLinea));
        }else{
            proyectoAulaRepository.findContenidosInstitucionPublicoFiltro(idInstitucion, Integer.parseInt(idLinea), Integer.parseInt(anio)).forEach(p -> {
                Map<String, Object> contenido = new LinkedHashMap<>();

                contenido.put("id", p[0]);
                contenido.put("nombre_proyecto", p[1]);
                contenido.put("docente_lider", p[2]);
                contenido.put("tema", p[3]);
                contenido.put("linea_transversal", p[4]);
                contenido.put("grado", p[5]);
                listProyectos.add(contenido);
            });
        }
        return listProyectos;
    }
    public List<Object> getListProyectosAnio(Integer idInstitucion, Integer ano) {
        listProyectos.clear();
        proyectoAulaRepository.findContenidosInstitucionPublicoFiltroAno(idInstitucion, ano).forEach(p -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", p[0]);
            contenido.put("nombre_proyecto", p[1]);
            contenido.put("docente_lider", p[2]);
            contenido.put("tema", p[3]);
            contenido.put("linea_transversal", p[4]);
            contenido.put("grado", p[5]);
            listProyectos.add(contenido);
        });
        return listProyectos;
    }
    public List<Object> getListProyectosLinea(Integer idInstitucion, Integer idLinea) {
        listProyectos.clear();
        proyectoAulaRepository.findContenidosInstitucionPublicoFiltroLinea(idInstitucion, idLinea).forEach(p -> {
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", p[0]);
            contenido.put("nombre_proyecto", p[1]);
            contenido.put("docente_lider", p[2]);
            contenido.put("tema", p[3]);
            contenido.put("linea_transversal", p[4]);
            contenido.put("grado", p[5]);
            listProyectos.add(contenido);
        });
        return listProyectos;
    }
}
