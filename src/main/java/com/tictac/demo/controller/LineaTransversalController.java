package com.tictac.demo.controller;

import com.tictac.demo.entity.LineaTransversal;
import com.tictac.demo.service.LineaTransversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/linea-transversal")
public class LineaTransversalController {

    @Autowired
    LineaTransversalService lineaTransversalService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLineaTransversal(@PathVariable Integer id){
        errorResponse.clear();
        Optional<LineaTransversal> lineaTransversal = lineaTransversalService.getLineaTransversal(id);
        if(lineaTransversal.isPresent()){
            return ResponseEntity.ok(lineaTransversal.get());
        }else{
            errorResponse.put("message", "No se encontró ninguna línea transversal con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveLineaTransversal(@RequestBody LineaTransversal lineaTransversal){
        errorResponse.clear();
        LineaTransversal l = lineaTransversalService.saveLineaTransversal(lineaTransversal);
        if(l != null){
            errorResponse.put("message", "Línea transversal creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la línea transversal");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLineaTransversal(@RequestBody LineaTransversal lineaTransversal){
        errorResponse.clear();
        String message = lineaTransversalService.updateLineaTransversal(lineaTransversal);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar la línea transversal");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLineaTransversal(@PathVariable Integer id){
        errorResponse.clear();
        String message = lineaTransversalService.deleteLineaTransversal(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la línea transversal");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<LineaTransversal> listLineaTransversal(){
        return lineaTransversalService.listLineaTransversal();
    }
}
