package com.tictac.demo.controller;

import com.tictac.demo.DTO.planTrabajo.PlanTrabajoDTO;
import com.tictac.demo.DTO.planTrabajo.update.InfoPlanTrabajoUpdate;
import com.tictac.demo.service.PlanTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan-trabajo")
public class PlanTrabajoController {

    @Autowired
    PlanTrabajoService planTrabajoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPlanTrabajo(@PathVariable Integer id){
        errorResponse.clear();
        List<Object> planTrabajo = planTrabajoService.getPlanTrabajo(id);
        if(!planTrabajo.isEmpty()){
            return ResponseEntity.ok(planTrabajo);
        }else{
            errorResponse.put("message", "No se encontró ningún plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPlanTrabajo(@RequestBody PlanTrabajoDTO planTrabajo){
        errorResponse.clear();
        String message = planTrabajoService.createPlanTrabajo(planTrabajo);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePlanTrabajo(@RequestBody InfoPlanTrabajoUpdate planTrabajo) throws IOException {
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

    @GetMapping("/list/{idInstitucion}")
    public ResponseEntity<?> listPlanTrabajo(@PathVariable Integer idInstitucion){
        return ResponseEntity.ok(planTrabajoService.listPlanTrabajo(idInstitucion));
    }

    @PatchMapping("/gestion/{idPlan}/{estado}/{recomendaciones}")
    public ResponseEntity<?> gestionPlanTrabajo(@PathVariable Integer idPlan, @PathVariable String estado, @PathVariable String recomendaciones){
        return ResponseEntity.ok(planTrabajoService.gestionPlanTrabajo(idPlan, estado, recomendaciones));
    }

    @PatchMapping("/cierre/{idPlan}/{cierre}")
    public ResponseEntity<?> cierrePlanTrabajo(@PathVariable Integer idPlan, @PathVariable Boolean cierre){
        return ResponseEntity.ok(planTrabajoService.cierrePlanTrabajo(idPlan, cierre));
    }
}
