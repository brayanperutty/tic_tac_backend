package com.tictac.demo.controller;

import com.tictac.demo.entity.Persona;
import com.tictac.demo.service.PersonaService;
import com.tictac.demo.util.ExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    ExcelReaderService excelReaderService;

    @GetMapping("/get/{cedula}")
    @ResponseBody
    public Persona getPersona(@PathVariable String cedula ){
        Optional<Persona> persona = personaService.getPersona(cedula);
        if(persona.isPresent())
        return persona.get();

        return null;
    }

    @PostMapping("/create")
    @ResponseBody
    public Persona savePersona(@RequestBody Persona persona){
        return personaService.savePersona(persona);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePersona(@PathVariable String id){
        personaService.deletePersona(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Persona> listPersona(){
        return personaService.listPersona();
    }

    @PostMapping("/cargar-listado")
    @ResponseBody
    public List<Persona> readExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return excelReaderService.processExcelFile(file);
    }
}
