/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.report.estadisticas;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.config.ConfigApp;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.clases.model.Ensayo;
import quino.clases.model.Paciente;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;
import quino.util.report.AbstractInformeAB;
import quino.util.test.Prueba;
import quino.util.test.PruebaFormaAB;

/**
 *
 * @author farias-i3
 */
public class EstadisticaFormaAB extends AbstractInformeAB {

    public EstadisticaFormaAB(HSSFWorkbook book) {
        super(book);
    }

    @Override
    protected void getEncabezado(HSSFSheet sheet) {
        String[] heads = {"Sujeto", "Ensayo", "Densidad de puntos",
            "% de puntos", "Tiempo de desplazamiento", "Velocidad del movimiento", "Ángulo", "Dirección movimiento",
            "Dirección presionada", "Panel de estímulo",
            "Tiempo de respuesta", "Resultado", "Descripción del error"};
        crearEncabezado(sheet, heads);
    }

    @Override
    public void getInformeExcel() {

        HSSFSheet sheet1 = book.createSheet();
        String str = foveal ? "Parámetros por ensayo - Forma A" : "Parámetros por ensayo - Forma B";
        getTitulo(sheet1, str);
        getEncabezado(sheet1);
        getCuerpo(sheet1);

        rowCount = 0;

        HSSFSheet sheet2 = book.createSheet();
        str = foveal ? "Parámetros generales - Forma A" : "Parámetros generales - Forma B";
        getTitulo(sheet2, str);
        buildSheet2(sheet2);

        rowCount = 0;

        HSSFSheet sheet3 = book.createSheet();
        str = foveal ? "Alfa de Cronbach - Forma A" : "Alfa de Cronbach - Forma B";
        getTitulo(sheet3, str);
        buildSheet3(sheet3);
    }

    protected void buildSheet3(HSSFSheet sheet) {
        String[] heads = {"#", "Sujeto", "Ensayos", "Edad",
            "Sexo", "Grado", "Errores", "Densidad Promedio", "Tiempo de respuesta promedio",
            "Duración del ensayo"};
        crearEncabezado(sheet, heads);
        
    }

    protected void buildSheet2(HSSFSheet sheet) {
        String[] heads = {"#", "Sujeto", "Ensayos", "Edad",
            "Sexo", "Grado", "Errores", "Densidad Promedio", "Tiempo de respuesta promedio",
            "Duración del ensayo"};
        crearEncabezado(sheet, heads);

        List<Paciente> pacientes = registro.getPacientes();

        int count = 1;
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente pacienteAcutal = pacientes.get(i);

            Prueba pruebaX = foveal ? pacienteAcutal.getFoveal() : pacienteAcutal.getPeriferica();

            if (pruebaX != null) {
                HSSFRow row = sheet.createRow(rowCount);
                HSSFCell celda = null;

                celda = getCelda(row, 0, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(count++);

                celda = getCelda(row, 1, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getNombre());

                celda = getCelda(row, 2, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(pruebaX.getEnsayos().size());

                celda = getCelda(row, 3, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getEdad());

                celda = getCelda(row, 4, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getSexo());

                celda = getCelda(row, 5, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getEscolaridad());

                celda = getCelda(row, 6, HSSFCell.CELL_TYPE_NUMERIC, true);
                celda.setCellValue(pruebaX.cantErrores());

                celda = getCelda(row, 7, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(((PruebaFormaAB) pruebaX).densidadPromedio());

                celda = getCelda(row, 8, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(pruebaX.tiempoRespuestaPromedio());

                celda = getCelda(row, 9, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(ConfigApp.TIEMPO_DURACION);
                //celda.setCellValue(((ConfigEnsayoFormaAB) pruebaX.getEnsayos().get(0).getConfiguracion()).getTiempoMovimiento());

                rowCount++;
            }
        }
    }

    @Override
    protected void getCuerpo(HSSFSheet sheet) {
        rowCount++;
        List<Paciente> pacientes = registro.getPacientes();

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente pacienteAcutal = pacientes.get(i);

            Prueba pruebaX = foveal ? pacientes.get(i).getFoveal() : pacientes.get(i).getPeriferica();

            if (pruebaX != null) {
                List<Ensayo> ensayos = pruebaX.getEnsayos();

                for (int j = 0; j < ensayos.size(); j++) {
                    Ensayo ensayoActual = ensayos.get(j);
                    Resultado resultadoActual = ensayoActual.getResultado();

                    ConfigEnsayoFormaAB configEnsayoActual = null;
                    if (ensayos.get(j).getConfiguracion() instanceof ConfigEnsayoFormaAB) {
                        configEnsayoActual = ((ConfigEnsayoFormaAB) ensayos.get(j).getConfiguracion());
                    }

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
                    celda.setCellValue(configEnsayoActual.getTiempoMovimiento());

                    celda = getCelda(row, 4, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(resultadoActual.getVelocidad());

                    celda = getCelda(row, 5, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getCantidad());

                    celda = getCelda(row, 6, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(resultadoActual.getAngulo());

                    celda = getCelda(row, 7, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getDireccion());

                    celda = getCelda(row, 8, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(resultadoActual.getKey());

                    celda = getCelda(row, 9, HSSFCell.CELL_TYPE_STRING, false);
                    celda.setCellValue(QuinoTools.getPanelMovimiento(pruebaX, configEnsayoActual.getPanelEstimulo()));

                    if (resultadoActual.getTiempoRespuesta() == 0) {
                        celda = getCelda(row, 10, HSSFCell.CELL_TYPE_STRING, true);
                        celda.setCellValue("N/R");
                    } else {
                        celda = getCelda(row, 10, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(resultadoActual.getTiempoRespuesta());
                    }

                    if (resultadoActual.isError()) {
                        celda = getCelda(row, 11, HSSFCell.CELL_TYPE_STRING, true);
                        celda.setCellValue("Error");
                    } else {
                        celda = getCelda(row, 11, HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue("OK");
                    }

                    celda = getCelda(row, 12, HSSFCell.CELL_TYPE_STRING, true);
                    celda.setCellValue(resultadoActual.isError() ? resultadoActual.getDescripcion() : "");

                    rowCount++;
                }
                sheet.createRow(rowCount);
                rowCount++;
            }
        }
    }
}
