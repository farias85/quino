/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import java.io.File;
import java.io.FileNotFoundException;
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
import quino.clases.config.IConfiguracion;
import quino.clases.model.Paciente;
import quino.clases.model.Prueba;
import quino.clases.model.Registro;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;

/**
 *
 * @author farias
 */
public class InformeExcel {

    private HSSFWorkbook book = new HSSFWorkbook();
    private int rowCount;

    public InformeExcel() {
    }

    public void getInformeExcel() {
        try {
            HSSFSheet sheet1 = book.createSheet();
            getTitulo(sheet1, "Parámetros x ensayo - Foveal");
            getEncabezado(sheet1);
            getCuerpo(sheet1, true);

            rowCount = 0;
            HSSFSheet sheet2 = book.createSheet();
            getTitulo(sheet2, "Parámetros x ensayo - Periférica");
            getEncabezado(sheet2);
            getCuerpo(sheet2, false);

            String strNombreArchivo = "C:/libro1.xls";
            File objFile = new File(strNombreArchivo);
            FileOutputStream archivoSalida;
            archivoSalida = new FileOutputStream(objFile);

            book.write(archivoSalida);
            archivoSalida.close();
        } catch (Exception ex) {
            Logger.getLogger(InformeExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getTitulo(HSSFSheet sheet, String titulo) {
        HSSFRow rowTitle1 = sheet.createRow(rowCount++);
        HSSFCell cellTitle1 = rowTitle1.createCell(4);
        cellTitle1.setCellStyle(getStyleTitulo());
        cellTitle1.setCellValue(titulo);
    }

    private void getEncabezado(HSSFSheet sheet) {

        String[] heads = {"Sujeto", "Ensayo", "Densidad de puntos", "% de puntos", "Velocidad del movimiento", "Panel de estímulo", "Tiempo de respuesta"};
        HSSFRow rowHead1 = sheet.createRow(rowCount++);

        for (int i = 0; i < heads.length; i++) {
            sheet.setColumnWidth(i, QuinoTools.getColumnWidthSize(20));

            HSSFCell cellHead1 = rowHead1.createCell(i);
            cellHead1.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellHead1.setCellStyle(getStyleCabecera());
            cellHead1.setCellValue(heads[i]);
        }
    }

    private HSSFCell getCelda(HSSFRow row, int numRow, int cellType, boolean error) {
        HSSFCell celda = row.createCell(numRow);
        celda.setCellType(cellType);
        if (error) {
            celda.setCellStyle(getStyleDatoError());
        } else {
            celda.setCellStyle(getStyleDato());
        }
        return celda;
    }

    private void getCuerpo(HSSFSheet sheet, boolean foveal) {
        try {
            Registro registro = Registro.cargarRegistro(IConfiguracion.REGISTRO_FILE_NAME);

            rowCount++;
            List<Paciente> pacientes = registro.getPacientes();

            for (int i = 0; i < pacientes.size(); i++) {
                Paciente pacienteAcutal = pacientes.get(i);

                Prueba pruebaX = foveal ? pacientes.get(i).getFoveal() : pacientes.get(i).getPeriferica();

                if (pruebaX != null) {
                    List<Resultado> resultados = pruebaX.getResultados();

                    for (int j = 0; j < resultados.size(); j++) {
                        Resultado resultadoActual = resultados.get(j);

                        HSSFRow row = sheet.createRow(rowCount);
                        HSSFCell celda = null;

                        if (j == 0) {
                            celda = getCelda(row, 0, HSSFCell.CELL_TYPE_STRING, false);
                            celda.setCellValue(pacienteAcutal.getNombre());
                        }

                        celda = getCelda(row, 1, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(j + 1);

                        celda = getCelda(row, 2, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(resultadoActual.getDensidad());

                        celda = getCelda(row, 3, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(resultadoActual.getCantPuntos());

                        celda = getCelda(row, 4, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(resultadoActual.getVelocidadMovimiento());

                        celda = getCelda(row, 5, HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue(QuinoTools.getPanelMovimiento(pruebaX, resultadoActual.getPanelEstimulo()));

                        if (resultadoActual.getTiempoRespuesta() == 0) {
                            celda = getCelda(row, 6, HSSFCell.CELL_TYPE_STRING, true);
                            celda.setCellValue("N/R");
                        } else {
                            celda = getCelda(row, 6, HSSFCell.CELL_TYPE_NUMERIC, false);
                            celda.setCellValue(resultadoActual.getTiempoRespuesta());
                        }

                        rowCount++;
                    }
                    sheet.createRow(rowCount);
                    rowCount++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(InformeExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InformeExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    private int incrementRowCount() {
        int aux = rowCount;
        rowCount++;
        return aux;
    }

    private HSSFFont getFontTitulo() {
        HSSFFont fontTitulo = book.createFont();
        fontTitulo.setFontHeightInPoints((short) 11);
        fontTitulo.setFontName(HSSFFont.FONT_ARIAL);
        fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return fontTitulo;
    }

    private HSSFCellStyle getStyleTitulo() {
        HSSFCellStyle styleTitulo = book.createCellStyle();
        styleTitulo.setFont(getFontTitulo());
        return styleTitulo;
    }

    private HSSFFont getFontCabecera() {
        HSSFFont fontCabecera = book.createFont();
        fontCabecera.setFontHeightInPoints((short) 9);
        fontCabecera.setFontName(HSSFFont.FONT_ARIAL);
        fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontCabecera.setItalic(true);
        return fontCabecera;
    }

    private HSSFCellStyle getStyleCabecera() {
        HSSFCellStyle styleCabecera = book.createCellStyle();
        styleCabecera.setWrapText(true);
        styleCabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleCabecera.setFont(getFontCabecera());
        return styleCabecera;
    }

    private HSSFCellStyle getStyleDato() {
        HSSFCellStyle styleDato = book.createCellStyle();
        styleDato.setWrapText(true);
        styleDato.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return styleDato;
    }

    private HSSFFont getFontDatoError() {
        HSSFFont fontDatoError = book.createFont();
        fontDatoError.setFontHeightInPoints((short) 9);
        fontDatoError.setColor(HSSFFont.COLOR_RED);
        fontDatoError.setFontName(HSSFFont.FONT_ARIAL);
        fontDatoError.setItalic(true);
        return fontDatoError;
    }

    private HSSFCellStyle getStyleDatoError() {
        HSSFCellStyle styleDatoError = book.createCellStyle();
        styleDatoError.setWrapText(true);
        styleDatoError.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleDatoError.setFont(getFontDatoError());
        return styleDatoError;
    }
}
