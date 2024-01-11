package com.tictac.demo.controller;

import com.tictac.demo.entity.Proceso;
import com.tictac.demo.service.ProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/proceso")
public class ProcesoController {

    @Autowired
    ProcesoService procesoService;
    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProceso(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Proceso> proceso = procesoService.getProceso(id);
        if(proceso.isPresent()){
            return ResponseEntity.ok(proceso.get());
        }else{
            errorResponse.put("message", "No se encontró ningún proceso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProceso(@RequestBody Proceso proceso){
        errorResponse.clear();
        Proceso p = procesoService.createProceso(proceso);
        if(p != null){
            errorResponse.put("message", "Proceso creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el proceso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProceso(@RequestBody Proceso proceso){
        errorResponse.clear();
        String message = procesoService.updateProceso(proceso);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el proceso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProceso(@PathVariable Integer id){
        errorResponse.clear();
        String message = procesoService.deleteProceso(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el proceso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Proceso> listProceso(){
        return procesoService.listProceso();
    }
}
