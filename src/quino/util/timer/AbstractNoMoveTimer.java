/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util.timer;

import quino.util.test.Prueba;

/**
 *
 * @author farias
 */
public abstract class AbstractNoMoveTimer extends AbstractQuinoTimer {

    public AbstractNoMoveTimer(Prueba prueba, boolean practica) {
        super(prueba, practica);
    }

    @Override
    public void run() {
        tiempoTranscurrido++;
        switch (estadoEnsayo()) {
            case EN_ESPERA:
                execEnEspera();
                break;
            case PREPARADO:
                execEsperandoRespuesta();
                break;
            case EJECUTANDO_MOVIMIENTO:
                execEsperandoRespuesta();
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

    @Override
    protected void execEnEspera() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //No se ejecuta
    @Override
    protected void execPreparado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //No se ejecuta
    @Override
    protected void execEjecutandoMovimiento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void execEsperandoRespuesta() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void execTerminado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
