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
        return persona.orElse(null);
    }

    @GetMapping("/get-by-institucion/{idInstitucion}")
    @ResponseBody
    public List<Persona> getPersonaByInstitucion(@PathVariable String idInstitucion){
        return personaService.getPersonaByInstitucion(idInstitucion);
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

    @PatchMapping("/asignar-rol/{idPersona}/{idRol}")
    @ResponseBody
    public Persona asignarRol(@PathVariable String idPersona, @PathVariable Integer idRol){
        return personaService.asignarRol(idPersona, idRol);
    }

    @PutMapping("/editar")
    @ResponseBody
    public Persona editarPersona(@RequestBody Persona persona){
        return personaService.editarPersona(persona);
    }
}
