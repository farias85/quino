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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 15/07/2014.
 */
package quino.util.report.estadisticas;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.config.ConfigApp;
import quino.clases.config.ConfigEnsayoEnrejado;
import quino.clases.model.Ensayo;
import quino.clases.model.Paciente;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;
import quino.util.report.AbstractInformeExcel;
import quino.util.test.Prueba;

public class EstadisticaEnrejado extends AbstractInformeExcel {

    private final FrecuenciaEspacial FE;

    public EstadisticaEnrejado(HSSFWorkbook book, FrecuenciaEspacial FE) {
        super(book);
        this.FE = FE;
    }

    @Override
    protected void getEncabezado(HSSFSheet sheet) {
        String[] heads = {"Sujeto", "Ensayo", "Frecuencia Espacial",
            "Contraste", "Intensidad Media", "Dirección del Movimiento",
            "Tiempo de respuesta (ms)", "Resultado", "Descripción del error"};
        crearEncabezado(sheet, heads);
    }

    private List<Ensayo> ensayosXFrecuencia(List<Ensayo> ensayosAux) {
        List<Ensayo> ensayos = new ArrayList<Ensayo>();

        if (this.FE != FrecuenciaEspacial.ALL) {
            for (Ensayo ensayo : ensayosAux) {
                if (ensayo.getConfiguracion() instanceof ConfigEnsayoEnrejado) {
                    ConfigEnsayoEnrejado config = ((ConfigEnsayoEnrejado) ensayo.getConfiguracion());
                    switch (FE) {
                        case HIGH:
                            if (config.getPpi() >= 25) {
                                ensayos.add(ensayo);
                            }
                            break;
                        case LOW:
                            if (config.getPpi() <= 6) {
                                ensayos.add(ensayo);
                            }
                            break;
                    }
                }
            }
        } else {
            ensayos = new ArrayList<Ensayo>(ensayosAux);
        }

        return ensayos;
    }

    @Override
    protected void getCuerpo(HSSFSheet sheet) {
        rowCount++;
        List<Paciente> pacientes = registro.getPacientes();

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente pacienteAcutal = pacientes.get(i);

            Prueba pruebaX = pacientes.get(i).getEnrejado();

            if (pruebaX != null) {
                List<Ensayo> ensayos = ensayosXFrecuencia(pruebaX.getEnsayos());

                for (int j = 0; j < ensayos.size(); j++) {
                    Ensayo ensayoActual = ensayos.get(j);
                    Resultado resultadoActual = ensayoActual.getResultado();

                    ConfigEnsayoEnrejado configEnsayoActual = null;
                    if (ensayos.get(j).getConfiguracion() instanceof ConfigEnsayoEnrejado) {
                        configEnsayoActual = ((ConfigEnsayoEnrejado) ensayos.get(j).getConfiguracion());
                    }

                    HSSFRow row = sheet.createRow(rowCount);
                    HSSFCell celda = null;

                    if (j == 0) {
                        celda = getCelda(row, 0, HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue(pacienteAcutal.getNombre());
                    }

                    int colNum = 1;
                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(j + 1);

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(QuinoTools.getPPiXFrecuenciaEspacial(configEnsayoActual.getPpi()));

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getContrat());

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getIntensidadMedia());

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(QuinoTools.getDireccion(configEnsayoActual.getDireccion()));

                    if (resultadoActual.isError()) {
                        celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, true);
                        celda.setCellValue("N/R");

                        celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, true);
                        celda.setCellValue("Error");
                    } else {
                        celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(resultadoActual.getTiempoRespuesta());

                        celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue("OK");
                    }

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, true);
                    celda.setCellValue(resultadoActual.isError() ? resultadoActual.getDescripcion() : "");

                    rowCount++;
                }
                sheet.createRow(rowCount);
                rowCount++;
            }
        }
    }

    @Override
    public void getInformeExcel() {

        HSSFSheet sheet1 = book.createSheet("Parámetros por ensayo");
        String str = "Enrejado sinusoidal ";

        switch (FE) {
            case HIGH:
                str += "| FE HIGH";
                break;
            case LOW:
                str += "| FE LOW";
                break;
        }

        getTitulo(sheet1, str);
        getEncabezado(sheet1);
        getCuerpo(sheet1);

        rowCount = 0;

        HSSFSheet sheet2 = book.createSheet("Parámetros generales");
        getTitulo(sheet2, str);
        buildSheet2(sheet2);

        rowCount = 0;

        HSSFSheet sheet3 = book.createSheet("Alfa de Cronbach");
        getTitulo(sheet3, str);
        buildSheet3(sheet3);
    }

    protected void buildSheet2(HSSFSheet sheet) {

        String[] heads = {"#", "Sujeto", "Ensayos", "Edad",
            "Sexo", "Grado", "Errores", "Tiempo de respuesta promedio (ms)",
            "Duración del ensayo (ms)"};
        crearEncabezado(sheet, heads);

        List<Paciente> pacientes = registro.getPacientes();

        int count = 1;
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente pacienteAcutal = pacientes.get(i);

            Prueba pruebaX = pacientes.get(i).getEnrejado();

            if (pruebaX != null) {
                HSSFRow row = sheet.createRow(rowCount);
                HSSFCell celda = null;

                int colNum = 0;
                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(count++);

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getNombre());

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(pruebaX.getEnsayos().size());

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getEdad());

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getSexo());

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_STRING, false);
                celda.setCellValue(pacienteAcutal.getEscolaridad());

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, true);
                celda.setCellValue(pruebaX.cantErrores());

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(pruebaX.tiempoRespuestaPromedio());

                celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                celda.setCellValue(ConfigApp.TIEMPO_DURACION);

                rowCount++;
            }
        }
    }

    protected void buildSheet3(HSSFSheet sheet) {

        ArrayList<double[]> data = new ArrayList<double[]>();

        String[] heads = {"#", "Sujeto", "Ensayos", "Frecuencia Espacial",
            "Dirección del Movimiento", "Resultado"};
        crearEncabezado(sheet, heads);

        rowCount++;
        List<Paciente> pacientes = registro.getPacientes();

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente pacienteAcutal = pacientes.get(i);

            Prueba pruebaX = pacientes.get(i).getEnrejado();

            if (pruebaX != null) {
                List<Ensayo> ensayos = ensayosXFrecuencia(pruebaX.getEnsayos());

                for (int j = 0; j < ensayos.size(); j++) {
                    Ensayo ensayoActual = ensayos.get(j);
                    Resultado resultadoActual = ensayoActual.getResultado();

                    ConfigEnsayoEnrejado configEnsayoActual = null;
                    if (ensayos.get(j).getConfiguracion() instanceof ConfigEnsayoEnrejado) {
                        configEnsayoActual = ((ConfigEnsayoEnrejado) ensayos.get(j).getConfiguracion());
                    }

                    HSSFRow row = sheet.createRow(rowCount);
                    HSSFCell celda = null;

                    if (j == 0) {
                        celda = getCelda(row, 0, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(i + 1);

                        celda = getCelda(row, 1, HSSFCell.CELL_TYPE_STRING, false);
                        celda.setCellValue(pacienteAcutal.getNombre());
                    }

                    int colNum = 2;
                    int k = 0;
                    double[] values = new double[heads.length - colNum];

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(j + 1);
                    values[k++] = ensayos.size();

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(QuinoTools.getPPiXFrecuenciaEspacial(configEnsayoActual.getPpi()));
                    values[k++] = QuinoTools.getPPiXFrecuenciaEspacial(configEnsayoActual.getPpi());

                    celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                    celda.setCellValue(configEnsayoActual.getDireccion());
                    values[k++] = configEnsayoActual.getDireccion();

                    if (resultadoActual.isError()) {
                        celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, true);
                        celda.setCellValue(0);
                        values[k++] = 0;
                    } else {
                        celda = getCelda(row, colNum++, HSSFCell.CELL_TYPE_NUMERIC, false);
                        celda.setCellValue(1);
                        values[k++] = 1;
                    }

                    data.add(values);

                    rowCount++;
                }
                sheet.createRow(rowCount);
                rowCount++;
            }
        }

        rowCount++;
        HSSFRow row = sheet.createRow(rowCount);

        HSSFCell celda = getCelda(row, 1, HSSFCell.CELL_TYPE_STRING, false);
        celda.setCellValue("Alpha de Cronbach");

        celda = getCelda(row, 2, HSSFCell.CELL_TYPE_NUMERIC, false);
        celda.setCellValue(QuinoTools.calcAlphaCronbach(data));

    }

}
