package com.tictac.demo.controller;

import com.tictac.demo.entity.CursoProyecto;
import com.tictac.demo.service.CursoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso-proyecto")
public class CursoProyectoController {

    @Autowired
    CursoProyectoService cursoProyectoService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public CursoProyecto getCursoProyecto(@PathVariable Integer id){
        Optional<CursoProyecto> cursoProyecto = cursoProyectoService.getCursoProyecto(id);

        return cursoProyecto.orElse(null);
    }

    @PostMapping("/create")
    @ResponseBody
    public CursoProyecto createCursoProyecto(@RequestBody CursoProyecto cursoProyecto){
        return cursoProyectoService.createCursoProyecto(cursoProyecto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCursoProyecto(@PathVariable Integer id){
        cursoProyectoService.deleteCursoProyecto(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<CursoProyecto> listCursoProyecto(){
        return cursoProyectoService.listCursoProyecto();
    }
}
