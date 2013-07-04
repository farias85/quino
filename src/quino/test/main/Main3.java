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
    }
}
