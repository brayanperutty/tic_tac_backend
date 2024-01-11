package com.tictac.demo.service;

import com.tictac.demo.entity.PlanTrabajo;
import com.tictac.demo.repository.PlanTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanTrabajoService {

    @Autowired
    PlanTrabajoRepository planTrabajoRepository;

    public Optional<PlanTrabajo> getPlanTrabajo(Integer id){
        return planTrabajoRepository.findById(id);
    }

    public PlanTrabajo createPlanTrabajo(PlanTrabajo planTrabajo){
        if(planTrabajo.getIdLinea() == null || planTrabajo.getIdLinea().toString().trim().isEmpty() ||
            planTrabajo.getNombre() == null || planTrabajo.getNombre().trim().isEmpty() ||
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

    public List<PlanTrabajo> listPlanTrabajo(){
        return planTrabajoRepository.findAll();
    }
}
