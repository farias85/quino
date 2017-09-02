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

import quino.clases.model.Velocidad;
import quino.util.Aleatorio;
import quino.util.QuinoTools;

public class ConfigEnsayoEnrejadoAuto extends ConfigEnsayoEnrejado {

    public ConfigEnsayoEnrejadoAuto() {
        super();

        Aleatorio random = new Aleatorio();
        onMove = random.nextInt(0, 15) % 3 != 0;

        ppi = QuinoTools.FRECUENCIA_ESPACIAL[random.nextInt(0, QuinoTools.FRECUENCIA_ESPACIAL.length - 1)];
        ppi = QuinoTools.getPPiXFrecuenciaEspacial(ppi);

        this.getConfiguracionXDireccion(random.nextInt(1, 8));

        this.key = onMove ? 32 : 0;
        float vprimaria = QuinoTools.VELOCIDAD[random.nextInt(0, QuinoTools.VELOCIDAD.length - 1)];
        this.velocidadPrimaria = new Velocidad(vprimaria);
        this.frecuenciaMuestreo = QuinoTools.FRECUENCIA_TEMPORAL[random.nextInt(0, QuinoTools.FRECUENCIA_TEMPORAL.length - 1)];
    }
}
