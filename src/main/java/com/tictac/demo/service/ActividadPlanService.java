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
        return actividadPlanRepository.save(actividadPlan);
    }

    public List<ActividadPlan> getByIdPlan(Integer idPlan){
        return actividadPlanRepository.findByIdPlan(idPlan);
    }

    public ActividadPlan editActividadPlan(ActividadPlan actividadPlan){
        Optional<ActividadPlan> act = actividadPlanRepository.findById(actividadPlan.getIdActividad());

        act.get().setIdPlan(actividadPlan.getIdPlan());
        act.get().setDocenteApoyo(actividadPlan.getDocenteApoyo());
        act.get().setNombre(actividadPlan.getNombre());
        act.get().setFechaInicio(actividadPlan.getFechaInicio());
        act.get().setFechaFin(actividadPlan.getFechaFin());
        act.get().setCumplimiento(actividadPlan.getCumplimiento());
        act.get().setObservaciones(actividadPlan.getObservaciones());

        actividadPlanRepository.save(act.get());

        return act.get();
    }


    public void deleteActividadPlan(Integer id){
        actividadPlanRepository.deleteById(id);
    }

    public List<ActividadPlan> listActividadPlan(){
        return actividadPlanRepository.findAll();
    }
}
