package com.tictac.demo.controller;

import com.tictac.demo.entity.Poblacion;
import com.tictac.demo.service.PoblacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/poblacion")
public class PoblacionController {

    @Autowired
    PoblacionService poblacionService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPoblacion(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Poblacion> p = poblacionService.getPoblacion(id);
        if(p.isPresent()){
            return ResponseEntity.ok(p.get());
        }else{
            errorResponse.put("message", "No se encontró ninguna población");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPoblacion(@RequestBody Poblacion poblacion){
        errorResponse.clear();
        Poblacion p = poblacionService.createPoblacion(poblacion);
        if(p != null){
            errorResponse.put("message", "Población creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la población");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePoblacion(@RequestBody Poblacion poblacion){
        errorResponse.clear();
        String message = poblacionService.updatePoblacion(poblacion);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar la población");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePoblacion(@PathVariable Integer id){
        errorResponse.clear();
        String message = poblacionService.deletePoblacion(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la población");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Poblacion> poblacionList(){
        return poblacionService.listPoblacion();
    }
}
