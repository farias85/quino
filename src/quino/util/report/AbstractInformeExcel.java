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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.config.ConfigApp;
import quino.clases.model.Registro;
import quino.util.QuinoTools;

public abstract class AbstractInformeExcel {

    protected Registro registro = Registro.cargarRegistro(ConfigApp.REGISTRO_FILE_NAME);
    protected HSSFWorkbook book;
    protected int rowCount = 0;
    private HSSFFont fontTitulo;
    private HSSFCellStyle styleTitulo;
    private HSSFFont fontCabecera;
    private HSSFCellStyle styleCabecera;
    private HSSFCellStyle styleDato;
    private HSSFFont fontDatoError;
    private HSSFCellStyle styleDatoError;

    public AbstractInformeExcel(HSSFWorkbook book) {
        this.book = book;

        fontTitulo = book.createFont();
        fontTitulo.setFontHeightInPoints((short) 11);
        fontTitulo.setFontName(HSSFFont.FONT_ARIAL);
        fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        styleTitulo = book.createCellStyle();
        styleTitulo.setFont(fontTitulo);

        fontCabecera = book.createFont();
        fontCabecera.setFontHeightInPoints((short) 9);
        fontCabecera.setFontName(HSSFFont.FONT_ARIAL);
        fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontCabecera.setItalic(true);

        styleCabecera = book.createCellStyle();
        styleCabecera.setWrapText(true);
        styleCabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleCabecera.setFont(fontCabecera);

        styleDato = book.createCellStyle();
        styleDato.setWrapText(true);
        styleDato.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        fontDatoError = book.createFont();
        fontDatoError.setFontHeightInPoints((short) 9);
        fontDatoError.setColor(HSSFFont.COLOR_RED);
        fontDatoError.setFontName(HSSFFont.FONT_ARIAL);
        fontDatoError.setItalic(true);

        styleDatoError = book.createCellStyle();
        styleDatoError.setWrapText(true);
        styleDatoError.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleDatoError.setFont(fontDatoError);
    }

    public abstract void getInformeExcel();

    protected void getTitulo(HSSFSheet sheet, String titulo) {
        HSSFRow rowTitle1 = sheet.createRow(rowCount++);
        HSSFCell cellTitle1 = rowTitle1.createCell(4);
        cellTitle1.setCellStyle(getStyleTitulo());
        cellTitle1.setCellValue(titulo);
    }

    protected abstract void getEncabezado(HSSFSheet sheet);

    protected void crearEncabezado(HSSFSheet sheet, String heads[]) {
        HSSFRow rowHead1 = sheet.createRow(rowCount++);

        for (int i = 0; i < heads.length; i++) {
            sheet.setColumnWidth(i, QuinoTools.getColumnWidthSize(20));

            HSSFCell cellHead1 = rowHead1.createCell(i);
            cellHead1.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellHead1.setCellStyle(getStyleCabecera());
            cellHead1.setCellValue(heads[i]);
        }
    }

    protected abstract void getCuerpo(HSSFSheet sheet);

    protected HSSFCell getCelda(HSSFRow row, int numRow, int cellType, boolean error) {
        HSSFCell celda = row.createCell(numRow);
        celda.setCellType(cellType);
        if (error) {
            celda.setCellStyle(getStyleDatoError());
        } else {
            celda.setCellStyle(getStyleDato());
        }
        return celda;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    protected HSSFFont getFontTitulo() {
        return fontTitulo;
    }

    protected HSSFCellStyle getStyleTitulo() {
        return styleTitulo;
    }

    protected HSSFFont getFontCabecera() {
        return fontCabecera;
    }

    protected HSSFCellStyle getStyleCabecera() {
        return styleCabecera;
    }

    protected HSSFCellStyle getStyleDato() {
        return styleDato;
    }

    protected HSSFFont getFontDatoError() {
        return fontDatoError;
    }

    protected HSSFCellStyle getStyleDatoError() {
        return styleDatoError;
    }
}
