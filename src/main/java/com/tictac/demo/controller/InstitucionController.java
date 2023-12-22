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

        return institucion.get();
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

    @GetMapping("/estadisticas-herramientas/{id}")
    @ResponseBody
    public Map<String, Object> getEstadisticasHerramientasInstitucion(@PathVariable Integer id){
        return institucionService.getEstadisticasHerramientasInstitucion(id);
    }

    @GetMapping("/estadisticas-proyectos/{id}")
    @ResponseBody
    public Map<String, Object> getEstadisticasProyectosInstitucion(@PathVariable Integer id){
        return institucionService.getEstadisticasProyectosInstitucion(id);
    }

    @GetMapping("/estadisticas-proyectos-municipio/{id}")
    @ResponseBody
    public Map<String, Integer> getEstadisticasProyectosMunicipio(@PathVariable Integer id){
        return institucionService.getEstadisticasProyectosMunicipio(id);
    }

    @GetMapping("/list/{ciudad}")
    @ResponseBody
    public List<Institucion> listInstitucionByCiudad(@PathVariable Integer ciudad){
        return institucionService.listInstitucionByCiudad(ciudad);
    }

}
