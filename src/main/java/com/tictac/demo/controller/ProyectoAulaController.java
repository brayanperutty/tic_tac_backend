package com.tictac.demo.controller;

import com.tictac.demo.entity.ProyectoAula;
import com.tictac.demo.service.ProyectoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyecto-aula")
public class ProyectoAulaController {

    @Autowired
    ProyectoAulaService proyectoAulaService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public ProyectoAula getProyectoAula(@PathVariable Integer id){
        Optional<ProyectoAula> proyectoAula = proyectoAulaService.getProyectoAula(id);

        return proyectoAula.get();
    }

    @PostMapping("/create")
    @ResponseBody
    public ProyectoAula createProyectoAula(ProyectoAula proyectoAula){
        return proyectoAulaService.createProyectoAula(proyectoAula);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProyectoAula(@PathVariable Integer id){
        proyectoAulaService.deleteProyectoAula(id);
    }

   @GetMapping("/list")
   @ResponseBody
   public List<ProyectoAula> listProyectoAula(){
        return proyectoAulaService.listProyectoAula();
   }
}
