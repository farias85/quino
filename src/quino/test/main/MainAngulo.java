/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import quino.util.Punto;
import quino.util.QuinoTools;

/**
 *
 * @author farias
 */
public class MainAngulo {

    public static void main(String[] args) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double x = (d.getWidth() / 2);
        double y = (d.getHeight() / 2);
        Punto p1 = new Punto(x, y);


        Punto p2 = new Punto(x - 1, y - 1);

        double angulo = QuinoTools.getAngulo(p1, p2);

        System.out.println("Angulo: " + angulo);

    }
}
