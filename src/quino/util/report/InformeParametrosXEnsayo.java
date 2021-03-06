/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 24/05/2014.
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
import quino.clases.config.ConfigEnsayoFormaAB;

public class InformeParametrosXEnsayo extends AbstractInformeAB {

    public InformeParametrosXEnsayo(HSSFWorkbook book) {
        super(book);
    }

    @Override
    public void getInformeExcel() {

        HSSFSheet sheet1 = book.createSheet();
        getTitulo(sheet1, "Parámetros x ensayo - Foveal");
        getEncabezado(sheet1);
        foveal = true;
        getCuerpo(sheet1);

        rowCount = 0;

        HSSFSheet sheet2 = book.createSheet();
        getTitulo(sheet2, "Parámetros x ensayo - Periférica");
        getEncabezado(sheet2);
        foveal = false;
        getCuerpo(sheet2);
    }

    @Override
    protected void getEncabezado(HSSFSheet sheet) {
        String[] heads = {"Sujeto", "Ensayo", "Densidad de puntos",
            "% de puntos", "Velocidad del movimiento", "Panel de estímulo",
            "Tiempo de respuesta"};
        crearEncabezado(sheet, heads);
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
                    Resultado resultadoActual = ensayos.get(j).getResultado();

                    if (ensayos.get(j).getConfiguracion() instanceof ConfigEnsayoFormaAB) {
                        ConfigEnsayoFormaAB configEnsayoActual = ((ConfigEnsayoFormaAB) ensayos.get(j).getConfiguracion());

                        HSSFRow row = sheet.createRow(rowCount);
                        HSSFCell celda;

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
                        celda.setCellValue(QuinoTools.getPanelMovimiento(pruebaX, ensayoActual.getConfiguracion().getPanelEstimulo()));

                        if (resultadoActual.getTiempoRespuesta() == 0) {
                            celda = getCelda(row, 6, HSSFCell.CELL_TYPE_STRING, true);
                            celda.setCellValue("N/R");
                        } else {
                            celda = getCelda(row, 6, HSSFCell.CELL_TYPE_NUMERIC, false);
                            celda.setCellValue(resultadoActual.getTiempoRespuesta());
                        }

                        rowCount++;
                    }
                }
                sheet.createRow(rowCount);
                rowCount++;
            }
        }
    }
}
