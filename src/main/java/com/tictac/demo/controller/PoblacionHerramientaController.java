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
    @ResponseBody
    public List<?> listPoblacionHerramienta(@PathVariable Integer idHerramienta){
        return poblacionHerramientaService.listPoblacionHerramienta(idHerramienta);
    }

    @DeleteMapping("/delete/{idHerramienta}/{idPoblacion}")
    public ResponseEntity<?> deletePoblacionHerramienta(@PathVariable Integer idHerramienta, @PathVariable Integer idPoblacion){
        errorResponse.clear();
        String message = poblacionHerramientaService.deletePoblacionHerramienta(idHerramienta, idPoblacion);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar esta población de esta herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
