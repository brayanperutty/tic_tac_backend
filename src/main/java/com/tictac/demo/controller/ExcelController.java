package com.tictac.demo.controller;

import com.tictac.demo.util.ExportExcelDataHerramientasDepartamento;
import com.tictac.demo.util.ExportExcelDataHerramientasMunicipio;
import com.tictac.demo.util.ExportExcelDataInstitucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/data")
public class ExcelController {

    @Autowired
    ExportExcelDataHerramientasDepartamento exportExcelDataHerramientasDepartamento;

    @Autowired
    ExportExcelDataHerramientasMunicipio exportExcelDataHerramientasMunicipio;

    @Autowired
    ExportExcelDataInstitucion exportExcelDataInstitucion;

    @GetMapping("/departamento/{ano}")
    public ResponseEntity<InputStreamResource> downloadDataDepartamento(@PathVariable String ano) throws Exception {

        ByteArrayInputStream stream = exportExcelDataHerramientasDepartamento.exportAllData(ano);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=estadisticas_departamento.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/municipio/{idMunicipio}/{ano}")
    public ResponseEntity<InputStreamResource> downloadDataMunicipio(@PathVariable Integer idMunicipio, @PathVariable String ano) throws Exception {

        ByteArrayInputStream stream = exportExcelDataHerramientasMunicipio.exportAllData(idMunicipio, ano);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=estadisticas_municipio.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/institucion/{idInstitucion}/{ano}")
    public ResponseEntity<?> downloadDataInstitucion(@PathVariable Integer idInstitucion, @PathVariable String ano) throws Exception {
        ByteArrayInputStream stream = exportExcelDataInstitucion.exportAllData(idInstitucion, ano);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Estadisticas_institucion.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }
}
