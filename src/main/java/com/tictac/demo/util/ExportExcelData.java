package com.tictac.demo.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ExportExcelData {

    public ByteArrayInputStream exportAllData(Integer idMunicipio) throws Exception{
        String [] columns = {"Nombre del municipio", "Número de Herramientas Pedagógicas", "Número de Proyectos de Aula", "Número de contenidos digitales",
                                "Top docentes + herramientas pedagógicas", "Top docentes + proyectos de aula", "Top docentes + contenidos digitales",
                                "Top colegios + herramientas pedagógicas", "Top colegios + proyectos de Aula", "Top colegios + contenidos digitales",
        };

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Estadísticas");

        sheet.addMergedRegion(new CellRangeAddress(0,2,0,10));

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++){

        }

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Estadísticas Norte de Santander 2024");
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerStyle.setFont(headerFont);

        headerCell.setCellStyle(headerStyle);

        sheet.autoSizeColumn(0);



        return null;
    }
}
