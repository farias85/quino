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
 * Representa una prueba de tipo Foveal
 * @author Felipe Rodriguez Arias
 */
public class PruebaFoveal extends PruebaFormaAB{

    public PruebaFoveal(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaFoveal(ConfigEnsayoFormaAB configEnsayo, int cantEnsayos) {
        super(configEnsayo, cantEnsayos);
    }

    public PruebaFoveal() {
        super();
    }
}
