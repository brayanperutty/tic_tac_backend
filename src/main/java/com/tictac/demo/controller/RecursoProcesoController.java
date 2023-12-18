package com.tictac.demo.controller;

import com.tictac.demo.entity.RecursoProceso;
import com.tictac.demo.service.RecursoProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recurso-proceso")
public class RecursoProcesoController {

    @Autowired
    RecursoProcesoService recursoProcesoService;


    @GetMapping("/get/{id}")
    @ResponseBody
    public RecursoProceso getRecursoProceso(@PathVariable Integer id){
        Optional<RecursoProceso> recursoProceso = recursoProcesoService.getRecursoProceso(id);

        return recursoProceso.get();
    }

    @PostMapping("/create")
    @ResponseBody
    public RecursoProceso createRecursoProceso(RecursoProceso recursoProceso){
        return recursoProcesoService.createRecursoProceso(recursoProceso);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRecursoProceso(@PathVariable Integer id){
        recursoProcesoService.deleteRecursoProceso(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<RecursoProceso> listRecursoProceso(){
        return recursoProcesoService.listRecursoProceso();
    }
}
