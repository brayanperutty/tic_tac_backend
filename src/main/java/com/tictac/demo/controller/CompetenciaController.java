package com.tictac.demo.controller;

import com.tictac.demo.entity.Competencia;
import com.tictac.demo.service.CompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/competencia")
public class CompetenciaController {

    @Autowired
    CompetenciaService competenciaService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCompetencia(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Competencia> competencia = competenciaService.getCompetencia(id);
        if(competencia.isPresent()){
            return ResponseEntity.ok(competencia.get());
        }else{
            errorResponse.put("message", "No se encontré ninguna competencia con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveCompetencia(@RequestBody Competencia competencia){
        errorResponse.clear();
        Competencia compe = competenciaService.saveCompetencia(competencia);
        if(compe != null){
            errorResponse.put("message", "Competencia creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la competencia");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCompetencia(@RequestBody Competencia competencia){
        errorResponse.clear();
        String message = competenciaService.updateCompetencia(competencia);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar la competencia");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompetencia(@PathVariable Integer id){
        errorResponse.clear();
        String message = competenciaService.deleteCompetencia(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la competencia");
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @GetMapping("/list")
    @ResponseBody
    public List<Competencia> listCompetencia(){
        return competenciaService.listCompetencia();
    }
}
