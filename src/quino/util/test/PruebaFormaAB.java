/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 04/10/2013.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.clases.config.ConfigEnsayoFormaABAuto;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

public abstract class PruebaFormaAB extends PruebaMultiEnsayo {

    public PruebaFormaAB(ConfigEnsayoFormaAB configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();

        for (int i = 0; i < cantEnsayos; i++) {
            ConfigEnsayoFormaAB result;

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
