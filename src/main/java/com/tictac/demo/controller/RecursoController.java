package com.tictac.demo.controller;

import com.tictac.demo.entity.Recurso;
import com.tictac.demo.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recurso")
public class RecursoController {

    @Autowired
    RecursoService recursoService;

    @PostMapping("/create")
    @ResponseBody
    public Recurso saveRecurso(@RequestBody Recurso recurso){
        return recursoService.saveRecurso(recurso);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRecurso(@PathVariable Integer id){
        recursoService.deleteRecurso(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Recurso> listRecurso(){
        return recursoService.listRecurso();
    }
}
