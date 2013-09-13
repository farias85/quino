/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.model;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayo;

/**
 * Representa una prueba de tipo Foveal
 * @author Felipe Rodriguez Arias
 */
public class PruebaFoveal extends Prueba{

    public PruebaFoveal(int cantEnsayos, Date fecha, ArrayList<Ensayo> ensayos) {
        super(cantEnsayos, fecha, ensayos);
    }

    public PruebaFoveal(int cantEnsayos, ConfigEnsayo configEnsayo) {
        super(cantEnsayos, configEnsayo);
    }

    public PruebaFoveal() {
        super();
    }
}
