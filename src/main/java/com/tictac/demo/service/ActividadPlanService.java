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

    public void deleteActividadPlan(Integer id){
        actividadPlanRepository.deleteById(id);
    }

    public List<ActividadPlan> listActividadPlan(){
        return actividadPlanRepository.findAll();
    }
}
