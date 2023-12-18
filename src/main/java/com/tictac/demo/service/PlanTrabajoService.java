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
        return planTrabajoRepository.save(planTrabajo);
    }

    public void deletePlanTrabajo(Integer id){
        planTrabajoRepository.deleteById(id);
    }

    public List<PlanTrabajo> listPlanTrabajo(){
        return planTrabajoRepository.findAll();
    }
}
