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
public class PruebaPeriferica extends PruebaFormaAB {

    public PruebaPeriferica(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaPeriferica() {
        super();
    }

    public PruebaPeriferica(ConfigEnsayoFormaAB configEnsayo, int cantEnsayos) {
        super(configEnsayo, cantEnsayos);
    }
}
