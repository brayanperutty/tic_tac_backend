package com.tictac.demo.controller;

import com.tictac.demo.DTO.contenidoDigital.ContenidoDigitalArchivoDTO;
import com.tictac.demo.DTO.contenidoDigital.ContenidoDigitalDTO;
import com.tictac.demo.DTO.contenidoDigital.update.ContenidoDigitalUpdate;
import com.tictac.demo.entity.ContenidoDigital;
import com.tictac.demo.service.ContenidoDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contenido-digital")
public class ContenidoDigitalController {

    @Autowired
    ContenidoDigitalService contenidoDigitalService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getContenidoDigital(@PathVariable Integer id){
        return ResponseEntity.ok(contenidoDigitalService.getContenidoDigital(id));
    }

    @PostMapping("/createUrl")
    public ResponseEntity<?> createContenidoDigitalUrl(@RequestBody ContenidoDigitalDTO contenidoDigital){
        errorResponse.clear();
        String message = contenidoDigitalService.createContenidoDigitalUrl(contenidoDigital);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al crear el contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/createArchivo")
    public ResponseEntity<?> createContenidoDigitalArchivo(@RequestBody ContenidoDigitalArchivoDTO contenidoDigital){
        errorResponse.clear();
        String message = contenidoDigitalService.createContenidoDigitalArchivo(contenidoDigital);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else {
            errorResponse.put("message", "Hubo un error al crear el contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateContenidoDigital(@RequestBody ContenidoDigitalUpdate contenidoDigital) throws IOException {
        errorResponse.clear();
        String message = contenidoDigitalService.updateContenidoDigital(contenidoDigital);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContenidoDigital(@PathVariable Integer id){
        errorResponse.clear();
        String message = contenidoDigitalService.deleteContenidoDigital(id);
        if(message != null) {
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ContenidoDigital> listContenidoDigital(){
        return contenidoDigitalService.listContenidoDigital();
    }

    @GetMapping("/observatorio")
    public ResponseEntity<?> listContenidoDigitalObservatorio(){
        return ResponseEntity.ok(contenidoDigitalService.getContenidosObservatorio());
    }

    @GetMapping("/institucion-publico/{idInstitucion}")
    public ResponseEntity<?> listContenidoDigitalInstitucionPublico(@PathVariable Integer idInstitucion){
        return ResponseEntity.ok(contenidoDigitalService.getContenidosInstitucionPublico(idInstitucion));
    }

    @GetMapping("/institucion-publico-filtro/{idInstitucion}/{idLinea}/{anio}")
    public ResponseEntity<?> listContenidoDigitalInstitucionPublicoFiltro(@PathVariable Integer idInstitucion, @PathVariable String idLinea, @PathVariable String anio){
        return ResponseEntity.ok(contenidoDigitalService.getListContenidosInstitucionPublicoFiltro(idInstitucion, idLinea, anio));
    }

    @GetMapping("/observatorio-filtro/{idLinea}/{anio}")
    public ResponseEntity<?> listContenidoDigitalObservatorioFiltro(@PathVariable String idLinea, @PathVariable String anio){
        return ResponseEntity.ok(contenidoDigitalService.getListContenidosObservatorioFiltro(idLinea, anio));
    }

    @GetMapping("/ranking-uso")
    public ResponseEntity<?> rankingDepartamentoUso(){
        return ResponseEntity.ok(contenidoDigitalService.rankingContenidoDepartamentoUso());
    }

    @GetMapping("/ranking-uso-linea/{idLinea}")
    public ResponseEntity<?> rankingDepartamentoUso(@PathVariable Integer idLinea){
        return ResponseEntity.ok(contenidoDigitalService.rankingContenidoDepartamentoUsoFiltro(idLinea));
    }

    @PatchMapping("/gestion/{idContenido}/{estado}/{recomendacion}")
    public ResponseEntity<?> gestionContenidoDigital(@PathVariable Integer idContenido, @PathVariable String estado, @PathVariable String recomendacion){
        return ResponseEntity.ok(contenidoDigitalService.gestionContenidoDigital(idContenido, estado, recomendacion));
    }

    @PatchMapping("/uso/{idContenido}")
    public ResponseEntity<?> usoContenidoDigital(@PathVariable Integer idContenido){
        return ResponseEntity.ok(contenidoDigitalService.usoContenidoDigital(idContenido));
    }
}
