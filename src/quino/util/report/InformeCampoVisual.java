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
import quino.clases.model.Prueba;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;

/**
 *
 * @author farias
 */
public class InformeCampoVisual extends AbstractInformeExcel {

    public InformeCampoVisual(HSSFWorkbook book) {
        super(book);
    }

    @Override
    public void getInformeExcel() {
        rowCount = 0;
        
        HSSFSheet sheet1 = book.createSheet();
        getTitulo(sheet1, "Posición Campo Visual - Foveal");
        getEncabezado(sheet1);
        getCuerpo(sheet1, true);
    }

    @Override
    protected void getEncabezado(HSSFSheet sheet) {
        String[] heads = new String[9];
        heads[0] = "Nombre";
        for (int i = 1; i < heads.length; i++) {
            heads[i] = QuinoTools.getPanelMovimiento(new Prueba(1, true), i);
        }
        crearEncabezado(sheet, heads);
    }

    @Override
    protected void getCuerpo(HSSFSheet sheet, boolean foveal) {
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

                    if (resultadoActual.getPanelEstimulo() != 0) {
                        celda = getCelda(row, resultadoActual.getPanelEstimulo(), HSSFCell.CELL_TYPE_STRING, false);
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
