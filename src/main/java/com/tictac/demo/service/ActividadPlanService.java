package com.tictac.demo.service;

import com.tictac.demo.entity.ActividadPlan;
import com.tictac.demo.repository.ActividadPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadPlanService {

    @Autowired
    ActividadPlanRepository actividadPlanRepository;


    public Optional<ActividadPlan> getActividadPlan(Integer id){
        return actividadPlanRepository.findById(id);
    }

    public ActividadPlan createActividadPlan(ActividadPlan actividadPlan){
        if(actividadPlan.getIdPlan() == null || (actividadPlan.getIdPlan()).toString().trim().isEmpty() ||
            actividadPlan.getDocenteApoyo() == null || actividadPlan.getDocenteApoyo().trim().isEmpty() ||
            actividadPlan.getNombre() == null || actividadPlan.getNombre().trim().isEmpty() ||
            actividadPlan.getFechaInicio() == null || actividadPlan.getFechaInicio().toString().trim().isEmpty() ||
            actividadPlan.getFechaFin() == null || actividadPlan.getFechaFin().toString().trim().isEmpty() ||
            actividadPlan.getCumplimiento() == null || actividadPlan.getCumplimiento().trim().isEmpty() ||
            actividadPlan.getObservaciones() == null || actividadPlan.getObservaciones().trim().isEmpty()){
            return null;
        }else{
            return actividadPlanRepository.save(actividadPlan);
        }
    }

    public List<ActividadPlan> getByIdPlan(Integer idPlan){
        return actividadPlanRepository.findByIdPlan(idPlan);
    }

    public String updateActividadPlan(ActividadPlan actividadPlan){
        if(actividadPlanRepository.existsById(actividadPlan.getIdActividad())){
            Optional<ActividadPlan> act = actividadPlanRepository.findById(actividadPlan.getIdActividad());

            act.get().setIdPlan(actividadPlan.getIdPlan());
            act.get().setDocenteApoyo(actividadPlan.getDocenteApoyo());
            act.get().setNombre(actividadPlan.getNombre());
            act.get().setFechaInicio(actividadPlan.getFechaInicio());
            act.get().setFechaFin(actividadPlan.getFechaFin());
            act.get().setCumplimiento(actividadPlan.getCumplimiento());
            act.get().setObservaciones(actividadPlan.getObservaciones());

            actividadPlanRepository.save(act.get());
            return "Actividad del plan actualizada con éxito";
        }else {
            return null;
        }
    }

    public String deleteActividadPlan(Integer id){
        if(actividadPlanRepository.existsById(id)){
            actividadPlanRepository.deleteById(id);
            return "Actividad eliminada con éxito";
        }else{
            return null;
        }
    }

    public List<ActividadPlan> listActividadPlan(){
        return actividadPlanRepository.findAll();
    }
}
