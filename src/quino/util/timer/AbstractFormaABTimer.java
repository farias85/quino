/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.event.KeyEvent;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.util.JPanelQuino;
import quino.util.QuinoTools;
import quino.util.test.Prueba;

/**
 *
 * @author farias
 */
public abstract class AbstractFormaABTimer extends AbstractQuinoTimer {

    protected ConfigEnsayoFormaAB configEnsayo;

    public AbstractFormaABTimer(Prueba prueba, boolean practica) {
        super(prueba, practica);

        if (ensayo.getConfiguracion() instanceof ConfigEnsayoFormaAB) {
            this.configEnsayo = ((ConfigEnsayoFormaAB) ensayo.getConfiguracion());
        } else {
            System.err.println("El ensayo no es de tipo ConfigEnsayoFormaAB en la clase AbstractFormaABTimer");
        }
    }

    /**
     * Mueve los puntos de un jpanel segun la configuración determinada
     * @param quinoJPanel El jpanel en el q se moverán los puntos
     */
    protected void moverPuntoYRepintar(JPanelQuino quinoJPanel) {
        if (configEnsayo.isAsincronico()) {
            quinoJPanel.moverAsincronico();
        } else {
            quinoJPanel.moverEnDireccion(configEnsayo.getDireccion());
        }
        quinoJPanel.repaint();
    }

    /**
     * Controla el resultado de la interacción con el teclado en el ensayo
     */
    protected void controlarEnsayo() {
        if (configEnsayo.isControl()
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
     * Captura el evento del teclado y define el tiempo de respuesta para
     * el ensayo
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
     * Obtiene los resultado finales de cada ensayo y se lo asigna a la
     * prueba en cuestión
     */
    protected void obtenerResultado() {
        resultado.setVelocidad(QuinoTools.getVelocidad(configEnsayo.getTiempoMovimiento()));
        resultado.setAngulo(buscarAngulo());
        ensayo.setResultado(resultado);
    }
}
