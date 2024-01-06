package com.tictac.demo.controller;

import com.tictac.demo.entity.ContenidoDigital;
import com.tictac.demo.service.ContenidoDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contenido-digital")
public class ContenidoDigitalController {

    @Autowired
    ContenidoDigitalService contenidoDigitalService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getContenidoDigital(@PathVariable Integer id){
        errorResponse.clear();
        Optional<ContenidoDigital> contenidoDigital = contenidoDigitalService.getContenidoDigital(id);
        if(contenidoDigital.isPresent()){
            return ResponseEntity.ok(contenidoDigital.get());
        }else{
            errorResponse.put("message", "No se encontró ningún contenido digital con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createContenidoDigital(@RequestBody ContenidoDigital contenidoDigital){
        errorResponse.clear();
        ContenidoDigital conte = contenidoDigitalService.createContenidoDigital(contenidoDigital);
        if(conte != null){
            errorResponse.put("message", "Contenido digital creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al crear el contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateContenidoDigital(@RequestBody ContenidoDigital contenidoDigital){
        errorResponse.clear();
        String message = contenidoDigitalService.updateContenidoDigital(contenidoDigital);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContenidoDigital(@PathVariable Integer id){
        errorResponse.clear();
        String message = contenidoDigitalService.deleteContenidoDigital(id);
        if(message != null) {
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ContenidoDigital> listContenidoDigital(){
        return contenidoDigitalService.listContenidoDigital();
    }
}
