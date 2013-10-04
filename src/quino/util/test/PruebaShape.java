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
public class PruebaShape extends Prueba{

    public PruebaShape(int cantEnsayos, Date fecha, ArrayList<Ensayo> ensayos) {
        super(cantEnsayos, fecha, ensayos);
    }

    public PruebaShape(int cantEnsayos, ConfigEnsayo configEnsayo) {
        super(cantEnsayos, configEnsayo);
    }

    public PruebaShape() {
        super();
    }
}
