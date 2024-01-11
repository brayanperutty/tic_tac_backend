package com.tictac.demo.controller;

import com.tictac.demo.entity.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tictac.demo.service.RolService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolService rolService;

    Map<String, String> errorResponse = new HashMap<>();
    Map<String, Integer> dato = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRol(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Rol> rol = rolService.getRol(id);
        if(rol.isPresent()){
            return ResponseEntity.ok(rol.get());
        }else{
            errorResponse.put("message", "No se encontró ningún rol");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/get-id-by-nombre/{nombre}")
    public ResponseEntity<?> getRolByNombre(@PathVariable String nombre){
        errorResponse.clear();
        dato.clear();
        Integer idRol = rolService.getRolByNombre(nombre);
        if(idRol != null){
            dato.put("message", idRol);
            return ResponseEntity.ok(dato);
        }else{
            errorResponse.put("message", "No se encontró ningún rol con ese nombre");
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> createRol(@RequestBody Rol rol){
        errorResponse.clear();
        Rol r = rolService.saveRol(rol);
        if(r != null){
            errorResponse.put("message", "Rol creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el rol");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRol(@RequestBody Rol rol){
        errorResponse.clear();
        String message = rolService.updateRol(rol);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar el rol");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Integer id){
        errorResponse.clear();
        String message = rolService.deleteRol(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar el rol");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Rol> rolList(){
        return rolService.rolList();
    }




}
