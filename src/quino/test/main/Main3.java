/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.util.QuinoTools;
import quino.util.report.InformeExcel;
import quino.util.report.InformeParametrosXEnsayo;

/**
 *
 * @author farias
 */
public class Main3 {

    public static void main(String[] args) {
        HSSFWorkbook book = new HSSFWorkbook();
        
        InformeExcel excel = new InformeParametrosXEnsayo(book);
        excel.getInformeExcel();

        QuinoTools.salvarLibroExcel("C:/book1.xls", book);


        /*jTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable1.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable1.getRowCount()) {
                    jTable1.setRowSelectionInterval(r, r);
                } else {
                    jTable1.clearSelection();
                }

                int rowindex = jTable1.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = //createYourPopUp(); menu;
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });*/
    }
}
