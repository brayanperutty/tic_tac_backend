package com.tictac.demo.controller;

import com.tictac.demo.entity.Rol;

import org.springframework.web.bind.annotation.*;
import com.tictac.demo.service.RolService;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {

    final
    RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/hola")
    public String connection(){
        return "Hola mundo!";
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
