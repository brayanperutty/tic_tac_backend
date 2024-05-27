package com.tictac.demo.controller;

import com.tictac.demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/data")
public class ExcelController {

    @Autowired
    ExportExcelDataHerramientasDepartamento exportExcelDataHerramientasDepartamento;

    @Autowired
    ExportExcelDataHerramientasMunicipio exportExcelDataHerramientasMunicipio;

    @Autowired
    ExportExcelDataInstitucion exportExcelDataInstitucion;

    @Autowired
    ExportExcelDataProyectosDepartamento exportExcelDataProyectosDepartamento;

    @Autowired
    ExportExcelDataProyectosMunicipio exportExcelDataProyectosMunicipio;

    @GetMapping("/herramietas-departamento")
    public ResponseEntity<InputStreamResource> downloadDataHerramientasDepartamento() throws Exception {

        ByteArrayInputStream stream = exportExcelDataHerramientasDepartamento.exportAllData();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=estadisticas_herramientas_departamento.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/herramientas-municipio/{idMunicipio}")
    public ResponseEntity<InputStreamResource> downloadDataHerramientasMunicipio(@PathVariable Integer idMunicipio) throws Exception {

        ByteArrayInputStream stream = exportExcelDataHerramientasMunicipio.exportAllData(idMunicipio);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=estadisticas_herramientas_municipio.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/institucion/{idInstitucion}")
    public ResponseEntity<?> downloadDataInstitucion(@PathVariable Integer idInstitucion) throws Exception {
        ByteArrayInputStream stream = exportExcelDataInstitucion.exportAllData(idInstitucion);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Estadisticas_institucion.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/proyectos-departamento/download")
    public ResponseEntity<?> downloadDataProyectosDepartamento(HttpServletResponse response) throws Exception {

            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=estadisticas_proyectos_departamento.xls";
            response.setHeader(headerKey, headerValue);
            exportExcelDataProyectosDepartamento.exportAllData(response);
            return ResponseEntity.ok("Documento generado con éxito");
    }

    @GetMapping("/proyectos-municipio/{idMunicipio}")
    public ResponseEntity<InputStreamResource> downloadDataProyectosMunicipio(@PathVariable Integer idMunicipio) throws Exception {

        ByteArrayInputStream stream = exportExcelDataProyectosMunicipio.exportAllData(idMunicipio);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=estadisticas_proyectos_municipio.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }
}
