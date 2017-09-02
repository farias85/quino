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
package quino.util.timer;

import java.awt.event.KeyEvent;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.util.JPanelQuino;
import quino.util.QuinoTools;
import quino.util.test.Prueba;

public abstract class AbstractFormaABTimer extends AbstractQuinoTimer {

    public AbstractFormaABTimer(Prueba prueba, boolean practica) {
        super(prueba, practica);

        if (!(ensayo.getConfiguracion() instanceof ConfigEnsayoFormaAB)) {
            System.err.println("El ensayo no es de tipo ConfigEnsayoFormaAB en la clase AbstractFormaABTimer");
        }
    }

    /**
     * Mueve los puntos de un jpanel segun la configuración determinada
     *
     * @param quinoJPanel El jpanel en el q se moverán los puntos
     */
    protected void moverPuntoYRepintar(JPanelQuino quinoJPanel) {
        if (((ConfigEnsayoFormaAB) ensayo.getConfiguracion()).isAsincronico()) {
            quinoJPanel.moverAsincronico();
        } else {
            quinoJPanel.moverEnDireccion(((ConfigEnsayoFormaAB) ensayo.getConfiguracion()).getDireccion());
        }
        quinoJPanel.repaint();
    }

    /**
     * Controla el resultado de la interacción con el teclado en el ensayo
     */
    protected void controlarEnsayo() {
        if (((ConfigEnsayoFormaAB) ensayo.getConfiguracion()).isControl()
                && ensayo.getConfiguracion().getPanelEstimulo() > 0) {
            int keyEsperada = ensayo.getConfiguracion().getKey();
            if (keyEsperada != resultado.getKey()) {
                resultado.setError(true);
                resultado.setDescripcion("Las direcciones no coinciden");
            }
        } else if (ensayo.getConfiguracion().getPanelEstimulo() == 0) {
            resultado.setError(true);
            resultado.setDescripcion("No hubo Estímulo");
        } else if (ensayo.getConfiguracion().getKey() != resultado.getKey()) {
            resultado.setError(true);
            resultado.setDescripcion("No se ha presionado la barra espaciadora");
        }
    }

    /**
     * Captura el evento del teclado y define el tiempo de respuesta para el
     * ensayo
     *
     * @param e El evento del teclado
     */
    protected void capturarEventoTeclado(KeyEvent e) {
        int k = e.getKeyCode();
        System.out.println("tecla presionada: " + k);

        resultado.setKey(k);

        if (ensayo.getConfiguracion().getPanelEstimulo() > 0) {
            resultado.setTiempoRespuesta((int) (getTiempoTranscurrido() - (enEspera + preparado + 1)));
        }

        controlarEnsayo();
        puedeTeclear = false;
    }

    /**
     * Obtiene los resultado finales de cada ensayo y se lo asigna a la prueba
     * en cuestión
     */
    protected void obtenerResultado() {
        resultado.setVelocidad(QuinoTools.getVelocidad(((ConfigEnsayoFormaAB) ensayo.getConfiguracion()).getTiempoMovimiento()));
        resultado.setAngulo(buscarAngulo());
        ensayo.setResultado(resultado);
    }
}
