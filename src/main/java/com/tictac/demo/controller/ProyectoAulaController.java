package com.tictac.demo.controller;

import com.tictac.demo.entity.ProyectoAula;
import com.tictac.demo.service.ProyectoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/proyecto-aula")
public class ProyectoAulaController {

    @Autowired
    ProyectoAulaService proyectoAulaService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProyectoAula(@PathVariable Integer id){
        errorResponse.clear();
        Optional<ProyectoAula> proyectoAula = proyectoAulaService.getProyectoAula(id);
        if(proyectoAula.isPresent()){
          return ResponseEntity.ok(proyectoAula.get());
        }else{
          errorResponse.put("message", "No se encontró ningún proyecto de Aula");
          return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProyectoAula(@RequestBody ProyectoAula proyectoAula){
        errorResponse.clear();
        ProyectoAula pa = proyectoAulaService.createProyectoAula(proyectoAula);
        if(pa != null){
          errorResponse.put("message", "Proyecto de aula creado con éxito");
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
