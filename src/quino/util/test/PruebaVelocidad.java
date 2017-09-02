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
import quino.clases.config.ConfigEnsayoVelocidad;
import quino.clases.config.ConfigEnsayoVelocidadAuto;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

public class PruebaVelocidad extends PruebaMultiEnsayo {

    public PruebaVelocidad() {
        super();
    }

    public PruebaVelocidad(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaVelocidad(ConfigEnsayoVelocidad configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();
        ConfigEnsayoVelocidad cev = new ConfigEnsayoVelocidad();

        for (int i = 0; i < cantEnsayos; i++) {

            if (configEnsayo instanceof ConfigEnsayoVelocidadAuto) {
                cev = new ConfigEnsayoVelocidadAuto();
            } else if (configEnsayo instanceof ConfigEnsayoVelocidad) {
                int direccion = random.nextInt(0, 8);
                cev = new ConfigEnsayoVelocidad(direccion,
                        configEnsayo.getPpi(), true, configEnsayo.getContrat(),
                        configEnsayo.getIntensidadMedia(), configEnsayo.getVelocidadPrimaria(),
                        configEnsayo.getVelocidadSecundaria(), configEnsayo.getFrecuenciaMuestreo());
            }

            int panelEstimulo = 0;
            if (cev.getVelocidadPrimaria().getAceleracion() != cev.getVelocidadSecundaria().getAceleracion()) {
                panelEstimulo = random.nextInt(1, 2);
            }
            cev.setPanelEstimulo(panelEstimulo);

            switch (panelEstimulo) {
                case 0:
                    cev.setKey(0); //ninguna tecla
                    break;
                case 1:
                    cev.setKey(37);//tecla de la flecha izquierda
                    break;
                case 2:
                    cev.setKey(39);//tecla de la flecha derecha
                    break;
            }

            Ensayo ensayo = new Ensayo(cev);
            ensayos.add(ensayo);
        }
    }

    @Override
    public String toString() {
        return "Velocidad";
    }
}
