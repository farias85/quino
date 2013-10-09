/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayoGabor;
import quino.clases.config.ConfigEnsayoGaborAuto;
import quino.clases.model.Ensayo;

/**
 *
 * @author farias
 */
public class PruebaGabor extends PruebaMultiEnsayo {

    public PruebaGabor(ConfigEnsayoGabor configEnsayo, int cantEnsayos) {
        super(cantEnsayos);

        for (int i = 0; i < cantEnsayos; i++) {
            if (configEnsayo instanceof ConfigEnsayoGaborAuto) {
                configEnsayo = new ConfigEnsayoGaborAuto();
            }

            Ensayo ensayo = new Ensayo(configEnsayo);
            ensayos.add(ensayo);
        }

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
