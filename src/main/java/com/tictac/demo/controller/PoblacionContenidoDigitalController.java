package com.tictac.demo.controller;

import com.tictac.demo.entity.PoblacionContenidoDigital;
import com.tictac.demo.service.PoblacionContenidoDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poblacion-contenido-digital")
public class PoblacionContenidoDigitalController {

    @Autowired
    PoblacionContenidoDigitalService poblacionContenidoDigitalService;

    Map<String, String> errorResponse = new HashMap<>();

    @PostMapping("/create")
    public ResponseEntity<?> createPoblacionContenidoDigital(@RequestBody PoblacionContenidoDigital poblacionContenidoDigital){
        errorResponse.clear();
        PoblacionContenidoDigital p = poblacionContenidoDigitalService.createPoblacionContenidoDigital(poblacionContenidoDigital);
        if(p != null){
            errorResponse.put("message", "Población creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la población");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{idContenidoDigital}/{idPoblacion}")
    public ResponseEntity<?> deletePoblacionContenidoDigital(@PathVariable Integer idContenidoDigital, @PathVariable Integer idPoblacion){
        errorResponse.clear();
        String message = poblacionContenidoDigitalService.deletePoblacionContenidoDigital(idContenidoDigital,idPoblacion);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar esta población de este contenido digital");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list/{idContenidoDigital}")
    @ResponseBody
    public List<PoblacionContenidoDigital> listPoblacionContenidoDigital(@PathVariable Integer idContenidoDigital){
        return poblacionContenidoDigitalService.listPoblacionContenidoDigital(idContenidoDigital);
    }
}
