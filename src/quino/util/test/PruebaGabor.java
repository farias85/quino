/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayoGabor;
import quino.clases.model.Ensayo;

/**
 *
 * @author farias
 */
public class PruebaGabor extends PruebaSingleEnsayo {

    public PruebaGabor(ConfigEnsayoGabor configEnsayo) {
        super();
        Ensayo ensayo = new Ensayo(configEnsayo);
        ensayos.add(ensayo);
    }

    public PruebaGabor(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaGabor() {
        super();
    }

    @Override
    public String toString() {
        return "Campana de Gabor";
    }
}
