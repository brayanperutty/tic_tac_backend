package com.tictac.demo.controller;

import com.tictac.demo.entity.Competencia;
import com.tictac.demo.service.CompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competencia")
public class CompetenciaController {

    @Autowired
    CompetenciaService competenciaService;

    @PostMapping("/create")
    @ResponseBody
    public Competencia saveCompetencia(@RequestBody Competencia competencia){
        return competenciaService.saveCompetencia(competencia);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompetencia(@PathVariable Integer id){
        competenciaService.deleteCompetencia(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Competencia> listCompetencia(){
        return competenciaService.listCompetencia();
    }
}
