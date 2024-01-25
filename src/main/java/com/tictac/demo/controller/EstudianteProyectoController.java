package com.tictac.demo.controller;

import com.tictac.demo.entity.EstudianteProyecto;
import com.tictac.demo.service.EstudianteProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/estudiante-proyecto")
public class EstudianteProyectoController {

    @Autowired
    EstudianteProyectoService estudianteProyectoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEstudianteProyecto(@PathVariable String id){
        errorResponse.clear();
        Optional<EstudianteProyecto> ep = estudianteProyectoService.getEstudianteProyecto(id);
        if(ep.isPresent()){
            return ResponseEntity.ok(ep.get());
        }else{
            errorResponse.put("message", "No se encontró ningún estudiante en este proyecto");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEstudianteProyecto(@RequestBody EstudianteProyecto estudianteProyecto){
        errorResponse.clear();
        EstudianteProyecto ep = estudianteProyectoService.createEstudianteProyecto(estudianteProyecto);
        if(ep != null){
            errorResponse.put("message", "Estudiante creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el estudiante");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEstudianteProyecto(@RequestBody EstudianteProyecto estudianteProyecto){
        errorResponse.clear();
        String message = estudianteProyectoService.updateEstudianteProyecto(estudianteProyecto);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el estudiante de este proyecto");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEstudianteProyecto(@PathVariable String id){
        errorResponse.clear();
        String message = estudianteProyectoService.deleteEstudianteProyecto(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar al estudiante de este proyecto");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<EstudianteProyecto> listEstudianteProyecto(){
        return estudianteProyectoService.listEstudianteProyecto();
    }



}
