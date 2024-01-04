package com.tictac.demo.controller;

import com.tictac.demo.entity.Momento;
import com.tictac.demo.service.MomentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/momento")
public class MomentoController {

    @Autowired
    MomentoService momentoService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Momento getMomento(@PathVariable Integer id){
        Optional<Momento> momento = momentoService.getMomento(id);

        return momento.orElse(null);
    }

    @PostMapping("/create")
    @ResponseBody
    public Momento createMomento(@RequestBody Momento momento){
        return momentoService.createMomento(momento);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMomento(@PathVariable Integer id){
        momentoService.deleteMomento(id);
    }
    
    @GetMapping("/list")
    @ResponseBody
    public List<Momento> listMomento(){
        return momentoService.listMomento();
    }
}
