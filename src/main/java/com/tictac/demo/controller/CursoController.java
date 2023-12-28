package com.tictac.demo.controller;

import com.tictac.demo.entity.Curso;
import com.tictac.demo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Curso getCurso(@PathVariable Integer id){
        Optional<Curso> curso = cursoService.getCurso(id);
        return curso.orElse(null);
    }

    @PostMapping("/create")
    @ResponseBody
    public Curso create(Curso curso){
        return cursoService.createCurso(curso);
    }

    @DeleteMapping("/delete/{id}")
    public void delele(@PathVariable Integer id){
        cursoService.deleteCurso(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Curso> listCurso(){
        return cursoService.listCurso();
    }
}
