/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.testing.main;

import java.util.ArrayList;
import quino.util.QuinoTools;

/**
 *
 * @author farias
 */
public class PruebaAlphaCombrach {

    public static void main(String[] args) {
        double fila1[] = {5, 5, 2};
        double fila2[] = {3, 3, 3};
        double fila3[] = {2, 3, 4};
        double fila4[] = {1, 4, 1};

        ArrayList<double[]> l = new ArrayList<double[]>();
        l.add(fila1);
        l.add(fila2);
        l.add(fila3);
        l.add(fila4);

        double result = QuinoTools.calcAlphaCronbach(l);
        System.out.println(result);
    }
}
