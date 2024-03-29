package com.tictac.demo.controller;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.entity.Informe;
import com.tictac.demo.service.DocenteService;
import com.tictac.demo.service.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @Autowired
    InformeService informeService;

    Map<String, String> errorResponse = new HashMap<>();

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDocente(@PathVariable String id){
        Optional<Docente> docente = docenteService.getDocente(id);
        if(docente.isPresent()){
            return ResponseEntity.ok(docente.get());
        }else{
            errorResponse.put("message", "No se encontró ningún docente con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveDocente(@RequestBody Docente docente){
        errorResponse.clear();
        Docente doc = docenteService.saveDocente(docente);
        if(doc != null){
            errorResponse.put("message", "Docente creado con éxito");
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear el docente");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDocente(@RequestBody Docente docente){
        errorResponse.clear();
        String message = docenteService.updateDocente(docente);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al actualizar al docente");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocente(@PathVariable String id){
        errorResponse.clear();
        String message = docenteService.deleteDocente(id);
        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al eliminar al docente");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/informe")
    public ResponseEntity<?> crearInforme(@RequestBody Informe informe) throws IOException {
        errorResponse.clear();
        String message = informeService.createInforme(informe);

        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Error al crear el informe");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/informe/{idInforme}")
    public ResponseEntity<?> getInforme(@PathVariable Integer idInforme){
        errorResponse.clear();
        Optional<Informe> informe = informeService.getInforme(idInforme);
        if(informe.isPresent()){
            return ResponseEntity.ok(informe);
        }else{
            errorResponse.put("message", "No se encontró ningún informe con ese ID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/informes-docente/{idDocente}")
    public ResponseEntity<?> listInformesDocente(@PathVariable String idDocente){
        return ResponseEntity.ok(informeService.listInforme(idDocente));
    }

    @GetMapping("/informes-institucion/{idInstitucion}")
    public ResponseEntity<?> listInformesInstitucion(@PathVariable Integer idInstitucion){
        return ResponseEntity.ok(informeService.listInformesInstitucion(idInstitucion));
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Docente> listDocente(){
        return docenteService.listDocente();
    }

    @GetMapping("/ranking-proyectos-departamento")
    public ResponseEntity<?> getRankingProyectosDepartamento(){
        return ResponseEntity.ok(docenteService.rankingDocentesProyectosDepartamento());
    }

    @GetMapping("/ranking-proyectos-municipio/{idMunicipio}")
    public ResponseEntity<?> getRankingProyectosMunicipio(@PathVariable Integer idMunicipio){
        return ResponseEntity.ok(docenteService.rankingDocentesProyectosMunicipio(idMunicipio));
    }

    @GetMapping("/ranking-proyectos-institucion/{idInstitucion}")
    public ResponseEntity<?> getRankingProyectosInstitucion(@PathVariable Integer idInstitucion){
        return ResponseEntity.ok(docenteService.rankingDocentesProyectosInstitucion(idInstitucion));
    }

    @GetMapping("/ranking-herramientas-departamento")
    public ResponseEntity<?> getRankingHerramientasDepartamento(){
        return ResponseEntity.ok(docenteService.rankingDocentesHerramientasDepartamento());
    }

    @GetMapping("/ranking-herramientas-municipio/{idMunicipio}")
    public ResponseEntity<?> getRankingHerramientasMunicipio(@PathVariable Integer idMunicipio){
        return ResponseEntity.ok(docenteService.rankingDocentesHerramientasMunicipio(idMunicipio));
    }

    @GetMapping("/ranking-herramientas-institucion/{idInstitucion}")
    public ResponseEntity<?> getRankingHerramientasInstitucion(@PathVariable Integer idInstitucion){
        return ResponseEntity.ok(docenteService.rankingDocentesHerramientasInstitucion(idInstitucion));
    }

    @GetMapping("/ranking-contenidos-departamento")
    public ResponseEntity<?> getRankingContenidosDepartamento(){
        return ResponseEntity.ok(docenteService.rankingDocentesContenidosDepartamento());
    }

    @GetMapping("/ranking-contenidos-municipio/{idMunicipio}")
    public ResponseEntity<?> getRankingContenidosMunicipio(@PathVariable Integer idMunicipio){
        return ResponseEntity.ok(docenteService.rankingDocentesContenidosMunicipio(idMunicipio));
    }

    @GetMapping("/ranking-contenidos-institucion/{idInstitucion}")
    public ResponseEntity<?> getRankingContenidosInstitucion(@PathVariable Integer idInstitucion){
        return ResponseEntity.ok(docenteService.rankingDocentesContenidosInstitucion(idInstitucion));
    }
}
