/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util.timer;

import java.awt.Toolkit;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import quino.util.test.Prueba;

/**
 *
 * @author farias
 */
public abstract class AbstractSinusoideTimer extends AbstractNoMoveTimer {

    protected JPanel jPanel;
    protected int fs;                     // sampling frequency in frames / s
    protected double fspa_cpi_x;          // spatial frequency in x, cicles / inch
    protected double fspa_cpi_y;          // spatial frequency in y, cicles / inch
    protected int ppi;                    //screen pixels x inch
    protected double fspa_cpp_x;          // spatial frequency in x,cicles / pixels
    protected double fspa_cpp_y;          // spatial frequency in x,cicles / pixels
    protected Mat mtx;
    protected int count = 0;

    public AbstractSinusoideTimer(Prueba prueba, boolean practica) {
        super(prueba, practica);

        ppi = Toolkit.getDefaultToolkit().getScreenResolution() / 3;
    }
}
