package com.tictac.demo.controller;

import com.tictac.demo.entity.Momento;
import com.tictac.demo.service.MomentoService;
import org.apache.xmlbeans.XmlError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/momento")
public class MomentoController {

    @Autowired
    MomentoService momentoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMomento(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Momento> momento = momentoService.getMomento(id);

        if(momento.isPresent()){
            return ResponseEntity.ok(momento.get());
        }else{
            errorResponse.put("message", "No se encontró ningún momento con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMomento(@RequestBody Momento momento){
        errorResponse.clear();
        Momento m = momentoService.createMomento(momento);
        if(m != null){
            errorResponse.put("message", "Ciudad creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un erro al crear la ciudad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMomento(@RequestBody Momento momento){
        errorResponse.clear();
        String message = momentoService.updateMomento(momento);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el momento");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMomento(@PathVariable Integer id){
        errorResponse.clear();
        String message = momentoService.deleteMomento(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el momento");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    @GetMapping("/list")
    @ResponseBody
    public List<Momento> listMomento(){
        return momentoService.listMomento();
    }
}
