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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 13/09/2013.
 */
package quino.clases.model;

import quino.clases.config.ConfigEnsayo;

/**
 * Representa cada ensayo dentro de una prueba.
 */
public class Ensayo {

    /**
     * Configuracion de la prueba a la q pertenece
     */
    private ConfigEnsayo configuracion;
    /**
     * El resultado del ensayo
     */
    private Resultado resultado;

    public Ensayo() {
    }

    public Ensayo(ConfigEnsayo configuracion) {
        this.configuracion = configuracion;
        resultado = new Resultado();
    }

    public Ensayo(ConfigEnsayo configuracion, Resultado resultado) {
        this.configuracion = configuracion;
        this.resultado = resultado;
    }

    public ConfigEnsayo getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfigEnsayo configuracion) {
        this.configuracion = configuracion;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}
