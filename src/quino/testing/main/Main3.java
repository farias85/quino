/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.testing.main;

import java.awt.Point;
import java.text.DateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.model.Segment2D;
import quino.clases.model.Segment2DGuia;
import quino.util.Aleatorio;
import quino.util.QuinoTools;
import quino.util.report.AbstractInformeExcel;
import quino.util.report.InformeParametrosXEnsayo;

/**
 *
 * @author farias
 */
public class Main3 {

    public static void main(String[] args) {

        /*DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        Date fecha =  new Date();
        System.out.println(df.format(fecha));*/

        /*HSSFWorkbook book = new HSSFWorkbook();
        
        AbstractInformeExcel excel = new InformeParametrosXEnsayo(book);
        excel.getInformeExcel();

        QuinoTools.salvarLibroExcel("C:/book1.xls", book);*/


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

        /*Aleatorio random = new Aleatorio();
        double a = random.nextInt(1, 2);
        a = random.nextInt(5, 6);
        a = random.nextInt(7, 8);
        a = random.nextInt(0, 1);
        a = random.nextInt(1, 2);*/


        /*Point p1 = new Point(150, 150);
        Segment2DOld segment2D = new Segment2DOld(p1, 4, 10);
        //Point p2 = new Point(2, 2);

        double m = Segment2DOld.buscarPendienteRecta(segment2D.getP1(), segment2D.getP2());
        double n = Segment2DOld.buscarOrdenadaRecta(segment2D.getP1(), segment2D.getP2());
        if (Double.isInfinite(m)) {
        int a = 2;
        }*/

        Point p1 = new Point(150, 150);
        double densidad1 = QuinoTools.porciento(25, 600);

        Point p2 = new Point(140, 10);
        double fraccionPi = (Math.PI / 10);

        for (int i = 0; i <= 10; i++) {
            double f = i * fraccionPi;
            double m0 = Math.tan(f);
            double angle = Math.atan(m0);
            double angle2 = angle < 0 ? (Math.PI / 2 + angle) + Math.PI / 2: angle;
            int dir = (int)(angle2 / fraccionPi);
            double grados = Math.toDegrees(angle);
            
            double fm = m0 + f + angle + grados + dir;
        }


        Segment2DGuia guia = new Segment2DGuia(p1, p2, densidad1);

        double a = guia.getPendiente();
        double angle = Math.atan(a);
        double d = angle / fraccionPi;
        int direccion = (int) (d);

        Segment2D sd = new Segment2D(p1, 0);
        a = sd.getPendiente();
        angle = Math.atan(a);
        direccion = (int) (Math.abs(angle) / Math.PI / 10);

        Segment2D sd2 = new Segment2D(p1.x, guia.getPendiente(), guia.getOrdenada());
    }
}
