package com.tictac.demo.controller;

import com.tictac.demo.entity.ActividadPlan;
import com.tictac.demo.service.ActividadPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/actividad-plan")
public class ActividadPlanController {

    @Autowired
    ActividadPlanService actividadPlanService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getActividadPlan(@PathVariable Integer id){
        Optional<ActividadPlan> actividadPlan = actividadPlanService.getActividadPlan(id);
        if(actividadPlan.isPresent()){
            return ResponseEntity.ok(actividadPlan.get());
        }else{
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "No se encontró ninguna Actividad con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createActividadPlan(@RequestBody ActividadPlan actividadPlan){
        ActividadPlan act = actividadPlanService.createActividadPlan(actividadPlan);
        Map<String, String> errorResponse = new HashMap<>();

        if(act != null){
            errorResponse.put("message", "Actividad creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la actividad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteActividadPlan(@PathVariable Integer id){
        Map<String, String> errorResponse = new HashMap<>();
        String message = actividadPlanService.deleteActividadPlan(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la actividad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/get-by-idplan/{idPlan}")
    public ResponseEntity<?> getByIdPlan(@PathVariable Integer idPlan){
        List<ActividadPlan> actividadPlan = actividadPlanService.getByIdPlan(idPlan);
        Map<String, String> errorResponse = new HashMap<>();
        if(actividadPlan.isEmpty()){
            errorResponse.put("message", "No se encontró ninguna actividad con ese idPlan");
            return ResponseEntity.badRequest().body(errorResponse);
        }else{
            return ResponseEntity.ok(actividadPlan);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ActividadPlan> listActividadPlan(){
        return actividadPlanService.listActividadPlan();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateActividadPlan(@RequestBody ActividadPlan actividadPlan){
        String message = actividadPlanService.updateActividadPlan(actividadPlan);
        Map<String, String> errorResponse = new HashMap<>();
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar la actividad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
