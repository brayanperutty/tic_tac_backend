package com.tictac.demo.controller;

import com.tictac.demo.entity.ActividadProyecto;
import com.tictac.demo.service.ActividadProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actividad-proyecto")
public class ActividadProyectoController {

    @Autowired
    ActividadProyectoService actividadProyectoService;


    @GetMapping("/get/{id}")
    @ResponseBody
    public ActividadProyecto getActividadProyecto(@PathVariable Integer id){
        Optional<ActividadProyecto> actividadProyecto = actividadProyectoService.getActividadProyecto(id);

        return actividadProyecto.orElse(null);
    }

    @PostMapping("/create")
    @ResponseBody
    public ActividadProyecto createActividadProyecto(@RequestBody ActividadProyecto actividadProyecto){
        return actividadProyectoService.createActividadProyecto(actividadProyecto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActividadProyecto(@PathVariable Integer id){
        actividadProyectoService.deleteActividadProyecto(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ActividadProyecto> listActividadProyecto(){
        return actividadProyectoService.listActividadProyecto();
    }
}
