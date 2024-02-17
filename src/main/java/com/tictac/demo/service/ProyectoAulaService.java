package com.tictac.demo.service;

import com.tictac.demo.DTO.proyectoAula.ActividadesProyectoDTO;
import com.tictac.demo.DTO.proyectoAula.ProyectoDTO;
import com.tictac.demo.entity.ActividadProyecto;
import com.tictac.demo.entity.CursoProyecto;
import com.tictac.demo.entity.EstudianteProyecto;
import com.tictac.demo.entity.ProyectoAula;
import com.tictac.demo.repository.ActividadProyectoRepository;
import com.tictac.demo.repository.CursoProyectoRepository;
import com.tictac.demo.repository.EstudianteProyectoRepository;
import com.tictac.demo.repository.ProyectoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            actividadProyectoRepository.findActividadesProyecto(idProyecto).forEach(ap -> {
                Map<String, Object> datosActividades = new LinkedHashMap<>();
                datosActividades.put("id", ap[0]);
                datosActividades.put("nombre_actividad", ap[1]);
                datosActividades.put("descripcion", ap[2]);
                datosActividades.put("cumplimiento", ap[3]);
                listActividades.add(datosActividades);
            });
            contenido.put("actividades", listActividades);
            listProyectos.add(contenido);

        return listProyectos;
    }

    public String createProyectoAula(ProyectoDTO proyectoAula){

        ProyectoAula pa = new ProyectoAula();
        ProyectoAula infoProyecto = proyectoAula.getInfoActividadProyectoPPT();
        List<Map<String, ActividadesProyectoDTO>> actividades = proyectoAula.getActividades();

        pa.setGrado(infoProyecto.getGrado());
        pa.setNombre(infoProyecto.getNombre());
        pa.setEstado("Pendiente");
        pa.setFechaInicio(infoProyecto.getFechaInicio());
        pa.setFechaFin(infoProyecto.getFechaFin());
        pa.setDocenteLider(infoProyecto.getDocenteLider());
        pa.setIdTema(infoProyecto.getIdTema());
        pa.setVisibilidad(infoProyecto.getVisibilidad());
        proyectoAulaRepository.save(pa);

        for (Map<String, ActividadesProyectoDTO> actividad : actividades){
            for (ActividadesProyectoDTO act : actividad.values()){
                ActividadProyecto ap = new ActividadProyecto();
                ap.setNombre(act.getNombre());
                ap.setDescripcion(act.getDescripcion());
                actividadProyectoRepository.save(ap);

                EstudianteProyecto ep = new EstudianteProyecto();
                ep.setIdActividad(ap.getIdActividad());
                ep.setIdEstudiante(act.getIdEstudiante());
                estudianteProyectoRepository.save(ep);

                CursoProyecto cp = new CursoProyecto();
                cp.setIdProyecto(pa.getIdProyecto());
                cp.setIdActividad(ap.getIdActividad());

                cursoProyectoRepository.save(cp);
            }
        }


        return "Proyecto creado con éxito";
    }

    public String updateProyectoAula(ProyectoAula proyectoAula){
        if(proyectoAulaRepository.existsById(proyectoAula.getIdProyecto())){
            Optional<ProyectoAula> pa = proyectoAulaRepository.findById(proyectoAula.getIdProyecto());

            pa.get().setGrado(proyectoAula.getGrado());
            pa.get().setIdTema(proyectoAula.getIdTema());
            pa.get().setDocenteLider(proyectoAula.getDocenteLider());
            pa.get().setFechaInicio(proyectoAula.getFechaInicio());
            pa.get().setEstado(proyectoAula.getEstado());
            pa.get().setFechaFin(proyectoAula.getFechaFin());
            pa.get().setLeccionesAprendidas(proyectoAula.getLeccionesAprendidas());
            pa.get().setNombre(proyectoAula.getNombre());
            proyectoAulaRepository.save(pa.get());
            return "Proyecto de aula actualizado con éxito";
        }else{
            return null;
        }
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
