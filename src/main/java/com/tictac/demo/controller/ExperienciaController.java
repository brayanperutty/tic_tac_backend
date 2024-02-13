package com.tictac.demo.controller;

import com.tictac.demo.DTO.ExperienciaDTO;
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
    public ResponseEntity<?> createExperiencia(@RequestBody ExperienciaDTO experiencia){
        errorResponse.clear();

        String message = experienciaService.createExperiencia(experiencia);

        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la experiencia");
            return ResponseEntity.ok(errorResponse);
        }
    }
}
