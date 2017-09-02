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
import quino.clases.model.Ensayo;

public abstract class PruebaMultiEnsayo extends Prueba {

    /**
     * Cantidad de ensayos de la prueba a ejcutarse
     */
    private int cantEnsayos;

    public PruebaMultiEnsayo(int cantEnsayos) {
        super();
        this.cantEnsayos = cantEnsayos;
    }

    public PruebaMultiEnsayo(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaMultiEnsayo() {
        super();
    }

    public int getCantEnsayos() {
        return cantEnsayos;
    }

    public void setCantEnsayos(int cantEnsayos) {
        this.cantEnsayos = cantEnsayos;
    }
}
