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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 06/07/2013.
 */
package quino.clases.config;

public class ConfigEnsayoEnrejado extends ConfigEnsayoSinusoide {

    /**
     * True cuando la matrix enrejada se va a mover, falso en caso contrario
     */
    protected boolean onMove = false;

    public ConfigEnsayoEnrejado() {
        super();
    }

    //No se puse la velocidad ni la frecuencia de muestreo en el constructor
    //xq la prueba de Orientacion hereda de esta prueba y ahi no hay movimiento
    //los valores hay que setearlos luego del constructor
    public ConfigEnsayoEnrejado(int direccion, double ppi, boolean onMove,
            double contrat, double intensidadMedia) {
        super(direccion, ppi, contrat, intensidadMedia);

        this.onMove = onMove;
        this.key = onMove ? 32 : 0;
    }

    public boolean isOnMove() {
        return onMove;
    }

    public void setOnMove(boolean onMove) {
        this.onMove = onMove;
    }
}
