package com.tictac.demo.controller;

import com.tictac.demo.entity.Tema;
import com.tictac.demo.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tema")
public class TemaController {

    @Autowired
    TemaService temaService;

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
