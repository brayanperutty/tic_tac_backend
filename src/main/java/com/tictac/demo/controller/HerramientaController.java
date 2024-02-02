package com.tictac.demo.controller;

import com.tictac.demo.entity.Herramienta;
import com.tictac.demo.service.HerramientaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/herramienta")
public class HerramientaController {

    @Autowired
    HerramientaService herramientaService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{idHerramienta}")
    public ResponseEntity<?> getHerramienta(@PathVariable Integer idHerramienta){
        return ResponseEntity.ok(herramientaService.getHerramientaById(idHerramienta));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHerramienta(@RequestBody Herramienta herramienta){
        errorResponse.clear();
        Herramienta h = herramientaService.createHerramienta(herramienta);
        if(h != null){
            errorResponse.put("message", "Herramienta creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHerramienta(@RequestBody Herramienta herramienta){
        errorResponse.clear();
        String message = herramientaService.updateHerramienta(herramienta);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo uno error al actualizar la herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHerramienta(@PathVariable Integer id){
        errorResponse.clear();
        String message = herramientaService.deleteHerramienta(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/by-lineatransversal/{idLineaTransversal}")
    public ResponseEntity<?> getHerramientaByLineaTransversal(@PathVariable Integer idLineaTransversal){
        return ResponseEntity.ok(herramientaService.getHerramientaByLineaTransversal(idLineaTransversal));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllHerramientas(){
        return ResponseEntity.ok(herramientaService.getAllHerramientas());
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalHerramientas(){
        return  ResponseEntity.ok(herramientaService.getTotalHerramientas());
    }
}
