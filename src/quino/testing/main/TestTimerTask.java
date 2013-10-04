/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.testing.main;

import java.util.TimerTask;

/**
 *
 * @author Administrador
 */
public class TestTimerTask extends TimerTask{
    private int cont = 0;

    @Override
    public void run() {
        System.out.println(cont);
        cont++;
    }

}
