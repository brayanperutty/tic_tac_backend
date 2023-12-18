package com.tictac.demo.controller;

import com.tictac.demo.entity.Ciudad;
import com.tictac.demo.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Ciudad getCiudad(@PathVariable Integer id){
        Optional<Ciudad> ciudad = ciudadService.getCiudad(id);

        return ciudad.get();
    }

    @PostMapping("/create")
    @ResponseBody
    public Ciudad saveCiudad(@RequestBody Ciudad ciudad){
        return ciudadService.saveCiudad(ciudad);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Ciudad> listCiudad(){
        return ciudadService.listCiudad();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCiudad(@PathVariable Integer id){
        ciudadService.deleteCiudad(id);
    }
}
