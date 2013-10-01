/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import quino.clases.model.Prueba;
import quino.clases.model.Ensayo;
import quino.clases.model.Resultado;
import quino.util.QuinoJPanel;
import quino.clases.config.*;
import java.awt.event.KeyEvent;
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
        if (numEnsayo == prueba.getCantEnsayos()) {
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
     * Mueve los puntos de un jpanel segun la configuración determinada
     * @param quinoJPanel El jpanel en el q se moverán los puntos
     */
    protected void moverPuntoYRepintar(QuinoJPanel quinoJPanel) {
        if (ensayo.getConfiguracion().isAsincronico()) {
            quinoJPanel.moverAsincronico();
        } else {
            quinoJPanel.moverEnDireccion(ensayo.getConfiguracion().getDireccion());
        }
        quinoJPanel.repaint();
    }

    /**
     * Controla el resultado de la interacción con el teclado en el ensayo
     */
    protected void controlarEnsayo() {
        if (ensayo.getConfiguracion().isControl()
                && ensayo.getPanelEstimulo() > 0) {
            int keyEsperada = ensayo.getConfiguracion().getKey();
            if (keyEsperada != resultado.getKey()) {
                resultado.setError(true);
                resultado.setDescripcion("Las direcciones no coinciden");
            }
        } else if (ensayo.getPanelEstimulo() == 0) {
            resultado.setError(true);
            resultado.setDescripcion("No hubo Estímulo");
        } else if (ensayo.getConfiguracion().getKey() != resultado.getKey()) {
            resultado.setError(true);
            resultado.setDescripcion("No se ha presionado la barra espaciadora");
        }
    }

    /**
     * Define los parametros de inicio para los ensayos
     * @param cantPaneles Cantidad de paneles de cada ensayo sin contar el
     * panel central.
     */
    protected void inicializarEnsayo() {
        ensayo = prueba.getEnsayos().get(numEnsayo);
        resultado = new Resultado();
    }

    /**
     * Captura el evento del teclado y define el tiempo de respuesta para
     * el ensayo
     * @param e El evento del teclado
     */
    protected void capturarEventoTeclado(KeyEvent e) {
        int k = e.getKeyCode();
        System.out.println("tecla presionada: " + k);

        resultado.setKey(k);

        if (ensayo.getPanelEstimulo() > 0) {
            resultado.setTiempoRespuesta(tiempoTranscurrido - (enEspera + preparado + 1));
        }

        controlarEnsayo();
        puedeTeclear = false;
    }

    /**
     * Obtiene los resultado finales de cada ensayo y se lo asigna a la
     * prueba en cuestión
     */
    protected void obtenerResultado() {
        resultado.setVelocidad(QuinoTools.getVelocidad(ensayo.getConfiguracion().getTiempoMovimiento()));
        resultado.setAngulo(buscarAngulo());
        ensayo.setResultado(resultado);
    }
}
