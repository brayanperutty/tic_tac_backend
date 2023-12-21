package com.tictac.demo.controller;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/get/{id}")
    @ResponseBody
    public Docente getDocente(@PathVariable String id){
        Optional<Docente> docente = docenteService.getDocente(id);

        return docente.get();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Docente> listDocente(){
        return docenteService.listDocente();
    }
}
