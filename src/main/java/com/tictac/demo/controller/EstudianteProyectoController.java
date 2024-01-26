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

    @GetMapping("/get/{idProyecto}")
    @ResponseBody
    public List<EstudianteProyecto> getEstudianteProyecto(@PathVariable Integer idProyecto){
        return estudianteProyectoService.listEstudianteProyecto(idProyecto);
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

    @DeleteMapping("/delete/{idProyecto}/{idEstudiante}")
    public ResponseEntity<?> deleteEstudianteProyecto(@PathVariable Integer idProyecto, @PathVariable String idEstudiante){
        errorResponse.clear();
        String message = estudianteProyectoService.deleteEstudianteProyecto(idProyecto, idEstudiante);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar al estudiante de este proyecto");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }



}
