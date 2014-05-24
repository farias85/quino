/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util.report;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author farias-i3
 */
public abstract class AbstractInformeAB extends AbstractInformeExcel {

    protected boolean foveal = true;

    public boolean isFoveal() {
        return foveal;
    }

    public void setFoveal(boolean foveal) {
        this.foveal = foveal;
    }

    public AbstractInformeAB(HSSFWorkbook book) {
        super(book);
    }
}
