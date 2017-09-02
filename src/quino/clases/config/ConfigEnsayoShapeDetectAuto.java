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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 10/07/2013.
 */
package quino.clases.config;

import quino.util.Aleatorio;

public class ConfigEnsayoShapeDetectAuto extends ConfigEnsayoShapeDetect {

    public ConfigEnsayoShapeDetectAuto(int key, int panelEstimulo, int densidad, int tolerancia, double pcShape, int numFigura) {
        super(key, panelEstimulo, densidad, tolerancia, pcShape, numFigura);
    }

    public ConfigEnsayoShapeDetectAuto() {
        super();

        Aleatorio random = new Aleatorio();
        densidad = random.nextInt(1000, 1500);
        tolerancia = random.nextInt(0, 1);
        pcShape = random.nextInt(8, 12);
        numFigura = random.nextInt(1, 4);

        panelEstimulo = random.nextInt(0, 2);

        switch (panelEstimulo) {
            case 0:
                key = 0; //ninguna tecla
                break;
            case 1:
                key = 37;//tecla de la flecha izquierda
                break;
            case 2:
                key = 39;//tecla de la flecha derecha
                break;
        }
    }
}
