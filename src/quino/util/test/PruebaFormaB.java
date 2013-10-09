/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.clases.model.Ensayo;

/**
 * Representa una prueba de tipo Periférica
 * @author Felipe Rodriguez Arias
 */
public class PruebaFormaB extends PruebaFormaAB {

    public PruebaFormaB(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaFormaB() {
        super();
    }

    public PruebaFormaB(ConfigEnsayoFormaAB configEnsayo, int cantEnsayos) {
        super(configEnsayo, cantEnsayos);
    }

    @Override
    public String toString() {
        return "Forma B";
    }
}
