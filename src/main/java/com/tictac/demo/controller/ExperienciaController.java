package com.tictac.demo.controller;

import com.tictac.demo.entity.Experiencia;
import com.tictac.demo.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/experiencia")
public class ExperienciaController {

    @Autowired
    ExperienciaService experienciaService;

    Map<String, String> errorResponse = new HashMap<>();
    @GetMapping("/list")
    public ResponseEntity<?> listExperiencias(){
        return ResponseEntity.ok(experienciaService.listExperiencias());
    }


    @PostMapping("/create")
    public ResponseEntity<?> createExperiencia(@RequestBody Experiencia experiencia){
        errorResponse.clear();

        Experiencia e = experienciaService.createExperiencia(experiencia);

        if(e != null){
            errorResponse.put("message", "Experiencia creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la experiencia");
            return ResponseEntity.ok(errorResponse);
        }
    }
}
