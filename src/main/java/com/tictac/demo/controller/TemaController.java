package com.tictac.demo.controller;

import com.tictac.demo.entity.Tema;
import com.tictac.demo.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tema")
public class TemaController {

    @Autowired
    TemaService temaService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTema(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Tema> tema = temaService.getTema(id);
        if(tema.isPresent()){
            return ResponseEntity.ok(tema.get());
        }else{
            errorResponse.put("message", "No se encontró ningún tema");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveTema(@RequestBody Tema tema){
        errorResponse.clear();
        Tema t = temaService.saveTema(tema);
        if(t != null){
            errorResponse.put("message", "Tema creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el tema");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTema(@RequestBody Tema tema){
        errorResponse.clear();
        String message = temaService.updateTema(tema);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el tema");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTema(@PathVariable Integer id){
        errorResponse.clear();
        String message = temaService.deleteTema(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el tema");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Tema> listTema(){
        return temaService.listTema();
    }

    @GetMapping("/list-by-linea/{idLinea}")
    public ResponseEntity<?> listTemaByLinea(@PathVariable Integer idLinea){
        return ResponseEntity.ok(temaService.listTemaByLinea(idLinea));
    }
}
