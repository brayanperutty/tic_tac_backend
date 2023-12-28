package com.tictac.demo.controller;

import com.tictac.demo.entity.ActividadPlan;
import com.tictac.demo.service.ActividadPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actividad-plan")
public class ActividadPlanController {

    @Autowired
    ActividadPlanService actividadPlanService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public ActividadPlan getActividadPlan(@PathVariable Integer id){
        Optional<ActividadPlan> actividadPlan = actividadPlanService.getActividadPlan(id);
        return actividadPlan.orElse(null);
    }

    @PostMapping("/create")
    @ResponseBody
    public ActividadPlan createActividadPlan(ActividadPlan actividadPlan){
        return actividadPlanService.createActividadPlan(actividadPlan);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActividadPlan(@PathVariable Integer id){
        actividadPlanService.deleteActividadPlan(id);
    }
    
    @GetMapping("/list")
    @ResponseBody
    public List<ActividadPlan> listActividadPlan(){
        return actividadPlanService.listActividadPlan();
    }
}
