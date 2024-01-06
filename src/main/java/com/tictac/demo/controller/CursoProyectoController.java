package com.tictac.demo.controller;

import com.tictac.demo.entity.CursoProyecto;
import com.tictac.demo.service.CursoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/curso-proyecto")
public class CursoProyectoController {

    @Autowired
    CursoProyectoService cursoProyectoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCursoProyecto(@PathVariable Integer id){
        errorResponse.clear();
        Optional<CursoProyecto> cursoProyecto = cursoProyectoService.getCursoProyecto(id);
        if(cursoProyecto.isPresent()){
            return ResponseEntity.ok(cursoProyecto.get());
        }else{
            errorResponse.put("message", "No se encontró ningún proyecto del curso con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCursoProyecto(@RequestBody CursoProyecto cursoProyecto){
        errorResponse.clear();
        CursoProyecto cp = cursoProyectoService.createCursoProyecto(cursoProyecto);
        if(cp != null){
            errorResponse.put("message", "Proyecto del curso creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
         errorResponse.put("message", "Hubo un error al crear la ciudad");
         return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCursoProyecto(@RequestBody CursoProyecto cursoProyecto){
        errorResponse.clear();
        String message = cursoProyectoService.updateCursoProyecto(cursoProyecto);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el proyecto del curso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCursoProyecto(@PathVariable Integer id){
        errorResponse.clear();
        String message = cursoProyectoService.deleteCursoProyecto(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al eliminar el proyecto del curso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<CursoProyecto> listCursoProyecto(){
        return cursoProyectoService.listCursoProyecto();
    }
}
