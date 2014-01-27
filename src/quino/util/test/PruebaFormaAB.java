/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.clases.config.ConfigEnsayoFormaABAuto;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

/**
 *
 * @author farias
 */
public abstract class PruebaFormaAB extends PruebaMultiEnsayo {

    public PruebaFormaAB(ConfigEnsayoFormaAB configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();

        for (int i = 0; i < cantEnsayos; i++) {
            ConfigEnsayoFormaAB result = new ConfigEnsayoFormaAB();

            if (configEnsayo instanceof ConfigEnsayoFormaABAuto) {
                if (this instanceof PruebaFormaA) {
                    result = new ConfigEnsayoFormaABAuto(configEnsayo.isControl(), random.nextInt(0, 8));
                } else {
                    result = new ConfigEnsayoFormaABAuto(configEnsayo.isControl(), random.nextInt(0, 2));
                }
            } else {
                if (this instanceof PruebaFormaA) {
                    result = new ConfigEnsayoFormaAB(configEnsayo.getTiempoMovimiento(),
                            configEnsayo.getDensidad(), configEnsayo.getCantidad(),
                            configEnsayo.getDireccion(), configEnsayo.isAsincronico(),
                            configEnsayo.isControl(), random.nextInt(0, 8));
                } else {
                    result = new ConfigEnsayoFormaAB(configEnsayo.getTiempoMovimiento(),
                            configEnsayo.getDensidad(), configEnsayo.getCantidad(),
                            configEnsayo.getDireccion(), configEnsayo.isAsincronico(),
                            configEnsayo.isControl(), random.nextInt(0, 2));
                }
            }


            Ensayo ensayo = new Ensayo(result);
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
            if (ce instanceof ConfigEnsayoFormaAB || ce instanceof ConfigEnsayoFormaABAuto) {
                sum = sum + ((ConfigEnsayoFormaAB) ensayos.get(i).getConfiguracion()).getDensidad();
            }
        }
        prom = sum / ensayos.size();
        return prom;
    }

    public PruebaFormaAB() {
        super();
    }

    public PruebaFormaAB(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }
}
