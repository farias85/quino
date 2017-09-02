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

import quino.util.test.Prueba;

public abstract class AbstractNoMoveTimer extends AbstractQuinoTimer {

    public AbstractNoMoveTimer(Prueba prueba, boolean practica) {
        super(prueba, practica);
    }

    @Override
    public void run() {
        switch (estadoEnsayo()) {
            case EN_ESPERA:
                execEnEspera();
                break;
            case PREPARADO:
                execEsperandoRespuesta();
                break;
            case ESPERANDO_RESPUESTA:
                execEsperandoRespuesta();
                break;
            case TERMINADO:
                execTerminado();
                startTime = 0;
                break;
        }
    }

    @Override
    protected void execEjecutandoMovimiento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void execPreparado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
