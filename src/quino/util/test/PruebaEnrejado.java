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
import quino.clases.config.ConfigEnsayoEnrejado;
import quino.clases.config.ConfigEnsayoEnrejadoAuto;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

public class PruebaEnrejado extends PruebaMultiEnsayo {

    public PruebaEnrejado(ConfigEnsayoEnrejado configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();
        ConfigEnsayoEnrejado cee;

        for (int i = 0; i < cantEnsayos; i++) {
            if (configEnsayo instanceof ConfigEnsayoEnrejadoAuto) {
                cee = new ConfigEnsayoEnrejadoAuto();
            } else {
                boolean onMove = random.nextInt(0, 15) % 3 != 0;
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

                cee.setFrecuenciaMuestreo(configEnsayo.getFrecuenciaMuestreo());
                cee.setVelocidadPrimaria(configEnsayo.getVelocidadPrimaria());
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
