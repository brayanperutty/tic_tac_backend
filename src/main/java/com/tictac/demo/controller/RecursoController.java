package com.tictac.demo.controller;

import com.tictac.demo.entity.Recurso;
import com.tictac.demo.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/recurso")
public class RecursoController {

    @Autowired
    RecursoService recursoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRecurso(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Recurso> recurso = recursoService.getRecurso(id);
        if(recurso.isPresent()){
            return ResponseEntity.ok(recurso.get());
        }else{
            errorResponse.put("message", "No se encontró ningún recurso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveRecurso(@RequestBody Recurso recurso){
        errorResponse.clear();
        Recurso r = recursoService.saveRecurso(recurso);
        if(r != null){
            errorResponse.put("message", "Recurso creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el recurso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRecurso(@RequestBody Recurso recurso){
        errorResponse.clear();
        String message = recursoService.updateRecurso(recurso);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el recurso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecurso(@PathVariable Integer id){
        errorResponse.clear();
        String message = recursoService.deleteRecurso(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el recurso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Recurso> listRecurso(){
        return recursoService.listRecurso();
    }
}
