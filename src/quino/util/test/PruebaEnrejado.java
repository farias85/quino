/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayoEnrejado;
import quino.clases.config.ConfigEnsayoEnrejadoAuto;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

/**
 *
 * @author farias
 */
public class PruebaEnrejado extends PruebaSingleEnsayo {

    public PruebaEnrejado(ConfigEnsayoEnrejado configEnsayo, int cantEnsayos) {
        super();
        Aleatorio random = new Aleatorio();

        for (int i = 0; i < cantEnsayos; i++) {
            if (configEnsayo instanceof ConfigEnsayoEnrejadoAuto) {
                configEnsayo = new ConfigEnsayoEnrejadoAuto();
            } else {
                boolean onMove = random.nextInt(0, 15) % 3 == 0 ? false : true;
                configEnsayo = new ConfigEnsayoEnrejado(configEnsayo.getDireccion(), configEnsayo.getPpi(), onMove);
            }

            Ensayo ensayo = new Ensayo(configEnsayo);
            ensayos.add(ensayo);
        }
    }

    public PruebaEnrejado(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaEnrejado() {
        super();
    }

    @Override
    public String toString() {
        return "Enrejado";
    }
}
