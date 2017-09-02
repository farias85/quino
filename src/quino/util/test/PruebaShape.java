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
import quino.clases.config.ConfigEnsayoShapeDetect;
import quino.clases.config.ConfigEnsayoShapeDetectAuto;
import quino.clases.model.Ensayo;

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
