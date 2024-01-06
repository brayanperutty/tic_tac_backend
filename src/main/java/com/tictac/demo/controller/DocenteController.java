package com.tictac.demo.controller;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDocente(@PathVariable String id){
        Optional<Docente> docente = docenteService.getDocente(id);
        if(docente.isPresent()){
            return ResponseEntity.ok(docente.get());
        }else{
            errorResponse.put("message", "No se encontró ningún docente con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveDocente(@RequestBody Docente docente){
        errorResponse.clear();
        Docente doc = docenteService.saveDocente(docente);
        if(doc != null){
            errorResponse.put("message", "Docente creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el docente");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDocente(@RequestBody Docente docente){
        errorResponse.clear();
        String message = docenteService.updateDocente(docente);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar al docente");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocente(@PathVariable String id){
        errorResponse.clear();
        String message = docenteService.deleteDocente(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar al docente");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Docente> listDocente(){
        return docenteService.listDocente();
    }
}
