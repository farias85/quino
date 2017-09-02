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
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.clases.model.Ensayo;

/**
 * Representa una prueba de tipo Foveal
 */
public class PruebaFormaA extends PruebaFormaAB {

    public PruebaFormaA(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaFormaA(ConfigEnsayoFormaAB configEnsayo, int cantEnsayos) {
        super(configEnsayo, cantEnsayos);
    }

    public PruebaFormaA() {
        super();
    }

    @Override
    public String toString() {
        return "Forma A";
    }
}
