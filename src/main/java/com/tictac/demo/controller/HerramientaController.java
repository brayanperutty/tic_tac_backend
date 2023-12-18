package com.tictac.demo.controller;

import com.tictac.demo.entity.Herramienta;
import com.tictac.demo.service.HerramientaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/herramienta")
public class HerramientaController {

    @Autowired
    HerramientaService herramientaService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Herramienta getHerramienta(@PathVariable Integer id){
        Optional<Herramienta> herramienta = herramientaService.getHerramienta(id);
        return herramienta.get();
    }

    @PostMapping("/create")
    @ResponseBody
    public Herramienta createHerramienta(Herramienta herramienta){
        return herramientaService.createHerramienta(herramienta);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHerramienta(@PathVariable Integer id){
        herramientaService.deleteHerramienta(id);
    }
    
    @GetMapping("/list")
    @ResponseBody
    public List<Herramienta> listHerramienta(){
        return herramientaService.listHerramienta();
    }
}
