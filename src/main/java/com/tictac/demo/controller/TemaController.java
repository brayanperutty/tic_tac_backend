package com.tictac.demo.controller;

import com.tictac.demo.entity.Tema;
import com.tictac.demo.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tema")
public class TemaController {

    @Autowired
    TemaService temaService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Tema getTema(@PathVariable Integer id){
        Optional<Tema> tema = temaService.getTema(id);
        return tema.orElse(null);
    }


    @PostMapping("/create")
    @ResponseBody
    public Tema saveTema(@RequestBody Tema tema){
        return temaService.saveTema(tema);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTema(@PathVariable Integer id){
        temaService.deleteTema(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Tema> listTema(){
        return temaService.listTema();
    }
}
