/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoShapeDetect;
import quino.clases.config.ConfigEnsayoShapeDetectAuto;
import quino.clases.model.Ensayo;

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

        for (int i = 0; i < cantEnsayos; i++) {

            if (configEnsayo instanceof ConfigEnsayoShapeDetectAuto) {
                configEnsayo = new ConfigEnsayoShapeDetectAuto();
            } else {
                configEnsayo = new ConfigEnsayoShapeDetect(configEnsayo.getDensidad(), configEnsayo.getTolerancia(), configEnsayo.getPcShape());
            }

            Ensayo ensayo = new Ensayo(configEnsayo);
            ensayos.add(ensayo);
        }
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

    @Override
    public String toString() {
        return "Detección de Forma";
    }
}
