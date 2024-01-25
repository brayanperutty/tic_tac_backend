package com.tictac.demo.controller;

import com.tictac.demo.entity.PoblacionHerramienta;
import com.tictac.demo.service.PoblacionHerramientaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poblacion-herramienta")
public class PoblacionHerramientaController {

    @Autowired
    PoblacionHerramientaService poblacionHerramientaService;

    Map<String, String> errorResponse = new HashMap<>();

    @PostMapping("/create")
    public ResponseEntity<?> createPoblacionHerramienta(@RequestBody PoblacionHerramienta poblacionHerramienta){
        errorResponse.clear();
        PoblacionHerramienta ph = poblacionHerramientaService.createPoblacionHerramienta(poblacionHerramienta);
        if(ph != null){
            errorResponse.put("message", "Poblacion de la herramienta creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la población en esta herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list/{idHerramienta}")
    public List<?> listPoblacionHerramienta(@PathVariable Integer idHerramienta){
        return poblacionHerramientaService.getPoblacionHerramienta(idHerramienta);
    }

    @DeleteMapping("/delete/{idPoblacion}/{idHerramienta}")
    public ResponseEntity<?> deletePoblacionHerramienta(@PathVariable Integer idPoblacion, @PathVariable Integer idHerramienta){
        errorResponse.clear();
        String message = poblacionHerramientaService.deletePoblacionHerramienta(idPoblacion, idHerramienta);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la población de esta herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
