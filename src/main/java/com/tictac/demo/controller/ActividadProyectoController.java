package com.tictac.demo.controller;

import com.tictac.demo.entity.ActividadProyecto;
import com.tictac.demo.service.ActividadProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/actividad-proyecto")
public class ActividadProyectoController {

    @Autowired
    ActividadProyectoService actividadProyectoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getActividadProyecto(@PathVariable Integer id){
        errorResponse.clear();
        Optional<ActividadProyecto> actividadProyecto = actividadProyectoService.getActividadProyecto(id);
        if(actividadProyecto.isPresent()){
            return ResponseEntity.ok(actividadProyecto.get());
        }else{
            errorResponse.put("message", "No se encontró ninguna Actividad con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateActividadProyecto(@RequestBody ActividadProyecto actividadProyecto){
        errorResponse.clear();
        String message = actividadProyectoService.updateActividadProyecto(actividadProyecto);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar la actividad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteActividadProyecto(@PathVariable Integer id){
        errorResponse.clear();
        String message = actividadProyectoService.deleteActividadProyecto(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo uno error al eliminar la actividad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ActividadProyecto> listActividadProyecto(){
        return actividadProyectoService.listActividadProyecto();
    }
}
