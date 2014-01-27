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
import quino.util.Aleatorio;

/**
 *
 * @author farias
 */
public class PruebaGabor extends PruebaMultiEnsayo {

    public PruebaGabor(ConfigEnsayoGabor configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();
        ConfigEnsayoGabor ceg = new ConfigEnsayoGabor();

        for (int i = 0; i < cantEnsayos; i++) {
            if (configEnsayo instanceof ConfigEnsayoGaborAuto) {
                ceg = new ConfigEnsayoGaborAuto();
            } else if (configEnsayo instanceof ConfigEnsayoGabor) {
                if (configEnsayo.getDireccion() == 0) {
                    int direccion = random.nextInt(1, 4);
                    ceg = new ConfigEnsayoGabor(direccion, configEnsayo.getPpi(),
                            configEnsayo.getContrat(), configEnsayo.getIntensidadMedia(),
                            configEnsayo.getGaussianStdpix(), configEnsayo.getRadio1(),
                            configEnsayo.getRadio2(), configEnsayo.getPpi2());
                } else {
                    ceg = new ConfigEnsayoGabor(configEnsayo.getDireccion(), configEnsayo.getPpi(),
                            configEnsayo.getContrat(), configEnsayo.getIntensidadMedia(),
                            configEnsayo.getGaussianStdpix(), configEnsayo.getRadio1(),
                            configEnsayo.getRadio2(), configEnsayo.getPpi2());
                }
            }

            Ensayo ensayo = new Ensayo(ceg);
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
