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
import quino.util.test.PruebaFormaA;
import quino.util.QuinoTools;
import quino.clases.model.Ensayo;

public class InformeCampoVisual extends AbstractInformeAB {

    public InformeCampoVisual(HSSFWorkbook book) {
        super(book);
    }

    @Override
    public void getInformeExcel() {
        rowCount = 0;
        
        HSSFSheet sheet1 = book.createSheet();
        getTitulo(sheet1, "Posición Campo Visual - Foveal");
        getEncabezado(sheet1);
        foveal = true;
        getCuerpo(sheet1);
    }

    @Override
    protected void getEncabezado(HSSFSheet sheet) {
        String[] heads = new String[9];
        heads[0] = "Nombre";
        for (int i = 1; i < heads.length; i++) {
            heads[i] = QuinoTools.getPanelMovimiento(new PruebaFormaA(), i);
        }
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

                    HSSFRow row = sheet.createRow(rowCount);
                    HSSFCell celda = null;

                    if (j == 0) {
                        celda = getCelda(row, 0, HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue(pacienteAcutal.getNombre());
                    }

                    if (ensayoActual.getConfiguracion().getPanelEstimulo() != 0) {
                        celda = getCelda(row, ensayoActual.getConfiguracion().getPanelEstimulo(), HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue("X");
                    }
                    rowCount++;
                }
                sheet.createRow(rowCount);
                rowCount++;
            }
        }
    }
}
