package com.tictac.demo.controller;

import com.tictac.demo.entity.Institucion;
import com.tictac.demo.service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/institucion")
public class InstitucionController {

    @Autowired
    InstitucionService institucionService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getInstitucion(@PathVariable Integer id){
        errorResponse.clear();
        Optional<Institucion> institucion = institucionService.getInstitucion(id);
        if(institucion.isPresent()){
            return ResponseEntity.ok(institucion.get());
        }else{
            errorResponse.put("message", "No se encontró ninguna institución con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveInstitucion(@RequestBody Institucion institucion){
        errorResponse.clear();
        Institucion i = institucionService.saveInstitucion(institucion);
        if(i != null){
            errorResponse.put("message", "Institución creada con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la institución");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInstitucion(@RequestBody Institucion institucion){
        errorResponse.clear();
        String message = institucionService.updateInstitucion(institucion);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "hubo un error al actualizar la institución");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstitucion(@PathVariable Integer id){
        errorResponse.clear();
        String message = institucionService.deleteInstitucion(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar la ciudad");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/get-by-nombre/{nombre}")
    public ResponseEntity<?> getIdInstitucionByNombre(@PathVariable String nombre){
        errorResponse.clear();
        Map<String, Integer> dato = new HashMap<>();
        Integer id = institucionService.getInstitucionByNombre(nombre);
        if(id != null){
            dato.put("idInstitucion", id);
            return ResponseEntity.ok(dato);
        }else{
            errorResponse.put("message", "No se encuentra ninguna institución con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Institucion> listInstitucion(){
        return institucionService.listInstitucion();
    }

    @GetMapping("/estadisticas-herramientas/{idInstitucion}")
    public ResponseEntity<?> getEstadisticasHerramientasInstitucion(@PathVariable Integer idInstitucion){
        errorResponse.clear();
        Map<String, Integer> dato = new HashMap<>();
        dato = institucionService.getEstadisticasHerramientasInstitucion(idInstitucion);
        if(dato != null){
            return ResponseEntity.ok(dato);
        }else{
         errorResponse.put("message", "Institución sin datos de herramientas registradas");
         return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/estadisticas-proyectos/{idInstitucion}")
    public ResponseEntity<?> getEstadisticasProyectosInstitucion(@PathVariable Integer idInstitucion){
        errorResponse.clear();
        Map<String, Integer> dato = new HashMap<>();
        dato = institucionService.getEstadisticasProyectosInstitucion(idInstitucion);
        if(dato != null){
            return ResponseEntity.ok(dato);
        }else{
            errorResponse.put("message", "Institución sin datos de proyectos registrados");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/estadisticas-proyectos-municipio/{idMunicipio}")
    public ResponseEntity<?> getEstadisticasProyectosMunicipio(@PathVariable Integer idMunicipio){
        errorResponse.clear();
        Map<String, Integer> dato = new HashMap<>();
        dato = institucionService.getEstadisticasProyectosMunicipio(idMunicipio);
        if(dato != null){
            return ResponseEntity.ok(dato);
        }else{
            errorResponse.put("message", "Municipio sin datos de proyectos registrados");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/estadisticas-herramientas-municipio/{idMunicipio}")
    public ResponseEntity<?> getEstadisticasHerramientasMunicipio(@PathVariable Integer idMunicipio){
        errorResponse.clear();
        Map<String, Integer> dato = new HashMap<>();
        dato = institucionService.getEstadisticasHerramientasMunicipio(idMunicipio);
        if(dato != null){
            return ResponseEntity.ok(dato);
        }else{
            errorResponse.put("message", "Municipio sin datos de herramientas registradas");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list/{ciudad}")
    public ResponseEntity<?> listInstitucionByCiudad(@PathVariable Integer ciudad){
        errorResponse.clear();
        List<Institucion> inst = institucionService.listInstitucionByCiudad(ciudad);
        if(inst.isEmpty() || inst == null){
            errorResponse.put("message", "No se encontró ninguna institución registrada en este municipio");
            return ResponseEntity.badRequest().body(errorResponse);
        }else{
            return ResponseEntity.ok(inst);
        }
    }

}
