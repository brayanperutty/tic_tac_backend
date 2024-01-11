package com.tictac.demo.controller;

import com.tictac.demo.entity.PlanTrabajo;
import com.tictac.demo.service.PlanTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/plan-trabajo")
public class PlanTrabajoController {

    @Autowired
    PlanTrabajoService planTrabajoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPlanTrabajo(@PathVariable Integer id){
        errorResponse.clear();
        Optional<PlanTrabajo> planTrabajo = planTrabajoService.getPlanTrabajo(id);
        if(planTrabajo.isPresent()){
            return ResponseEntity.ok(planTrabajo.get());
        }else{
            errorResponse.put("message", "No se encontró ningún plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPlanTrabajo(@RequestBody PlanTrabajo planTrabajo){
        errorResponse.clear();
        PlanTrabajo pt = planTrabajoService.createPlanTrabajo(planTrabajo);
        if(pt != null){
            errorResponse.put("message", "Plan de trabajo creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePlanTrabajo(@RequestBody PlanTrabajo planTrabajo){
        errorResponse.clear();
        String message = planTrabajoService.updatePlanTrabajo(planTrabajo);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlanTrabajo(@PathVariable Integer id){
        errorResponse.clear();
        String message = planTrabajoService.deletePlanTrabajo(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<PlanTrabajo> listPlanTrabajo(){
        return planTrabajoService.listPlanTrabajo();
    }
}
