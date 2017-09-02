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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 16/07/2014.
 */
package quino.clases.config;

import quino.clases.model.Velocidad;

public class ConfigEnsayoVelocidad extends ConfigEnsayoEnrejado {

    protected Velocidad velocidadSecundaria = new Velocidad(1);

    public ConfigEnsayoVelocidad(int direccion, double ppi, boolean onMove,
            double contrat, double intensidadMedia, Velocidad primaria,
            Velocidad secundaria, double frecuenciaMuestro) {
        super(direccion, ppi, onMove, contrat, intensidadMedia);

        this.velocidadPrimaria = primaria;
        this.velocidadSecundaria = secundaria;
        this.frecuenciaMuestreo = frecuenciaMuestro;
    }

    public ConfigEnsayoVelocidad() {
        super();
    }

    public Velocidad getVelocidadSecundaria() {
        return velocidadSecundaria;
    }

    public void setVelocidadSecundaria(Velocidad velocidadSecundaria) {
        this.velocidadSecundaria = velocidadSecundaria;
    }
}
