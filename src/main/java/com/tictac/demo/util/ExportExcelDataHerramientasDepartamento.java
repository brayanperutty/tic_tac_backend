package com.tictac.demo.util;

import com.tictac.demo.repository.HerramientaRepository;
import com.tictac.demo.repository.InstitucionRepository;
import com.tictac.demo.repository.PersonaRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExportExcelDataHerramientasDepartamento {

    @Autowired
    HerramientaRepository herramientaRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    InstitucionRepository institucionRepository;

    public ByteArrayInputStream exportAllData(String ano) throws Exception{

        String [] columns = {"PPT Ambiental", "PPT Sociales", "PPT Sexualidad", "PPT Emprendimiento", "PPT TIC",
                                "Top docentes herramientas pedagógicas", "Top instituciones herramientas pedagógicas"
        };

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //Crear la hoja de cálculo
        Sheet sheet = workbook.createSheet("Estadísticas Norte de Santander");

        //Combinar las celdas para el header
        sheet.addMergedRegion(new CellRangeAddress(0,2,0,6));

        //Agregar el header
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Estadísticas de Herramientas Pedagógicas Norte de Santander " + ano);


        //Estilos del header
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCell.setCellStyle(headerStyle);

        //Estilos de los encabezados
        Font cellFont = workbook.createFont();
        cellFont.setBold(true);
        cellFont.setFontHeightInPoints((short) 10);
        cellFont.setColor(IndexedColors.WHITE.getIndex());
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setFont(cellFont);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setWrapText(true);

        //Agregar los encabezados
        Row headerRowColumn = sheet.createRow(3);
        for (int i = 0; i < columns.length; i++){
            Cell columnCell = headerRowColumn.createCell(i);
            columnCell.setCellValue(columns[i]);
            columnCell.setCellStyle(cellStyle);
        }
        for (int i = 0; i < columns.length; i++) {

            sheet.autoSizeColumn(i);

            // Opcional: establecer un ancho mínimo para la columna
            int columnWidth = sheet.getColumnWidth(i);
            if (columnWidth < 3000) {
                sheet.setColumnWidth(i, 3000);
            }
        }

        //Estilos de las celdas de la información
        Font infoFont = workbook.createFont();
        infoFont.setFontHeightInPoints((short) 11);
        CellStyle infoStyle = workbook.createCellStyle();
        infoStyle.setFont(infoFont);
        infoStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        infoStyle.setAlignment(HorizontalAlignment.CENTER);
        infoStyle.setBorderBottom(BorderStyle.THIN);
        infoStyle.setBorderTop(BorderStyle.THIN);
        infoStyle.setBorderLeft(BorderStyle.THIN);
        infoStyle.setBorderRight(BorderStyle.THIN);
        infoStyle.setWrapText(true);

        //Agregar estadísticas de las herramientas
        Object[] herramientas = herramientaRepository.findTotalHerramientas().get(0);
        Row dataRow = sheet.createRow(4);
        for (int i = 0; i < herramientas.length; i++){
            Cell dataCell = dataRow.createCell(i);
            dataCell.setCellValue(herramientas[i].toString());
            dataCell.setCellStyle(infoStyle);
        }

        //Agregar ranking docentes
        List<Object[]> topDocentes = personaRepository.findHerramientasByDepartamento();
        String [] rankingDocentes = new String[3];
        topDocentes.forEach(docente -> {
            rankingDocentes[Integer.parseInt(docente[0].toString())-1] = docente[0] + " lugar. " + docente[1] + " " + docente[2] + ", " + docente[3] + ", " + docente[4] + ", PPT - "
                    + docente[5] + ", " + docente[6] + " herramientas realizadas";
        });
        String ranking = "\n" + rankingDocentes[0] + "\n" + "\n" + rankingDocentes[1] + "\n" + "\n" + rankingDocentes[2] + "\n";
        Cell dataDocentes = dataRow.createCell(dataRow.getRowNum()+1);
        dataDocentes.setCellValue(workbook.getCreationHelper().createRichTextString(ranking));
        dataDocentes.setCellStyle(infoStyle);
        sheet.autoSizeColumn(5);

        //Agregar ranking instituciones
        List<Object[]> topInstituciones = institucionRepository.findHerramientasByDepartamento();
        String [] rankingInstituciones = new String[3];
        topInstituciones.forEach(inst -> {
            rankingInstituciones[Integer.parseInt(inst[0].toString())-1] = inst[0] + " lugar. " + inst[1] + ", " + inst[2] + ", " + inst[3] + " herramientas realizadas";
        });
        String topInst = "\n" + rankingInstituciones[0] + "\n" + "\n" + rankingInstituciones[1] + "\n" + "\n" + rankingInstituciones[2] + "\n";
        Cell dataInstituciones = dataRow.createCell(dataRow.getRowNum()+2);
        dataInstituciones.setCellValue(workbook.getCreationHelper().createRichTextString(topInst));
        dataInstituciones.setCellStyle(infoStyle);
        sheet.autoSizeColumn(6);

        workbook.write(stream);
        workbook.close();

        return new ByteArrayInputStream(stream.toByteArray());
    }
}
