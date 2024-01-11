package com.tictac.demo.controller;

import com.tictac.demo.entity.RecursoProceso;
import com.tictac.demo.service.RecursoProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/recurso-proceso")
public class RecursoProcesoController {

    @Autowired
    RecursoProcesoService recursoProcesoService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRecursoProceso(@PathVariable Integer id) {
        errorResponse.clear();
        Optional<RecursoProceso> recursoProceso = recursoProcesoService.getRecursoProceso(id);
        if (recursoProceso.isPresent()){
            return ResponseEntity.ok(recursoProceso.get());
        }else{
            errorResponse.put("message", "No se encontró ningún recurso para ese proceso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRecursoProceso(@RequestBody RecursoProceso recursoProceso){
        errorResponse.clear();
        RecursoProceso rp = recursoProcesoService.createRecursoProceso(recursoProceso);
        if(rp != null){
            errorResponse.put("message", "Recurso creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el recurso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRecursoProceso(@RequestBody RecursoProceso recursoProceso){
        errorResponse.clear();
        String message = recursoProcesoService.updateRecursoProceso(recursoProceso);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el recurso del proceso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecursoProceso(@PathVariable Integer id){
        errorResponse.clear();
        String message = recursoProcesoService.deleteRecursoProceso(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el recurso del proceso");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<RecursoProceso> listRecursoProceso(){
        return recursoProcesoService.listRecursoProceso();
    }
}
