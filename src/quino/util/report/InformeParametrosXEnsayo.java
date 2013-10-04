/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.report;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.model.Paciente;
import quino.util.test.Prueba;
import quino.clases.model.Ensayo;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;
import quino.clases.config.ConfigEnsayo;

/**
 *
 * @author farias
 */
public class InformeParametrosXEnsayo extends AbstractInformeExcel {

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

    protected void getEncabezado(HSSFSheet sheet) {
        String[] heads = {"Sujeto", "Ensayo", "Densidad de puntos",
            "% de puntos", "Velocidad del movimiento", "Panel de estímulo",
            "Tiempo de respuesta"};
        crearEncabezado(sheet, heads);
    }

    protected void getCuerpo(HSSFSheet sheet, boolean foveal) {
        rowCount++;
        List<Paciente> pacientes = registro.getPacientes();

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente pacienteAcutal = pacientes.get(i);

            Prueba pruebaX = foveal ? pacientes.get(i).getFoveal() : pacientes.get(i).getPeriferica();

            if (pruebaX != null) {
                List<Ensayo> ensayos = pruebaX.getEnsayos();

                for (int j = 0; j < ensayos.size(); j++) {
                    Ensayo ensayoActual = ensayos.get(j);
                    Resultado resultadoActual = ensayos.get(j).getResultado();
                    ConfigEnsayo configEnsayoActual = ensayos.get(j).getConfiguracion();

                    HSSFRow row = sheet.createRow(rowCount);
                    HSSFCell celda = null;

                    if (j == 0) {
                        celda = getCelda(row, 0, HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue(pacienteAcutal.getNombre());
                    }

                    celda = getCelda(row, 1, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(j + 1);

                    celda = getCelda(row, 2, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getDensidad());

                    celda = getCelda(row, 3, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getCantidad());

                    celda = getCelda(row, 4, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getTiempoMovimiento());

                    celda = getCelda(row, 5, HSSFCell.CELL_TYPE_STRING, false);
                    celda.setCellValue(QuinoTools.getPanelMovimiento(pruebaX, ensayoActual.getPanelEstimulo()));

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
    }
}
