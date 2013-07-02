/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Administrador
 */
public class Main1 {

    public static void main(String[] args) {
        TimerTask task = new TestTimerTask();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1);
    }
}
