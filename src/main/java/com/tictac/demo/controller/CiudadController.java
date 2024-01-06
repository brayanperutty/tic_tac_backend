package com.tictac.demo.controller;

import com.tictac.demo.entity.Ciudad;
import com.tictac.demo.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCiudad(@PathVariable Integer id) {
        errorResponse.clear();
        Optional<Ciudad> ciudad = ciudadService.getCiudad(id);

        if (ciudad.isPresent()) {
            return ResponseEntity.ok(ciudad.get());
        } else {
            errorResponse.put("message", "No se encontró ninguna ciudad con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveCiudad(@RequestBody Ciudad ciudad) {
        errorResponse.clear();
        Ciudad savedCiudad = ciudadService.saveCiudad(ciudad);
        if (savedCiudad != null) {
            errorResponse.put("message", "Ciudad creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al crear la ciudad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update/{idCiudad}")
    public ResponseEntity<?> updateCiudad(@RequestBody Ciudad ciudad){
        errorResponse.clear();
        String message = ciudadService.updateCiudad(ciudad);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar la ciudad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCiudad(@PathVariable Integer id){
        errorResponse.clear();
        String message = ciudadService.deleteCiudad(id);
        if( message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la ciudad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Ciudad> listCiudad(){
        return ciudadService.listCiudad();
    }
}
