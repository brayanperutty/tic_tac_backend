package com.tictac.demo.controller;

import com.tictac.demo.entity.Proceso;
import com.tictac.demo.service.ProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proceso")
public class ProcesoController {

    @Autowired
    ProcesoService procesoService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Proceso getProceso(@PathVariable Integer id){
        Optional<Proceso> proceso = procesoService.getProceso(id);
        return proceso.get();
    }

    @PostMapping("/create")
    @RequestMapping
    public Proceso createProceso(Proceso proceso){
        return procesoService.createProceso(proceso);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProceso(@PathVariable Integer id){
        procesoService.deleteProceso(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Proceso> listProceso(){
        return procesoService.listProceso();
    }
}
