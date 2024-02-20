package com.tictac.demo.service;

import com.tictac.demo.DTO.planTrabajo.ActividadesPlanDTO;
import com.tictac.demo.DTO.planTrabajo.InfoPlanTrabajoDTO;
import com.tictac.demo.DTO.planTrabajo.PlanTrabajoDTO;
import com.tictac.demo.DTO.planTrabajo.update.ActividadesPlanUpdate;
import com.tictac.demo.DTO.planTrabajo.update.InfoPlanTrabajoUpdate;
import com.tictac.demo.DTO.proyectoAula.update.InfoProyectoUpdate;
import com.tictac.demo.entity.*;
import com.tictac.demo.repository.ActividadPlanRepository;
import com.tictac.demo.repository.DocentePlanTrabajoRepository;
import com.tictac.demo.repository.PlanTrabajoRepository;
import com.tictac.demo.repository.SituacionProblematicaRepository;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class PlanTrabajoService {

    @Autowired
    PlanTrabajoRepository planTrabajoRepository;

    @Autowired
    ActividadPlanRepository actividadPlanRepository;

    @Autowired
    DocentePlanTrabajoRepository docentePlanTrabajoRepository;

    @Autowired
    SituacionProblematicaRepository situacionProblematicaRepository;

    List<Object> planes = new ArrayList<>();

    public List<Object> getPlanTrabajo(Integer id){
        planes.clear();

        Object[] obj = actividadPlanRepository.findProyectoAula(id).get(0);
        Map<String, Object> contenido = new LinkedHashMap<>();
        List<Object> listActividades = new ArrayList<>();

        contenido.put("id", obj[0]);
        contenido.put("nombre_plan", obj[1]);
        contenido.put("anio", obj[2]);
        contenido.put("lecciones_aprendidas", obj[3]);

        contenido.put("situacion", situacionProblematicaRepository.findByIdPlan(Integer.parseInt(obj[0].toString())));

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

    public String createPlanTrabajo(PlanTrabajoDTO planTrabajo){

        PlanTrabajo pt = new PlanTrabajo();
        InfoPlanTrabajoDTO infoPlan = planTrabajo.getInfoPlanTrabajoPPT();
        List<Map<String, ActividadesPlanDTO>> actividades = planTrabajo.getActividades();

        pt.setNombre(infoPlan.getNombrePlanTrabajo());
        pt.setAnio(infoPlan.getAnio());
        pt.setIdLinea(Integer.parseInt(infoPlan.getLineaPPT()));
        planTrabajoRepository.save(pt);


        SituacionProblematica sp = new SituacionProblematica();

        sp.setTitulo(infoPlan.getTitulo());
        sp.setCasos(Integer.parseInt(infoPlan.getCasos()));
        sp.setLinea(Integer.parseInt(infoPlan.getLineaPPT()));
        sp.setFecha(infoPlan.getFecha());
        sp.setDescripcion(infoPlan.getDescripcion());
        situacionProblematicaRepository.save(sp);



        for (Map<String, ActividadesPlanDTO> actividad : actividades){
            for (ActividadesPlanDTO act : actividad.values()){
                ActividadPlan ap = new ActividadPlan();

                ap.setNombre(act.getActividad());
                ap.setIdPlan(pt.getIdPlan());
                ap.setDocenteApoyo(act.getDocentesApoyo());
                ap.setFechaInicio(act.getFechaInicio());
                ap.setFechaFin(act.getFechaFin());
                actividadPlanRepository.save(ap);

            }
        }
        return "Plan de Trabajo PPT creado con éxito";
    }

    public String updatePlanTrabajo(InfoPlanTrabajoUpdate planTrabajo){

        List<ActividadesPlanUpdate> actividades = planTrabajo.getActividades();
        Optional<PlanTrabajo> pt = planTrabajoRepository.findById(planTrabajo.getIdPlan());

        pt.get().setNombre(planTrabajo.getNombrePlanTrabajo());
        pt.get().setAnio(planTrabajo.getAnio());
        pt.get().setIdLinea(Integer.parseInt(planTrabajo.getLineaPPT()));
        planTrabajoRepository.save(pt.get());

        SituacionProblematica sp = situacionProblematicaRepository.findByIdPlan(planTrabajo.getIdPlan());
        sp.setTitulo(planTrabajo.getTitulo());
        sp.setCasos(Integer.parseInt(planTrabajo.getCasos()));
        sp.setLinea(Integer.parseInt(planTrabajo.getLineaPPT()));
        sp.setFecha(planTrabajo.getFecha());
        sp.setDescripcion(planTrabajo.getDescripcion());
        situacionProblematicaRepository.save(sp);

        for (ActividadesPlanUpdate act : actividades){
            Optional<ActividadPlan> ap = actividadPlanRepository.findById(act.getIdActividad());

            ap.get().setNombre(act.getActividad());
            ap.get().setIdPlan(planTrabajo.getIdPlan());
            ap.get().setDocenteApoyo(act.getDocentesApoyo());
            ap.get().setFechaInicio(act.getFechaInicio());
            ap.get().setFechaFin(act.getFechaFin());
            actividadPlanRepository.save(ap.get());

        }
        return "Plan de trabajo actualizado con éxito";
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
