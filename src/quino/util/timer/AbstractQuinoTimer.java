/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import quino.clases.model.Prueba;
import quino.clases.model.Ensayo;
import quino.clases.model.Resultado;
import quino.util.Aleatorio;
import quino.util.QuinoJPanel;
import quino.clases.config.*;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

/**
 *
 * @author Felipao
 */
public abstract class AbstractQuinoTimer extends TimerTask {

    protected Prueba prueba;
    protected Configuracion configuracion;
    protected Ensayo ensayo;
    protected int tiempoTranscurrido = 0;
    protected int numEnsayo = 0;
    protected int panelEstimulo;
    protected int enEspera;
    protected int preparado;
    protected int esperandoRespuesta;
    protected boolean puedeTeclear = false;
    protected boolean inOut = true;

    public AbstractQuinoTimer(Prueba prueba, Configuracion configuracion) {
        this.prueba = prueba;
        this.configuracion = configuracion;

        enEspera = porcientoDuracion(IConfiguracion.PC_EN_ESPERA);
        preparado = porcientoDuracion(IConfiguracion.PC_PREPARADO);
        esperandoRespuesta = porcientoDuracion(IConfiguracion.PC_ESPERANDO_RESPUESTA);
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
    protected void panelsClear(){
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
     * Devuelve el valor del porciento de tiempo que se utiliza en una tarea
     * a partir del tiempo de duración total IConfiguracion.TIEMPO_DURACION
     * @param porcentaje Porcentaje asignado en IConfiguracion para la
     * tarea en cuestión, por ejemplo: IConfiguracion.PC_EN_ESPERA
     * @return El valor del porciento respecto al tiempo de duración
     */
    private int porcientoDuracion(int porcentaje) {
        return (int) (porcentaje * IConfiguracion.TIEMPO_DURACION / 100);
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
        if (configuracion.isAsincronico()) {
            quinoJPanel.moverAsincronico();
        } else {
            quinoJPanel.moverEnDireccion(configuracion.getDireccion());
        }
        quinoJPanel.repaint();
    }

    /**
     * Controla el resultado de la interacción con el teclado en el ensayo
     */
    protected void controlarEnsayo() {
        if (configuracion.isControl() && panelEstimulo > 0) {
            switch (configuracion.getDireccion()) { //
                case 1: {
                    if (ensayo.getKey() != 104 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
                case 2: {
                    if (ensayo.getKey() != 98 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
                case 3: {
                    if (ensayo.getKey() != 102 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
                case 4: {
                    if (ensayo.getKey() != 100 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
                case 5: {
                    if (ensayo.getKey() != 105 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
                case 6: {
                    if (ensayo.getKey() != 103 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
                case 7: {
                    if (ensayo.getKey() != 99 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
                case 8: {
                    if (ensayo.getKey() != 97 && !ensayo.isError()) {
                        ensayo.setError(true);
                        ensayo.setDescripcion("Las direcciones no coinciden");
                    }
                }
                break;
            }
        } else if (panelEstimulo == 0) {
            ensayo.setError(true);
            ensayo.setDescripcion("No hubo Estímulo");
        }

    }

    /**
     * Define los parametros de inicio para los ensayos
     * @param cantPaneles Cantidad de paneles de cada ensayo sin contar el
     * panel central.
     */
    protected void inicializarEnsayo(int cantPaneles) {

        if (configuracion instanceof ConfiguracionAutomatica) {
            boolean controlarMovimiento = configuracion.isControl();
            configuracion = new ConfiguracionAutomatica(controlarMovimiento);
            configuracion.setDensidad(configuracion.getDensidad() / 8);
            ((ConfiguracionAutomatica) configuracion).Re_Cantidad();
        }

        ensayo = new Ensayo(configuracion);
        Aleatorio al = new Aleatorio();
        panelEstimulo = al.nextInt(0, cantPaneles);
    }

    /**
     * Captura el evento del teclado y define el tiempo de respuesta para
     * el ensayo
     * @param e El evento del teclado
     */
    protected void capturarEventoTeclado(KeyEvent e) {
        int k = e.getKeyCode();
        System.out.println("tecla presionada: " + k);

        ensayo.setKey(k);

        if (panelEstimulo > 0) {
            ensayo.setTiempoRespuesta(tiempoTranscurrido - (enEspera + preparado + 1));
        }

        controlarEnsayo();
        puedeTeclear = false;
    }

    /**
     * Obtiene los resultado finales de cada ensayo y se lo asigna a la
     * prueba en cuestión
     */
    protected void obtenerResultado(){

        int cantidad = configuracion.getCantidad();
        int tiempoMovimiento = configuracion.getTiempo_movimiento();
        int tiempoRespuesta = ensayo.getTiempoRespuesta();
        int direccion = configuracion.getDireccion();
        int densidad = configuracion.getDensidad();
        boolean error = ensayo.isError();
        boolean asincronico = configuracion.isAsincronico();
        String descrip = ensayo.getDescripcion();
        int key = ensayo.getKey();
        double velocidad = configuracion.CalcularVelocidad();
        double angulo = buscarAngulo();

        Resultado result = new Resultado(tiempoMovimiento, tiempoRespuesta, direccion,
                densidad, cantidad, error, numEnsayo, panelEstimulo, asincronico,
                descrip, key, configuracion.isControl(), velocidad, angulo);

        prueba.getResultados().add(result);
    }
}
