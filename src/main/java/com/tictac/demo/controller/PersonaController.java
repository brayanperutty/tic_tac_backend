package com.tictac.demo.controller;

import com.tictac.demo.entity.Persona;
import com.tictac.demo.service.PersonaService;
import com.tictac.demo.util.ExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    ExcelReaderService excelReaderService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{cedula}")
    public ResponseEntity<?> getPersona(@PathVariable String cedula ){
        errorResponse.clear();
        Optional<Persona> persona = personaService.getPersona(cedula);
        if(persona.isPresent()){
            return ResponseEntity.ok(persona.get());
        }else{
            errorResponse.put("message", "No se encontró ninguna persona con esa cédula");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/get-by-institucion/{idInstitucion}")
    public ResponseEntity<?> getPersonaByInstitucion(@PathVariable String idInstitucion){
        errorResponse.clear();
        List<Persona> personas = personaService.getPersonaByInstitucion(idInstitucion);
        if(personas.isEmpty()){
            errorResponse.put("message", "No se encontraron personas registradas en esta institución");
            return ResponseEntity.badRequest().body(errorResponse);
        }else{
            return ResponseEntity.ok(personas);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> savePersona(@RequestBody Persona persona){
        errorResponse.clear();
        String message = personaService.savePersona(persona);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la persona");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPersona(@RequestParam String codigo, @RequestParam String password, @RequestParam Integer idRol){
        errorResponse.clear();
        Map<String, Object> persona = personaService.loginPersona(codigo, password, idRol);
        if(!persona.isEmpty()){
            return ResponseEntity.ok(persona);
        }else{
            errorResponse.put("message", "Login incorrecto. Por favor verifique sus datos");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> updatePersona(@RequestBody Persona persona){
        errorResponse.clear();
        String message = personaService.updatePersona(persona);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar la persona");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable String id){
        errorResponse.clear();
        String message = personaService.deletePersona(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al eliminar la persona");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/cargar-listado/{idInstitucion}")
    public ResponseEntity<?> readExcel(@RequestParam("file") MultipartFile file, @PathVariable Integer idInstitucion) throws IOException {
        errorResponse.clear();
        if(excelReaderService.processExcelFile(file, idInstitucion)){
            errorResponse.put("message", "Listado de docentes registrados con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al registrar el listado de docentes");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PatchMapping("/asignar-rol/{codigoDocente}/{estado}/{idLinea}")
    public ResponseEntity<?> asignarRol(@PathVariable String codigoDocente, @PathVariable Boolean estado, @PathVariable Integer idLinea){
        errorResponse.clear();
        String message = personaService.asignarRol(codigoDocente, estado, idLinea);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al asignar el rol");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Persona> listPersona(){
        return personaService.listPersona();
    }

    @GetMapping("/list-institucion/{idInstitucion}/{idDocente}")
    public ResponseEntity<?> listDocentes(@PathVariable Integer idInstitucion, @PathVariable String idDocente){
        return ResponseEntity.ok(personaService.listDocentes(idInstitucion, idDocente));
    }
}
