package com.tictac.demo.controller;

import com.tictac.demo.entity.ActividadPlan;
import com.tictac.demo.service.ActividadPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ActividadPlan createActividadPlan(@RequestBody ActividadPlan actividadPlan){

        if(actividadPlan.getIdActividad() == null){
            return actividadPlanService.createActividadPlan(actividadPlan);
        }else{
            return actividadPlanService.editActividadPlan(actividadPlan);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActividadPlan(@PathVariable Integer id){
        actividadPlanService.deleteActividadPlan(id);
    }

    @GetMapping("/get-by-idplan/{idPlan}")
    @ResponseBody
    public List<ActividadPlan> getByIdPlan(@PathVariable Integer idPlan){
        return actividadPlanService.getByIdPlan(idPlan);
    }
    
    @GetMapping("/list")
    @ResponseBody
    public List<ActividadPlan> listActividadPlan(){
        return actividadPlanService.listActividadPlan();
    }
}
