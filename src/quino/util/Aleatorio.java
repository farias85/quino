/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import java.util.Random;

/**
 *
 * @author davisito
 */
public class Aleatorio extends Random {

    public int nextInt(int inf, int sup) {
        int i = nextInt();
        i = inf + (Math.abs(i) % (sup - inf + 1));
        return (i);
    }
}
