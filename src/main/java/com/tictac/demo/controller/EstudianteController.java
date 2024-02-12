package com.tictac.demo.controller;

import com.tictac.demo.entity.Estudiante;
import com.tictac.demo.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id]")
    public ResponseEntity<?> getEstudiante(@PathVariable String id){
        errorResponse.clear();
        Optional<Estudiante> e = estudianteService.getEstudiante(id);
        if(e.isPresent()){
            return ResponseEntity.ok(e.get());
        }else{
            errorResponse.put("message", "No se encontró ningún estudiante");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveEstudiante(@RequestBody Estudiante estudiante){
        errorResponse.clear();
        Estudiante e = estudianteService.createEstudiante(estudiante);
        if(e != null){
            errorResponse.put("message", "Estudiante creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el estudiante");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEstudiante(@RequestBody Estudiante estudiante){
        errorResponse.clear();
        String message = estudianteService.updateEstudiante(estudiante);
        if(message != null) {
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el estudiante");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable String id){
        errorResponse.clear();
        String message = estudianteService.deleteEstudiante(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el estudiante");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Estudiante> listEstudiante(){
        return estudianteService.listEstudiante();
    }

    @GetMapping("/list-by-grado/{grado}")
    public ResponseEntity<?> estudiantesByGrado(@PathVariable Integer grado){
        return ResponseEntity.ok(estudianteService.listEstudiantesByGrado(grado));
    }



}
