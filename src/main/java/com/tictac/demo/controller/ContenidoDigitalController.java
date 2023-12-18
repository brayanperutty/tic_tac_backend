package com.tictac.demo.controller;

import com.tictac.demo.entity.ContenidoDigital;
import com.tictac.demo.service.ContenidoDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contenido-digital")
public class ContenidoDigitalController {

    @Autowired
    ContenidoDigitalService contenidoDigitalService;


    @GetMapping("/get/{id}")
    @ResponseBody
    public ContenidoDigital getContenidoDigital(@PathVariable Integer id){
        Optional<ContenidoDigital> contenidoDigital = contenidoDigitalService.getContenidoDigital(id);

        return contenidoDigital.get();
    }

    @PostMapping("/create")
    @ResponseBody
    public ContenidoDigital createContenidoDigital(ContenidoDigital contenidoDigital){
        return contenidoDigitalService.createContenidoDigital(contenidoDigital);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContenidoDigital(@PathVariable Integer id){
        contenidoDigitalService.deleteContenidoDigital(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ContenidoDigital> listContenidoDigital(){
        return contenidoDigitalService.listContenidoDigital();
    }
}
