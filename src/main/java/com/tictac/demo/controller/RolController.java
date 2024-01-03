package com.tictac.demo.controller;

import com.tictac.demo.entity.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tictac.demo.service.RolService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolService rolService;


    @GetMapping("/get/{id}")
    @ResponseBody
    public Rol getRol(@PathVariable Integer id){
        Optional<Rol> rol = rolService.getRol(id);
        return rol.orElse(null);
    }

    @GetMapping("/get-id-by-nombre/{nombre}")
    @ResponseBody
    public Integer getRolByNombre(@PathVariable String nombre){
        return rolService.getRolByNombre(nombre);
    }


    @PostMapping("/create")
    @ResponseBody
    public Rol createRol(@RequestBody Rol rol){
        return rolService.saveRol(rol);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Rol> rolList(){
        return rolService.rolList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRol(@PathVariable Integer id){
        rolService.deleteRol(id);
    }


}
