/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util.timer;

import javax.swing.JPanel;
import org.opencv.core.Mat;
import quino.util.test.Prueba;

/**
 *
 * @author farias
 */
public abstract class AbstractSinusoideTimer extends AbstractNoMoveTimer {

    protected JPanel jPanel;
    protected Mat mtx;
    protected int count = 0;

    public AbstractSinusoideTimer(Prueba prueba, boolean practica) {
        super(prueba, practica);
    }
}
