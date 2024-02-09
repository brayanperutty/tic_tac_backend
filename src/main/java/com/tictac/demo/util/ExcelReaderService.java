package com.tictac.demo.util;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.entity.Institucion;
import com.tictac.demo.entity.Persona;
import com.tictac.demo.repository.*;
import com.tictac.demo.service.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.text.similarity.LevenshteinDistance;

@Service
public class ExcelReaderService {

    @Autowired
    PersonaService personaService;

    @Autowired
    DocenteService docenteService;

    @Autowired
    RolService rolService;

    @Autowired
    InstitucionService institucionService;

    @Autowired
    InstitucionRepository institucionRepository;
    @Autowired
    CiudadService ciudadService;

    List<Persona> personas = new ArrayList<>();

    public Boolean processExcelFile(MultipartFile file) throws IOException {
        personas.clear();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getPhysicalNumberOfCells() == 0) {
                    // Si la fila está vacía, detén el bucle while
                    break;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                String cedula = cellIterator.next().getStringCellValue();
                String nombre = cellIterator.next().getStringCellValue();
                String apellido = cellIterator.next().getStringCellValue();
                String password = cellIterator.next().getStringCellValue();
                Date fechaNacimiento = null;

                Cell edadCell = cellIterator.next();
                if (edadCell.getCellType() == CellType.NUMERIC) {
                    fechaNacimiento = edadCell.getDateCellValue();
                } else if (edadCell.getCellType() == CellType.STRING) {
                    String fechaStr = edadCell.getStringCellValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        fechaNacimiento = dateFormat.parse(fechaStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


                String codigo = cellIterator.next().getStringCellValue();

                String nombreRol = cellIterator.next().getStringCellValue();
                Integer idRol = rolService.getRolByNombre(nombreRol);


                String ciudadUsuario = cellIterator.next().getStringCellValue();
                Integer ciudadId = ciudadService.getIdCiudadByNombre(ciudadUsuario);


                String institucion = cellIterator.next().getStringCellValue();
                Integer idInstitucion = institucionService.getInstitucionByNombre(institucion);


                Persona nuevaPersona = new Persona(cedula, nombre, apellido, password, fechaNacimiento, codigo, idRol, idInstitucion);
                personaService.savePersona(nuevaPersona);
                System.out.println(nuevaPersona);
                Docente docente = new Docente();
                docente.setIdDocente(cedula);
                docenteService.saveDocente(docente);
                personas.add(nuevaPersona);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
