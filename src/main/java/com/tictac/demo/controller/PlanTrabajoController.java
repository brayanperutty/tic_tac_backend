package com.tictac.demo.controller;

import com.tictac.demo.entity.PlanTrabajo;
import com.tictac.demo.service.PlanTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plan-trabajo")
public class PlanTrabajoController {

    @Autowired
    PlanTrabajoService planTrabajoService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public PlanTrabajo getPlanTrabajo(@PathVariable Integer id){
        Optional<PlanTrabajo> planTrabajo = planTrabajoService.getPlanTrabajo(id);

        return planTrabajo.orElse(null);
    }

    @PostMapping("/create")
    @ResponseBody
    public PlanTrabajo createPlanTrabajo(@RequestBody PlanTrabajo planTrabajo){

        if(planTrabajo.getIdPlan() == null){
            return planTrabajoService.createPlanTrabajo(planTrabajo);
        }else {
            return planTrabajoService.editPlanTrabajo(planTrabajo);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlanTrabajo(@PathVariable Integer id){
        planTrabajoService.deletePlanTrabajo(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<PlanTrabajo> listPlanTrabajo(){
        return planTrabajoService.listPlanTrabajo();
    }
}
