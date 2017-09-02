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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 04/07/2013.
 */
package quino.clases.config;

public abstract class ConfigEnsayo {

    /**
     * Valor de la tecla presionada
     */
    protected int key;

    /**
     * Panel de estimulo en el q se produjo el movimiento
     */
    protected int panelEstimulo;

    public ConfigEnsayo() {
    }

    public ConfigEnsayo(int key, int panelEstimulo) {
        this.key = key;
        this.panelEstimulo = panelEstimulo;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getPanelEstimulo() {
        return panelEstimulo;
    }

    public void setPanelEstimulo(int panelEstimulo) {
        this.panelEstimulo = panelEstimulo;
    }
}
