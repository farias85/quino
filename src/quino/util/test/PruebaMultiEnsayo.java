/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.model.Ensayo;

/**
 *
 * @author farias
 */
public abstract class PruebaMultiEnsayo extends Prueba {

    /**
     * Cantidad de ensayos de la prueba a ejcutarse
     */
    private int cantEnsayos;

    public PruebaMultiEnsayo(int cantEnsayos) {
        super();
        this.cantEnsayos = cantEnsayos;
    }

    public PruebaMultiEnsayo(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaMultiEnsayo() {
        super();
    }

    public int getCantEnsayos() {
        return cantEnsayos;
    }

    public void setCantEnsayos(int cantEnsayos) {
        this.cantEnsayos = cantEnsayos;
    }
}
