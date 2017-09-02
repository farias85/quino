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
import quino.clases.config.ConfigEnsayoGabor;
import quino.clases.config.ConfigEnsayoGaborAuto;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

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
                
                ceg.setFrecuenciaMuestreo(configEnsayo.getFrecuenciaMuestreo());
                ceg.setVelocidadPrimaria(configEnsayo.getVelocidadPrimaria());
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
