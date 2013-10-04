/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoShapeDetect;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

/**
 *
 * @author farias
 */
public class PruebaShape extends PruebaMultiEnsayo {

    public PruebaShape(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaShape(ConfigEnsayoShapeDetect configEnsayo, int cantEnsayos) {
        super(cantEnsayos);

        Aleatorio random = new Aleatorio();
        int panelEstimulo = random.nextInt(0, 2);

        switch (panelEstimulo) {
            case 0:
                configEnsayo.setKey(0); //ninguna tecla
                break;
            case 1:
                configEnsayo.setKey(37);//tecla de la flecha izquierda
                break;
            case 2:
                configEnsayo.setKey(39);//tecla de la flecha derecha
                break;
        }

        Ensayo ensayo = new Ensayo(configEnsayo, panelEstimulo);
        ensayos.add(ensayo);
    }

    /**
     * Devuelve el promedio de la densidad de esta prueba
     * @return Promedio de la densidad de cada ensayo
     */
    public int densidadPromedio() {
        int sum = 0;
        int prom = 0;
        for (int i = 0; i < ensayos.size(); i++) {
            ConfigEnsayo ce = ensayos.get(i).getConfiguracion();
            if (ce instanceof ConfigEnsayoShapeDetect) {
                sum = sum + ((ConfigEnsayoShapeDetect) ensayos.get(i).getConfiguracion()).getDensidad();
            }
        }
        prom = sum / ensayos.size();
        return prom;
    }

    public PruebaShape() {
        super();
    }
}
