package com.tictac.demo.service;

import com.tictac.demo.entity.PlanTrabajo;
import com.tictac.demo.repository.ActividadPlanRepository;
import com.tictac.demo.repository.PlanTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlanTrabajoService {

    @Autowired
    PlanTrabajoRepository planTrabajoRepository;

    @Autowired
    ActividadPlanRepository actividadPlanRepository;

    List<Object> planes = new ArrayList<>();

    public List<Object> getPlanTrabajo(Integer id){
        planes.clear();
        Map<String, Object> datos = new LinkedHashMap<>();

        Object[] obj = actividadPlanRepository.findProyectoAula(id).get(0);
        Map<String, Object> contenido = new LinkedHashMap<>();
        List<Object> listActividades = new ArrayList<>();

        contenido.put("id", obj[0]);
        contenido.put("nombre_plan", obj[1]);
        contenido.put("anio", obj[2]);
        contenido.put("lecciones_aprendidas", obj[3]);

        actividadPlanRepository.getListActividadPlan(id).forEach(ap -> {
            Map<String, Object> datosActividades = new LinkedHashMap<>();

            datosActividades.put("id", ap[0]);
            datosActividades.put("nombre_actividad", ap[1]);
            datosActividades.put("fecha_inicio", ap[2]);
            datosActividades.put("fecha_fin", ap[3]);
            datosActividades.put("nombre_docente", ap[4]);
            datosActividades.put("cumplimiento", ap[5]);
            listActividades.add(datosActividades);
        });

        contenido.put("actividades", listActividades);
        planes.add(contenido);

        return planes;
    }

    public PlanTrabajo createPlanTrabajo(PlanTrabajo planTrabajo){
        if(planTrabajo.getIdLinea() == null || planTrabajo.getIdLinea().toString().trim().isEmpty() ||
            planTrabajo.getNombre() == null || planTrabajo.getNombre().trim().isEmpty() ||
            planTrabajo.getEstado() == null || planTrabajo.getEstado().trim().isEmpty() ||
            planTrabajo.getAnio() == null || planTrabajo.getAnio().trim().isEmpty() ||
            planTrabajo.getLeccionesAprendidas() == null || planTrabajo.getLeccionesAprendidas().trim().isEmpty()){
            return null;
        }else{
            return planTrabajoRepository.save(planTrabajo);
        }
    }

    public String updatePlanTrabajo(PlanTrabajo planTrabajo){
        if(planTrabajoRepository.existsById(planTrabajo.getIdPlan())){
            Optional<PlanTrabajo> pt = planTrabajoRepository.findById(planTrabajo.getIdPlan());

            pt.get().setIdLinea(planTrabajo.getIdLinea());
            pt.get().setNombre(planTrabajo.getNombre());
            pt.get().setEstado(planTrabajo.getEstado());
            pt.get().setAnio(planTrabajo.getAnio());
            pt.get().setLeccionesAprendidas(planTrabajo.getLeccionesAprendidas());

            planTrabajoRepository.save(pt.get());
            return "Plan de trabajo creado con éxito";
        }else{
            return null;
        }
    }

    public String deletePlanTrabajo(Integer id){
        if(planTrabajoRepository.existsById(id)){
            planTrabajoRepository.deleteById(id);
            return "Plan de trabajo eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<Object> listPlanTrabajo(){
        planes.clear();
        planTrabajoRepository.getListPlanTrabajo().forEach(pt ->{
            Map<String, Object> contenido = new LinkedHashMap<>();

            contenido.put("id", pt[0]);
            contenido.put("nombre_plan", pt[1]);
            contenido.put("linea_transversal", pt[2]);
            contenido.put("anio", pt[3]);

            planes.add(contenido);
        });
        return planes;
    }
}
