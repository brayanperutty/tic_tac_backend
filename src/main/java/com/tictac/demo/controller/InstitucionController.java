package com.tictac.demo.controller;

import com.tictac.demo.entity.Institucion;
import com.tictac.demo.service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institucion")
public class InstitucionController {

    @Autowired
    InstitucionService institucionService;

    @PostMapping("/create")
    @ResponseBody
    public Institucion saveInstitucion(@RequestBody Institucion institucion){
        return institucionService.saveInstitucion(institucion);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInstitucion(@PathVariable Integer id){
        institucionService.deleteInstitucion(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Institucion> listInstitucion(){
        return institucionService.listInstitucion();
    }
}
