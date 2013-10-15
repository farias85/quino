/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayoOrientacion;
import quino.clases.config.ConfigEnsayoOrientacionAuto;
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

    public PruebaOrientacion(ConfigEnsayoOrientacion configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();
        ConfigEnsayoOrientacion ceo = new ConfigEnsayoOrientacion();

        for (int i = 0; i < cantEnsayos; i++) {
            int panelEstimulo = random.nextInt(0, 2);
            if (configEnsayo instanceof ConfigEnsayoOrientacionAuto) {
                ceo = new ConfigEnsayoOrientacionAuto();
            } else if (configEnsayo instanceof ConfigEnsayoOrientacion) {
                int direccion = random.nextInt(5, 6);
                if (configEnsayo.getDireccion() == 0) {
                    ceo = new ConfigEnsayoOrientacion(direccion,
                            configEnsayo.getPpi(), false, configEnsayo.getContrat(),
                            configEnsayo.getIntensidadMedia());
                } else {
                    ceo = new ConfigEnsayoOrientacion(configEnsayo.getDireccion(),
                            configEnsayo.getPpi(), false, configEnsayo.getContrat(),
                            configEnsayo.getIntensidadMedia());
                }
            }

            ceo.setPanelEstimulo(panelEstimulo);
            switch (panelEstimulo) {
                case 1:
                    ceo.setKey(37);
                    break;
                case 2:
                    ceo.setKey(39);
                    break;
            }

            Ensayo ensayo = new Ensayo(ceo);
            ensayos.add(ensayo);
        }
    }

    @Override
    public String toString() {
        return "Orientación";
    }
}
