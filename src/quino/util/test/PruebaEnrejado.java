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
public class PruebaEnrejado extends PruebaMultiEnsayo {

    public PruebaEnrejado(ConfigEnsayoEnrejado configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();
        ConfigEnsayoEnrejado cee = new ConfigEnsayoEnrejado();

        for (int i = 0; i < cantEnsayos; i++) {
            if (configEnsayo instanceof ConfigEnsayoEnrejadoAuto) {
                cee = new ConfigEnsayoEnrejadoAuto();
            } else {
                boolean onMove = random.nextInt(0, 15) % 3 == 0 ? false : true;
                if (configEnsayo.getDireccion() == 0) {
                    int direccion = random.nextInt(1, 8);
                    cee = new ConfigEnsayoEnrejado(direccion,
                            configEnsayo.getPpi(), onMove, configEnsayo.getContrat(),
                            configEnsayo.getIntensidadMedia());
                } else {
                    cee = new ConfigEnsayoEnrejado(configEnsayo.getDireccion(),
                            configEnsayo.getPpi(), onMove, configEnsayo.getContrat(),
                            configEnsayo.getIntensidadMedia());
                }
            }

            Ensayo ensayo = new Ensayo(cee);
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
