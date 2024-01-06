package com.tictac.demo.controller;

import com.tictac.demo.entity.Curso;
import com.tictac.demo.service.CursoService;
import org.apache.commons.collections4.Put;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCurso(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Curso> curso = cursoService.getCurso(id);
        if(curso.isPresent()){
            return ResponseEntity.ok(curso.get());
        }else {
            errorResponse.put("message", "No se encontró ningún curso con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Curso curso){
        errorResponse.clear();
        Curso c = cursoService.createCurso(curso);
        if(c != null){
            errorResponse.put("message", "Curso creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la ciudad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCurso(@RequestBody Curso curso){
        errorResponse.clear();
        String message = cursoService.updateCurso(curso);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el curso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        errorResponse.clear();
        String message = cursoService.deleteCurso(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al eliminar el curso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Curso> listCurso(){
        return cursoService.listCurso();
    }
}
