/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.config.ConfigApp;
import quino.clases.model.Paciente;
import quino.clases.model.Prueba;
import quino.clases.model.Registro;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;

/**
 *
 * @author farias
 */
public class Main2 {

    public static void main(String[] args) {
        /*try {
            Registro registro = Registro.cargarRegistro(ConfigApp.REGISTRO_FILE_NAME);
            String heads[] = {"Sujeto", "Ensayo", "Densidad de puntos", "% de puntos", "Velocidad del movimiento", "Panel de estímulo", "Tiempo de respuesta"};

            HSSFWorkbook book = new HSSFWorkbook();
            HSSFSheet sheet1 = book.createSheet("Foveal");
            HSSFSheet sheet2 = book.createSheet("Periférico");

            HSSFFont font = book.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

            HSSFCellStyle cellStyle1 = book.createCellStyle();
            cellStyle1.setFont(font);

            HSSFCellStyle cellStyle2 = book.createCellStyle();
            cellStyle2.setWrapText(true);
            cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFFont font3 = book.createFont();
            font3.setFontHeightInPoints((short) 9);
            font3.setFontName(HSSFFont.FONT_ARIAL);
            font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font3.setItalic(true);

            HSSFCellStyle cellStyle3 = book.createCellStyle();
            cellStyle3.setWrapText(true);
            cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle3.setFont(font3);

            HSSFFont font4 = book.createFont();
            font4.setFontHeightInPoints((short) 9);
            font4.setColor(HSSFFont.COLOR_RED);
            font4.setFontName(HSSFFont.FONT_ARIAL);
            font4.setItalic(true);

            HSSFCellStyle cellStyle4 = book.createCellStyle();
            cellStyle4.setWrapText(true);
            cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle4.setFont(font4);

            HSSFRow rowTitle1 = sheet1.createRow(0);
            HSSFCell cellTitle1 = rowTitle1.createCell(4);
            cellTitle1.setCellStyle(cellStyle1);
            cellTitle1.setCellValue("Parámetros x ensayo - Foveal");

            HSSFRow rowTitle2 = sheet2.createRow(0);
            HSSFCell cellTitle2 = rowTitle2.createCell(4);
            cellTitle2.setCellStyle(cellStyle1);
            cellTitle2.setCellValue("Parámetros x ensayo - Periférica");

            HSSFRow rowHead1 = sheet1.createRow(1);
            HSSFRow rowHead2 = sheet2.createRow(1);

            for (int i = 0; i < heads.length; i++) {
                sheet1.setColumnWidth(i, QuinoTools.getColumnWidthSize(20));
                sheet2.setColumnWidth(i, QuinoTools.getColumnWidthSize(20));

                HSSFCell cellHead1 = rowHead1.createCell(i);
                cellHead1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cellHead1.setCellStyle(cellStyle3);
                cellHead1.setCellValue(heads[i]);

                HSSFCell cellHead2 = rowHead2.createCell(i);
                cellHead2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cellHead2.setCellStyle(cellStyle3);
                cellHead2.setCellValue(heads[i]);
            }

            int rowCount = 2;
            List<Paciente> pacientes = registro.getPacientes();

            for (int i = 0; i < pacientes.size(); i++) {
                Paciente pacienteAcutal = pacientes.get(i);

                Prueba foveal = pacientes.get(i).getFoveal();

                if (foveal != null) {
                    List<Resultado> resultados = foveal.getResultados();

                    for (int j = 0; j < resultados.size(); j++) {
                        Resultado resultadoActual = resultados.get(j);

                        HSSFRow row = sheet1.createRow(rowCount);
                        HSSFCell cell = null;

                        if (j == 0) {
                            cell = row.createCell(0);
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(cellStyle2);
                            cell.setCellValue(pacienteAcutal.getNombre());
                        }

                        cell = row.createCell(1);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(j + 1);

                        cell = row.createCell(2);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(resultadoActual.getDensidad());

                        cell = row.createCell(3);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(resultadoActual.getCantPuntos());

                        cell = row.createCell(4);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(resultadoActual.getVelocidadMovimiento());

                        cell = row.createCell(5);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(QuinoTools.getPanelMovimiento(foveal, resultadoActual.getPanelEstimulo()));

                        if (resultadoActual.getTiempoRespuesta() == 0) {
                            cell = row.createCell(6);
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(cellStyle4);
                            cell.setCellValue("N/R");
                        } else {
                            cell = row.createCell(6);
                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellStyle(cellStyle2);
                            cell.setCellValue(resultadoActual.getTiempoRespuesta());
                        }



                        rowCount++;
                    }
                    sheet1.createRow(rowCount);
                    rowCount++;
                }

                //PRUEBA PERIFERICA
                rowCount = 2;
                Prueba periferica = pacientes.get(i).getPeriferica();

                if (periferica != null) {
                    List<Resultado> resultados = periferica.getResultados();

                    for (int j = 0; j < resultados.size(); j++) {
                        Resultado resultadoActual = resultados.get(j);

                        HSSFRow row = sheet2.createRow(rowCount);
                        HSSFCell cell = null;

                        if (j == 0) {
                            cell = row.createCell(0);
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(cellStyle2);
                            cell.setCellValue(pacienteAcutal.getNombre());
                        }

                        cell = row.createCell(1);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(j + 1);

                        cell = row.createCell(2);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(resultadoActual.getDensidad());

                        cell = row.createCell(3);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(resultadoActual.getCantPuntos());

                        cell = row.createCell(4);
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(resultadoActual.getVelocidadMovimiento());

                        cell = row.createCell(5);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(QuinoTools.getPanelMovimiento(foveal, resultadoActual.getPanelEstimulo()));

                        if (resultadoActual.getTiempoRespuesta() == 0) {
                            cell = row.createCell(6);
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(cellStyle4);
                            cell.setCellValue("N/R");
                        } else {
                            cell = row.createCell(6);
                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellStyle(cellStyle2);
                            cell.setCellValue(resultadoActual.getTiempoRespuesta());
                        }



                        rowCount++;
                    }
                    sheet2.createRow(rowCount);
                    rowCount++;
                }
            }

            String strNombreArchivo = "C:/libro1.xls";
            File objFile = new File(strNombreArchivo);
            FileOutputStream archivoSalida;

            archivoSalida = new FileOutputStream(objFile);
            book.write(archivoSalida);
            archivoSalida.close();

        } catch (IOException ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        } */

    }

    public static void getInforme1() {
        // Proceso la información y genero el xls.
        HSSFWorkbook objLibro = new HSSFWorkbook();

// Creo la hoja
        HSSFSheet hoja1 = objLibro.createSheet("hoja 1");

// creo la fila.
        HSSFRow objFila = hoja1.createRow((short) 1);

// Aunque no es necesario podemos establecer estilos a las celdas.
// Primero, establecemos el tipo de fuente
        HSSFFont fuente = objLibro.createFont();
        fuente.setFontHeightInPoints((short) 11);
        fuente.setFontName(HSSFFont.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

// Luego creamos el objeto que se encargará de aplicar el estilo a la celda
        HSSFCellStyle estiloCelda = objLibro.createCellStyle();
        estiloCelda.setWrapText(true);
        estiloCelda.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        estiloCelda.setFont(fuente);



// También, podemos establecer bordes...
        estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setBottomBorderColor((short) 8);
        estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setLeftBorderColor((short) 8);
        estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setRightBorderColor((short) 8);
        estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setTopBorderColor((short) 8);

// Establecemos el tipo de sombreado de nuestra celda
        estiloCelda.setFillForegroundColor((short) 22);
        estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

// Creamos la celda, aplicamos el estilo y definimos
// el tipo de dato que contendrá la celda
        HSSFCell celda = objFila.createCell((short) 0);
        celda.setCellStyle(estiloCelda);
        celda.setCellType(HSSFCell.CELL_TYPE_STRING);

// Finalmente, establecemos el valor
        celda.setCellValue("Un valor");

        // Volcamos la información a un archivo.
        String strNombreArchivo = "C:/libro1.xls";
        File objFile = new File(strNombreArchivo);
        FileOutputStream archivoSalida;
        try {
            archivoSalida = new FileOutputStream(objFile);
            objLibro.write(archivoSalida);
            archivoSalida.close();

        } catch (Exception ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
