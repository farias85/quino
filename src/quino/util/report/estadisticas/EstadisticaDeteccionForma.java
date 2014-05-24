/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util.report.estadisticas;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.util.report.AbstractInformeExcel;

/**
 *
 * @author farias-i3
 */
public class EstadisticaDeteccionForma extends AbstractInformeExcel{

    public EstadisticaDeteccionForma(HSSFWorkbook book) {
        super(book);
    }

    @Override
    protected void getCuerpo(HSSFSheet sheet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void getEncabezado(HSSFSheet sheet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getInformeExcel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
