/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.model;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayo;

/**
 * Representa una prueba de tipo Periférica
 * @author Felipe Rodriguez Arias
 */
public class PruebaPeriferica extends Prueba{

    public PruebaPeriferica(int cantEnsayos, Date fecha, ArrayList<Ensayo> ensayos) {
        super(cantEnsayos, fecha, ensayos);
    }

    public PruebaPeriferica(int cantEnsayos, ConfigEnsayo configEnsayo) {
        super(cantEnsayos, configEnsayo);
    }

    public PruebaPeriferica() {
        super();
    }
}
