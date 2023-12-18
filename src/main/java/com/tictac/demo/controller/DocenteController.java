package com.tictac.demo.controller;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @PostMapping("/create")
    @ResponseBody
    public Docente saveDocente(@RequestBody Docente docente){
     return docenteService.saveDocente(docente);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDocente(@PathVariable String id){
        docenteService.deleteDocente(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Docente> listDocente(){
        return docenteService.listDocente();
    }
}
