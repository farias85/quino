/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayo;
import quino.clases.model.Ensayo;

/**
 *
 * @author farias
 */
public class PruebaGabor extends Prueba{

    public PruebaGabor(int cantEnsayos, Date fecha, ArrayList<Ensayo> ensayos) {
        super(cantEnsayos, fecha, ensayos);
    }

    public PruebaGabor(int cantEnsayos, ConfigEnsayo configEnsayo) {
        super(cantEnsayos, configEnsayo);
    }

    public PruebaGabor() {
        super();
    }
}
