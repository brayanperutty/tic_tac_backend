package com.tictac.demo.util;

import com.tictac.demo.entity.Persona;
import com.tictac.demo.repository.PersonaRepository;
import com.tictac.demo.service.InstitucionService;
import com.tictac.demo.service.RolService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelReaderService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    RolService rolService;

    @Autowired
    InstitucionService institucionService;

    List<Persona> personas = new ArrayList<>();

    public List<Persona> processExcelFile(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
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
                Integer id_rol = rolService.getRolByNombre(cellIterator.next().getStringCellValue());
                Integer id_institucion = institucionService.getInstitucionByNombre(cellIterator.next().getStringCellValue());

                Persona nuevaPersona = new Persona(cedula, nombre, apellido, password, fechaNacimiento, codigo, id_rol, id_institucion);

                personaRepository.save(nuevaPersona);
                personas.add(nuevaPersona);
            }
        }
        return personas;
    }
}
