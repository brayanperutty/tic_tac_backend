package com.tictac.demo.service;

import com.tictac.demo.entity.ProyectoAula;
import com.tictac.demo.repository.ActividadProyectoRepository;
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

    public ProyectoAula createProyectoAula(ProyectoAula proyectoAula){
        if(proyectoAula.getGrado() == null || proyectoAula.getGrado().toString().trim().isEmpty() ||
            proyectoAula.getIdTema() == null || proyectoAula.getIdTema().toString().trim().isEmpty() ||
            proyectoAula.getDocenteLider() == null || proyectoAula.getDocenteLider().trim().isEmpty() ||
            proyectoAula.getEstado() == null || proyectoAula.getEstado().trim().isEmpty() ||
            proyectoAula.getFechaInicio() == null || proyectoAula.getFechaInicio().toString().trim().isEmpty() ||
            proyectoAula.getFechaFin() == null || proyectoAula.getFechaFin().toString().trim().isEmpty() ||
            proyectoAula.getLeccionesAprendidas() == null || proyectoAula.getLeccionesAprendidas().trim().isEmpty() ||
            proyectoAula.getNombre() == null || proyectoAula.getNombre().trim().isEmpty()){
            return null;
        }else{
            return proyectoAulaRepository.save(proyectoAula);
        }
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
            List<Object> listActividades = new ArrayList<>();

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
