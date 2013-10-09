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
public class PruebaOrientacion extends PruebaMultiEnsayo {

    public PruebaOrientacion() {
        super();
    }

    public PruebaOrientacion(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaOrientacion(ConfigEnsayoEnrejado configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();

        for (int i = 0; i < cantEnsayos; i++) {
            int panelEstimulo = random.nextInt(0, 2);
            if (configEnsayo instanceof ConfigEnsayoEnrejadoAuto) {
                configEnsayo = new ConfigEnsayoEnrejadoAuto();
            } else {
                configEnsayo = new ConfigEnsayoEnrejado(configEnsayo.getDireccion(),
                        configEnsayo.getPpi(), false, configEnsayo.getContrat(), configEnsayo.getIntensidadMedia());
            }

            configEnsayo.setPanelEstimulo(panelEstimulo);
            switch (panelEstimulo) {
                case 1:
                    configEnsayo.setKey(37);
                    break;
                case 2:
                    configEnsayo.setKey(39);
                    break;
            }

            Ensayo ensayo = new Ensayo(configEnsayo);
            ensayos.add(ensayo);
        }
    }

    @Override
    public String toString() {
        return "Orientación";
    }
}
