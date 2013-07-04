/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.report;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author farias
 */
public abstract class InformeExcel {

    protected HSSFWorkbook book;
    protected int rowCount;

    public InformeExcel(HSSFWorkbook book) {
        this.book = book;
    }

    public abstract void getInformeExcel();
    protected abstract void getTitulo(HSSFSheet sheet, String titulo);
    protected abstract void getEncabezado(HSSFSheet sheet);
    protected abstract void getCuerpo(HSSFSheet sheet, boolean foveal);

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
        HSSFFont fontTitulo = book.createFont();
        fontTitulo.setFontHeightInPoints((short) 11);
        fontTitulo.setFontName(HSSFFont.FONT_ARIAL);
        fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return fontTitulo;
    }

    protected HSSFCellStyle getStyleTitulo() {
        HSSFCellStyle styleTitulo = book.createCellStyle();
        styleTitulo.setFont(getFontTitulo());
        return styleTitulo;
    }

    protected HSSFFont getFontCabecera() {
        HSSFFont fontCabecera = book.createFont();
        fontCabecera.setFontHeightInPoints((short) 9);
        fontCabecera.setFontName(HSSFFont.FONT_ARIAL);
        fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontCabecera.setItalic(true);
        return fontCabecera;
    }

    protected HSSFCellStyle getStyleCabecera() {
        HSSFCellStyle styleCabecera = book.createCellStyle();
        styleCabecera.setWrapText(true);
        styleCabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleCabecera.setFont(getFontCabecera());
        return styleCabecera;
    }

    protected HSSFCellStyle getStyleDato() {
        HSSFCellStyle styleDato = book.createCellStyle();
        styleDato.setWrapText(true);
        styleDato.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return styleDato;
    }

    protected HSSFFont getFontDatoError() {
        HSSFFont fontDatoError = book.createFont();
        fontDatoError.setFontHeightInPoints((short) 9);
        fontDatoError.setColor(HSSFFont.COLOR_RED);
        fontDatoError.setFontName(HSSFFont.FONT_ARIAL);
        fontDatoError.setItalic(true);
        return fontDatoError;
    }

    protected HSSFCellStyle getStyleDatoError() {
        HSSFCellStyle styleDatoError = book.createCellStyle();
        styleDatoError.setWrapText(true);
        styleDatoError.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleDatoError.setFont(getFontDatoError());
        return styleDatoError;
    }
}
