/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import java.util.Random;

/**
 * Representa un número aleatorio
 * @author davisito
 */
public class Aleatorio extends Random {

    /**
     * Devuelve un valor entero entre el valor inf y el sup
     * @param inf Limite inferior
     * @param sup Limite superior
     * @return Un número entero entre inf y sup
     */
    public int nextInt(int inf, int sup) {
        int i = nextInt();
        i = inf + (Math.abs(i) % (sup - inf + 1));
        return (i);
    }
}
