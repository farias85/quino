/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.report;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
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
public class InformeParametrosXEnsayo extends InformeExcel {

    public InformeParametrosXEnsayo(HSSFWorkbook book) {
        super(book);
    }

    public void getInformeExcel() {

        HSSFSheet sheet1 = book.createSheet();
        getTitulo(sheet1, "Parámetros x ensayo - Foveal");
        getEncabezado(sheet1);
        getCuerpo(sheet1, true);

        rowCount = 0;
        HSSFSheet sheet2 = book.createSheet();
        getTitulo(sheet2, "Parámetros x ensayo - Periférica");
        getEncabezado(sheet2);
        getCuerpo(sheet2, false);
    }

    protected void getTitulo(HSSFSheet sheet, String titulo) {
        HSSFRow rowTitle1 = sheet.createRow(rowCount++);
        HSSFCell cellTitle1 = rowTitle1.createCell(4);
        cellTitle1.setCellStyle(getStyleTitulo());
        cellTitle1.setCellValue(titulo);
    }

    protected void getEncabezado(HSSFSheet sheet) {

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

    protected void getCuerpo(HSSFSheet sheet, boolean foveal) {
        try {
            Registro registro = Registro.cargarRegistro(ConfigApp.REGISTRO_FILE_NAME);

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
}
