package com.tictac.demo.controller;

import com.tictac.demo.entity.DocentePlanTrabajo;
import com.tictac.demo.service.DocentePlanTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/docente-plantrabajo")
public class DocentePlanTrabajoController {

    @Autowired
    DocentePlanTrabajoService docentePlanTrabajoService;

    Map<String, String> errorResponse = new HashMap<>();

    @PostMapping("/create")
    public ResponseEntity<?> createDocentePlanTrabajo(@RequestBody DocentePlanTrabajo docentePlanTrabajo){
        errorResponse.clear();
        DocentePlanTrabajo d = docentePlanTrabajoService.createDocentePlanTrabajo(docentePlanTrabajo);
        if(d != null){
            errorResponse.put("message", "Docente para el plan de trabajo creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el docente para este plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list/{idActividadPlan}")
    @ResponseBody
    public List<DocentePlanTrabajo> listDocentePlanTrabajo(@PathVariable Integer idActividadPlan){
        return docentePlanTrabajoService.listDocentePlanTrabajo(idActividadPlan);
    }

    @DeleteMapping("/delete/{idActividadPlan}/{idDocente}")
    public ResponseEntity<?> deleteDocentePlanTrabajo(@PathVariable Integer idActividadPlan, @PathVariable String idDocente){
        errorResponse.clear();
        String message = docentePlanTrabajoService.deleteDocentePlanTrabajo(idActividadPlan, idDocente);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el docente de este plan de trabajo");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
