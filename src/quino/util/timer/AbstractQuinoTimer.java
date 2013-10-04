/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import quino.util.test.Prueba;
import quino.clases.model.Ensayo;
import quino.clases.model.Resultado;
import quino.clases.config.*;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import quino.util.QuinoTools;

/**
 *
 * @author Felipao
 */
public abstract class AbstractQuinoTimer extends TimerTask {

    protected Prueba prueba;
    protected Ensayo ensayo;
    protected Resultado resultado;
    protected int tiempoTranscurrido = 0;
    protected int numEnsayo = 0;
    protected int enEspera;
    protected int preparado;
    protected int esperandoRespuesta;
    protected boolean puedeTeclear = false;
    protected boolean inOut = true;
    protected boolean practica;
    protected KeyListener keyPress;

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

    @Override
    public void run() {
        tiempoTranscurrido++;
        switch (estadoEnsayo()) {
            case EN_ESPERA:
                execEnEspera();
                break;
            case PREPARADO:
                execPreparado();
                break;
            case EJECUTANDO_MOVIMIENTO:
                execEjecutandoMovimiento();
                break;
            case ESPERANDO_RESPUESTA:
                execEsperandoRespuesta();
                break;
            case TERMINADO:
                execTerminado();
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
        numEnsayo++;
        if (numEnsayo == prueba.getEnsayos().size()) {
            cancel();
            return true;
        }
        tiempoTranscurrido = 0;
        return false;
    }

    /**
     * Devuelve el estado asociado al valor de tiempoTranscurrido
     * @return El nombre del estado
     */
    protected EstadoEnsayo estadoEnsayo() {
        if (tiempoTranscurrido <= enEspera) {
            return EstadoEnsayo.EN_ESPERA;
        } else if (tiempoTranscurrido <= enEspera + preparado) {
            return EstadoEnsayo.PREPARADO;
        } else if (tiempoTranscurrido == enEspera + preparado + 1) {
            return EstadoEnsayo.EJECUTANDO_MOVIMIENTO;
        } else if (tiempoTranscurrido < enEspera + preparado + esperandoRespuesta - 1) {
            return EstadoEnsayo.ESPERANDO_RESPUESTA;
        }
        return EstadoEnsayo.TERMINADO;
    }

    /**
     * Define los parametros de inicio para los ensayos
     * @param cantPaneles Cantidad de paneles de cada ensayo sin contar el
     * panel central.
     */
    protected void inicializarEnsayo() {
        if (numEnsayo <= prueba.getEnsayos().size() - 1) {
            ensayo = prueba.getEnsayos().get(numEnsayo);
            resultado = new Resultado();
        }
    }
}
