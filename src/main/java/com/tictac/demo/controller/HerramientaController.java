package com.tictac.demo.controller;

import com.tictac.demo.DTO.herramienta.HerramientaDTO;
import com.tictac.demo.DTO.herramienta.update.HerramientaUpdate;
import com.tictac.demo.service.HerramientaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/herramienta")
public class HerramientaController {

    @Autowired
    HerramientaService herramientaService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{idHerramienta}")
    public ResponseEntity<?> getHerramienta(@PathVariable Integer idHerramienta){
        return ResponseEntity.ok(herramientaService.getHerramientaById(idHerramienta));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHerramienta(@RequestBody HerramientaDTO herramienta){
        errorResponse.clear();
        String message = herramientaService.createHerramienta(herramienta);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHerramienta(@RequestBody HerramientaUpdate herramienta){
        errorResponse.clear();
        String message = herramientaService.updateHerramienta(herramienta);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo uno error al actualizar la herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHerramienta(@PathVariable Integer id){
        errorResponse.clear();
        String message = herramientaService.deleteHerramienta(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la herramienta");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllHerramientas(){
        return ResponseEntity.ok(herramientaService.getAllHerramientas());
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalHerramientas(){
        return  ResponseEntity.ok(herramientaService.getTotalHerramientas());
    }

    @GetMapping("/ranking-uso")
    public ResponseEntity<?> rankingDepartamentoHerramientasUso(){
        return ResponseEntity.ok(herramientaService.rankingDepartamentoHerramientasUso());
    }

    @GetMapping("/ranking-uso-linea/{idLinea}")
    public ResponseEntity<?> rankingDepartamentoHerramientasUso(@PathVariable Integer idLinea){
        return ResponseEntity.ok(herramientaService.rankingDepartamentoHerramientasUsoFiltro(idLinea));
    }

    @GetMapping("/institucion-publico-filtro/{idInstitucion}/{idLinea}/{anio}")
    public ResponseEntity<?> getHerramientasInstitucionPublicoFiltro(@PathVariable Integer idInstitucion, @PathVariable String idLinea, @PathVariable String anio){
        return ResponseEntity.ok(herramientaService.getHerramientasInstitucionPublicoFiltro(idInstitucion, idLinea, anio));
    }

    @GetMapping("/institucion-privado-filtro/{idInstitucion}/{idLinea}/{anio}/{estado}")
    public ResponseEntity<?> getHerramientasInstitucionPublicoFiltro(@PathVariable Integer idInstitucion, @PathVariable String idLinea, @PathVariable String anio, @PathVariable String estado){
        return ResponseEntity.ok(herramientaService.getHerramientaInstitucionPrivadoFiltro(idInstitucion, idLinea, anio, estado));
    }


    @PatchMapping("/gestion/{idHerramienta}/{estado}/{comentarios}")
    public ResponseEntity<?> gestionHerramienta(@PathVariable Integer idHerramienta, @PathVariable String estado, @PathVariable String comentarios){
        return ResponseEntity.ok(herramientaService.gestionHerramienta(idHerramienta,estado,comentarios));
    }

    @PatchMapping("/uso/{idHerramienta}")
    public ResponseEntity<?> usoHerramienta(@PathVariable Integer idHerramienta){
        return ResponseEntity.ok(herramientaService.usoHerramienta(idHerramienta));
    }
}
