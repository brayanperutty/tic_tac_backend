package com.tictac.demo.controller;

import com.tictac.demo.entity.Institucion;
import com.tictac.demo.service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/institucion")
public class InstitucionController {

    @Autowired
    InstitucionService institucionService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Institucion getInstitucion(@PathVariable Integer id){
        Optional<Institucion> institucion = institucionService.getInstitucion(id);
        return institucion.orElse(null);
    }

    @GetMapping("/get-by-nombre/{nombre}")
    @ResponseBody
    public Integer getIdInstitucionByNombre(@PathVariable String nombre){
        return institucionService.getInstitucionByNombre(nombre);
    }

    @PostMapping("/create")
    @ResponseBody
    public Institucion saveInstitucion(@RequestBody Institucion institucion){
        return institucionService.saveInstitucion(institucion);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInstitucion(@PathVariable Integer id){
        institucionService.deleteInstitucion(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Institucion> listInstitucion(){
        return institucionService.listInstitucion();
    }

    @GetMapping("/estadisticas-herramientas/{idInstitucion}")
    @ResponseBody
    public Map<String, Object> getEstadisticasHerramientasInstitucion(@PathVariable Integer idInstitucion){
        return institucionService.getEstadisticasHerramientasInstitucion(idInstitucion);
    }

    @GetMapping("/estadisticas-proyectos/{idInstitucion}")
    @ResponseBody
    public Map<String, Object> getEstadisticasProyectosInstitucion(@PathVariable Integer idInstitucion){
        return institucionService.getEstadisticasProyectosInstitucion(idInstitucion);
    }

    @GetMapping("/estadisticas-proyectos-municipio/{idMunicipio}")
    @ResponseBody
    public Map<String, Integer> getEstadisticasProyectosMunicipio(@PathVariable Integer idMunicipio){
        return institucionService.getEstadisticasProyectosMunicipio(idMunicipio);
    }

    @GetMapping("/estadisticas-herramientas-municipio/{idMunicipio}")
    @ResponseBody
    public Map<String, Integer> getEstadisticasHerramientasMunicipio(@PathVariable Integer idMunicipio){
        return institucionService.getEstadisticasHerramientasMunicipio(idMunicipio);
    }

    @GetMapping("/list/{ciudad}")
    @ResponseBody
    public List<Institucion> listInstitucionByCiudad(@PathVariable Integer ciudad){
        return institucionService.listInstitucionByCiudad(ciudad);
    }

}
