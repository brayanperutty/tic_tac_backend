package com.tictac.demo.controller;

import com.tictac.demo.entity.LiderLinea;
import com.tictac.demo.service.LiderLineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lider-linea")
public class LiderLineaController {

    @Autowired
    LiderLineaService liderLineaService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public LiderLinea getLiderLinea(@PathVariable String id){
        Optional<LiderLinea> liderLinea = liderLineaService.getLiderLinea(id);

        return liderLinea.orElse(null);
    }


    @PostMapping("/create")
    @ResponseBody
    public LiderLinea saveLiderLinea(@RequestBody LiderLinea liderLinea){
        return liderLineaService.saveLiderLinea(liderLinea);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLiderLinea(@PathVariable String id){
        liderLineaService.deleteLiderLinea(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<LiderLinea> listLiderLinea(){
        return liderLineaService.listLiderLinea();
    }
}
