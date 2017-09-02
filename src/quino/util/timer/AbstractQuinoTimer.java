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
import quino.clases.model.Ensayo;
import quino.clases.model.Resultado;
import quino.clases.config.*;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import quino.util.QuinoTools;

public abstract class AbstractQuinoTimer extends TimerTask {

    protected Prueba prueba;
    protected Ensayo ensayo;
    protected Resultado resultado;
    //protected int tiempoTranscurrido = 0;
    protected int numEnsayo = 0;
    protected int enEspera;
    protected int preparado;
    protected int esperandoRespuesta;
    protected boolean puedeTeclear = false;
    protected boolean inOut = true;
    protected boolean practica;
    protected KeyListener keyPress;
    protected boolean movEjecutado = false;
    protected long startTime = 0;

    public AbstractQuinoTimer(Prueba prueba, boolean practica) {
        this.prueba = prueba;
        this.practica = practica;
        this.ensayo = prueba.getEnsayos().get(numEnsayo);
        this.resultado = new Resultado();

        enEspera = QuinoTools.porcientoDuracion(ConfigApp.PC_EN_ESPERA);
        preparado = QuinoTools.porcientoDuracion(ConfigApp.PC_PREPARADO);
        esperandoRespuesta = QuinoTools.porcientoDuracion(ConfigApp.PC_ESPERANDO_RESPUESTA);
    }

    /**
     * Momento de inicio de ensayo o cambio de tarea
     */
    protected abstract void execEnEspera();

    /**
     * Momento de inicio del ensayo, preparado para recibir la acción de
     * movimientiento o no
     */
    protected abstract void execPreparado();

    /**
     * Momento en el q se ejecuta el movimiento o no
     */
    protected abstract void execEjecutandoMovimiento();

    /**
     * Momento de espera de la respuesta
     */
    protected abstract void execEsperandoRespuesta();

    /**
     * Momento de terminación de la tarea, se recopilan los resultado obtenidos
     */
    protected abstract void execTerminado();

    /**
     * Método para la configuración del movimiento de los puntos.
     * No se pone abstracto para no atar a las clases hijas con tantos
     * métodos abstractos, pero si hubiera q mover puntos en el ensayo
     * se debe sobreescribir
     */
    protected void moverPuntos() {
    }

    /**
     * Limpia el dibujo de los paneles que no son los centrales.
     * Se debe configurar en la clase hija
     */
    protected void panelsClear() {
    }

    /**
     * Dibuja en los paneles que no son los centrales.
     * Se debe configurar en la clase hija.
     */
    protected void panelsRepaint() {
    }

    /**
     * Rellena con nuevos puntos los paneles q no son los centrales
     * Se debe configurar en la clase hija
     */
    protected void panelsRellenar() {
    }

    /**
     * Busca el angulo de movimiento de los puntos trasladados
     * Se debe implementar en la clase hija
     * @return El valor del ángulo en grados
     */
    protected double buscarAngulo() {
        return 0;
    }

    /**
     * Devuelve el estado asociado al valor de tiempoTranscurrido
     * @return El nombre del estado
     */
    protected EstadoEnsayo estadoEnsayo() {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
        long elapsedTime = getTiempoTranscurrido();
        if (elapsedTime <= enEspera) {
            return EstadoEnsayo.EN_ESPERA;
        } else if (elapsedTime <= enEspera + preparado) {
            return EstadoEnsayo.PREPARADO;
        } else if (elapsedTime < enEspera + preparado + esperandoRespuesta - 1) {
            return EstadoEnsayo.ESPERANDO_RESPUESTA;
        }
        return EstadoEnsayo.TERMINADO;
    }

    protected long getTiempoTranscurrido() {
        return System.currentTimeMillis() - startTime;
    }

    @Override
    public void run() {
        switch (estadoEnsayo()) {
            case EN_ESPERA:
                execEnEspera();
                break;
            case PREPARADO:
                execPreparado();
                break;
            case ESPERANDO_RESPUESTA: {
                if (!movEjecutado) {
                    execEjecutandoMovimiento();
                    movEjecutado = true;
                }
                execEsperandoRespuesta();
            }
            break;
            case TERMINADO: {
                execTerminado();
                movEjecutado = false;
                startTime = 0;
            }
            break;
            default:
        }
    }

    /**
     * Cancela la tarea ejecutada
     * @return Devuelve true si se ha ejecutado el último ensayo y se puede
     * cancelar toda la tarea; devuelve false si es necesario continuar con
     * la tarea pues no han transcurrido los ensayos necesarios
     */
    protected boolean cancelarTarea() {
        if (numEnsayo == prueba.getEnsayos().size() - 1) {
            cancel();
            return true;
        } else {
            numEnsayo++;
            ensayo = prueba.getEnsayos().get(numEnsayo);
            resultado = new Resultado();
        }
        return false;
    }
}
