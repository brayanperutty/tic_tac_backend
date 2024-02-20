package com.tictac.demo.controller;

import com.tictac.demo.DTO.proyectoAula.ProyectoDTO;
import com.tictac.demo.entity.ProyectoAula;
import com.tictac.demo.service.ProyectoAulaService;
import io.swagger.annotations.OAuth2Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/proyecto-aula")
public class ProyectoAulaController {

    @Autowired
    ProyectoAulaService proyectoAulaService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProyectoAula(@PathVariable Integer id){
        return ResponseEntity.ok(proyectoAulaService.getProyectoAula(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProyectoAula(@RequestBody ProyectoDTO proyectoAula) throws ParseException {
        errorResponse.clear();
        String message = proyectoAulaService.createProyectoAula(proyectoAula);
        if(message != null){
          errorResponse.put("message", message);
          return ResponseEntity.ok(errorResponse);
        }else{
          errorResponse.put("message", "Hubo un error al crear el proyecto de aula");
          return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProyectoAula(@RequestBody ProyectoAula proyectoAula){
      errorResponse.clear();
      String message = proyectoAulaService.updateProyectoAula(proyectoAula);
      if(message != null){
        errorResponse.put("message", message);
        return ResponseEntity.ok(errorResponse);
      }else{
        errorResponse.put("message", "Hubo un error al actualizar el proyecto de aula");
        return ResponseEntity.badRequest().body(errorResponse);
      }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProyectoAula(@PathVariable Integer id){
        errorResponse.clear();
        String message = proyectoAulaService.deleteProyectoAula(id);
        if(message != null){
          errorResponse.put("message", message);
          return ResponseEntity.ok(errorResponse);
        }else{
          errorResponse.put("message", "Hubo un error al eliminar el proyecto de aula");
          return ResponseEntity.badRequest().body(errorResponse);
        }
    }

   @GetMapping("/list")
   @ResponseBody
   public List<ProyectoAula> listProyectoAula(){
        return proyectoAulaService.listProyectoAula();
   }

   @GetMapping("/list/proyectos-docente/{idDocente}")
   public ResponseEntity<?> listProyectosDocente(@PathVariable String idDocente){
        return ResponseEntity.ok(proyectoAulaService.listProyectosDocente(idDocente));
   }

   @GetMapping("/total")
    public ResponseEntity<?> getTotalProyectos(){
        return ResponseEntity.ok(proyectoAulaService.getTotalProyectos());
   }

   @GetMapping("/institucion-publico/{idInstitucion}")
    public ResponseEntity<?> getProyectosInstitucionPublico(@PathVariable Integer idInstitucion){
        return ResponseEntity.ok(proyectoAulaService.getProyectosInstitucionPublico(idInstitucion));
   }

   @GetMapping("/institucion-publico-filtro/{idInstitucion}/{idLinea}/{anio}")
    public ResponseEntity<?> getProyectosInstitucionPublicoFiltro(@PathVariable Integer idInstitucion, @PathVariable String idLinea, @PathVariable String anio){
        return ResponseEntity.ok(proyectoAulaService.getProyectosInstitucionPublicoFiltro(idInstitucion, idLinea, anio));
   }
}
