package com.tictac.demo.util;

import com.tictac.demo.repository.CiudadRepository;
import com.tictac.demo.repository.HerramientaRepository;
import com.tictac.demo.repository.InstitucionRepository;
import com.tictac.demo.repository.PersonaRepository;
import com.tictac.demo.service.InstitucionService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class ExportExcelDataInstitucion {

    @Autowired
    HerramientaRepository herramientaRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    InstitucionRepository institucionRepository;

    @Autowired
    InstitucionService institucionService;

    public ByteArrayInputStream exportAllData(Integer idInstitucion) throws Exception{

        String institucion = institucionRepository.findNombreInstitucion(idInstitucion);

        String [] columns = {"PPT Ambiental", "PPT Sociales", "PPT Sexualidad", "PPT Emprendimiento", "PPT TIC", "Top Docentes"};

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //Crear la hoja de cálculo
        Sheet sheet = workbook.createSheet("Estadísticas " + institucion);

        //Combinar las celdas para el header
        sheet.addMergedRegion(new CellRangeAddress(0,2,0,6));

        //Agregar el header
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Estadísticas " + institucion + " - 2023");


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
        Cell columnRespeto = headerRowColumn.createCell(0);
        columnRespeto.setCellValue(" ");
        columnRespeto.setCellStyle(cellStyle);
        for (int i = 1; i <= columns.length; i++){
            Cell columnCell = headerRowColumn.createCell(i);
            columnCell.setCellValue(columns[i-1]);
            columnCell.setCellStyle(cellStyle);
        }
        for (int i = 0; i < columns.length; i++) {

            sheet.autoSizeColumn(i);

            // Opcional: establecer un ancho mínimo para la columna
            int columnWidth = sheet.getColumnWidth(i);
            if (columnWidth < 3500) {
                sheet.setColumnWidth(i, 3500);
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

        //Agregar estadísticas de las herramientas---------------------------------------------------------------------------------------------
        Map<String, Object> datosHerramientas = institucionService.getEstadisticasHerramientasInstitucion(idInstitucion);

        Row dataRowH = sheet.createRow(4);
        Cell herramienta = dataRowH.createCell(0);
        herramienta.setCellValue("Herramientas");
        herramienta.setCellStyle(cellStyle);

        String[] pptH = {"ambiental", "sociales", "sexualidad", "emprendimiento", "tic"};
        for (int i = 1; i < datosHerramientas.size(); i++){
            Cell dataCell = dataRowH.createCell(i);
            dataCell.setCellValue(datosHerramientas.get(pptH[(i)-1].toString()).toString());
            dataCell.setCellStyle(infoStyle);
        }
        //Agregar ranking docentes herramientas
        List<Object[]> topDocentesH = personaRepository.findHerramientasByInstitucion(idInstitucion);
        String [] rankingDocentesH = new String[3];
        topDocentesH.forEach(docente -> {
            rankingDocentesH[Integer.parseInt(docente[0].toString())-1] = docente[0] + " lugar. " + docente[1] + " " + docente[2] + ", "  + docente[3] + ", PPT - "
                    + docente[4] + ", " + docente[5] + " herramientas realizadas";
        });
        String rankingH = "\n" + rankingDocentesH[0] + "\n" + "\n" + rankingDocentesH[1] + "\n" + "\n" + rankingDocentesH[2] + "\n";
        Cell dataDocentesH = dataRowH.createCell(dataRowH.getRowNum()+2);
        dataDocentesH.setCellValue(workbook.getCreationHelper().createRichTextString(rankingH));
        dataDocentesH.setCellStyle(infoStyle);
        sheet.autoSizeColumn(6);




        //Agregar estadísticas de los proyectos -----------------------------------------------------------------------------------------------------
        Map<String, Object> datosProyectos = institucionService.getEstadisticasProyectosInstitucion(idInstitucion);
        Row dataRowP = sheet.createRow(5);
        Cell proyecto = dataRowP.createCell(0);
        proyecto.setCellValue("Proyectos");
        proyecto.setCellStyle(cellStyle);
        String[] pptP = {"ambiental", "sociales", "sexualidad", "emprendimiento", "tic"};
        for (int i = 1; i < datosProyectos.size(); i++){
            Cell dataCell = dataRowP.createCell(i);
            dataCell.setCellValue(datosProyectos.get(pptP[(i)-1].toString()).toString());
            dataCell.setCellStyle(infoStyle);
        }
        //Agregar ranking docentes herramientas
        List<Object[]> topDocentesP = personaRepository.findProyectosByInstitucion(idInstitucion);
        String [] rankingDocentesP = new String[3];
        topDocentesP.forEach(docente -> {
            rankingDocentesP[Integer.parseInt(docente[0].toString())-1] = docente[0] + " lugar. " + docente[1] + " " + docente[2] + ", "  + docente[3] + ", PPT - "
                    + docente[4] + ", " + docente[5] + " proyectos realizados";
        });
        String rankingP = "\n" + rankingDocentesP[0] + "\n" + "\n" + rankingDocentesP[1] + "\n" + "\n" + rankingDocentesP[2] + "\n";
        Cell dataDocentesP = dataRowP.createCell(dataRowP.getRowNum() + 1);
        dataDocentesP.setCellValue(workbook.getCreationHelper().createRichTextString(rankingP));
        dataDocentesP.setCellStyle(infoStyle);
        sheet.autoSizeColumn(6);




        //Agregar estadísticas de los contenidos -----------------------------------------------------------------------------------------------------
        Map<String, Object> datosContenidos = institucionService.getEstadisticasContenidosInstitucion(idInstitucion);
        Row dataRowC = sheet.createRow(6);
        Cell contenido = dataRowC.createCell(0);
        contenido.setCellValue("Contenidos");
        contenido.setCellStyle(cellStyle);
        String[] pptC = {"ambiental", "sociales", "sexualidad", "emprendimiento", "tic"};
        for (int i = 1; i < datosContenidos.size(); i++){
            Cell dataCell = dataRowC.createCell(i);
            dataCell.setCellValue(datosContenidos.get(pptC[(i)-1].toString()).toString());
            dataCell.setCellStyle(infoStyle);
        }
        //Agregar ranking docentes contenidos
        List<Object[]> topDocentesC = personaRepository.findContenidosByInstitucion(idInstitucion);
        String [] rankingDocentesC = new String[3];
        topDocentesC.forEach(docente -> {
            rankingDocentesC[Integer.parseInt(docente[0].toString())-1] = docente[0] + " lugar. " + docente[1] + " " + docente[2] + ", "  + docente[3] + ", PPT - "
                    + docente[4] + ", " + docente[5] + " contenidos realizados";
        });
        String rankingC = "\n" + rankingDocentesC[0] + "\n" + "\n" + rankingDocentesC[1] + "\n" + "\n" + rankingDocentesC[2] + "\n";
        Cell dataDocentesC = dataRowC.createCell(dataRowC.getRowNum());
        dataDocentesC.setCellValue(workbook.getCreationHelper().createRichTextString(rankingC));
        dataDocentesC.setCellStyle(infoStyle);
        sheet.autoSizeColumn(6);


        workbook.write(stream);
        workbook.close();

        return new ByteArrayInputStream(stream.toByteArray());
    }
}
