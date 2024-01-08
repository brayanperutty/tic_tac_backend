package com.tictac.demo.controller;

import com.tictac.demo.entity.LiderLinea;
import com.tictac.demo.service.LiderLineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/lider-linea")
public class LiderLineaController {

    @Autowired
    LiderLineaService liderLineaService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLiderLinea(@PathVariable String id){
        errorResponse.clear();
        Optional<LiderLinea> liderLinea = liderLineaService.getLiderLinea(id);

        if(liderLinea.isPresent()){
            return ResponseEntity.ok(liderLinea.get());
        }else{
            errorResponse.put("message", "No se encontró ningún líder de esta línea con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> saveLiderLinea(@RequestBody LiderLinea liderLinea){
        errorResponse.clear();
        LiderLinea ll = liderLineaService.saveLiderLinea(liderLinea);
        if(ll != null){
            errorResponse.put("message", "Líder de línea creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear al líder de la línea");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLiderLinea(@RequestBody LiderLinea liderLinea){
        errorResponse.clear();
        String message = liderLineaService.updateLiderLinea(liderLinea);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el líder de la línea");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLiderLinea(@PathVariable String id){
        errorResponse.clear();
        String message = liderLineaService.deleteLiderLinea(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el líder de la línea");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<LiderLinea> listLiderLinea(){
        return liderLineaService.listLiderLinea();
    }
}
