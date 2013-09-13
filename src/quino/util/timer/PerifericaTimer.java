/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import quino.util.QuinoJPanel;
import quino.util.CentralJPanel;
import quino.util.Punto;
<<<<<<< HEAD
import quino.clases.config.ConfigEnsayo;
=======
>>>>>>> b4b1a9087c50544423bd17c82b4fe43f6dfcf7e4
import quino.clases.model.Prueba;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import quino.util.QuinoTools;
import quino.view.prueba.PerifericaTestView;
import quino.view.prueba.ResultView;

/**
 *
 * @author Felipao
 */
public class PerifericaTimer extends AbstractQuinoTimer {

    private QuinoJPanel panel1;
    private QuinoJPanel panel2;
    private CentralJPanel panel3;
    private PerifericaTestView test;

<<<<<<< HEAD
    public PerifericaTimer(Prueba prueba, 
=======
    public PerifericaTimer(Prueba prueba,
>>>>>>> b4b1a9087c50544423bd17c82b4fe43f6dfcf7e4
            QuinoJPanel panel1, QuinoJPanel panel2, CentralJPanel panel3,
            PerifericaTestView test, boolean practica) {
        super(prueba);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.test = test;
        this.practica = practica;
    }

    /**
     * Momento de inicio de ensayo o cambio de tarea. Para q se ejecute esta
     * tarea el atributo boolean inOut debe ser true
     */
    @Override
    protected void execEnEspera() {
        if (inOut) {
            inOut = false;
            System.out.println("en espera " + tiempoTranscurrido);

            panel3.setColor(Color.BLACK);
            panel3.repaint();

            panelsClear();
            panelsRepaint();
        }
    }

    /**
     * Momento de inicio del ensayo, preparado para recibir la acción de
     * movimientiento o no. Para q se ejecute esta tarea el atributo boolean
     * inOut debe ser false
     */
    @Override
    protected void execPreparado() {
        if (!inOut) {
            inOut = true;
            System.out.println("preparado " + tiempoTranscurrido);

            panel3.setColor(Color.GREEN);
            panel3.repaint();

            panelsRellenar();
            panelsRepaint();
        }
    }

    /**
     * Momento en el q se ejecuta el movimiento o no. Para q se ejecute esta
     * tarea el atributo boolean inOut debe ser true
     */
    @Override
    protected void execEjecutandoMovimiento() {
        if (inOut) {
            inOut = false;
            System.out.println("ejecutando el movimiento " + tiempoTranscurrido);

            inicializarEnsayo(2);
            moverPuntos();
        }
    }

    /**
     * Momento de espera de la respuesta. Para q se ejecute esta
     * tarea el atributo boolean inOut debe ser false
     */
    @Override
    protected void execEsperandoRespuesta() {
        if (!inOut) {
            System.out.println("esperando respuesta " + tiempoTranscurrido);
            inOut = true;

            keyPress = new KeyListener() {

                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void keyPressed(KeyEvent e) {
                    if (puedeTeclear) {
                        capturarEventoTeclado(e);
                    }
                }

                public void keyReleased(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }
            };

            test.addKeyListener(keyPress);
            puedeTeclear = true;
        }
    }

    @Override
    protected void execTerminado() {
        System.out.println("terminado " + tiempoTranscurrido);
        test.removeKeyListener(keyPress);

        if (resultado.getKey() == 0 && ensayo.getPanelEstimulo() > 0) {
            resultado.setError(true);
            resultado.setDescripcion("Omisión");
        }

        obtenerResultado();

        if (cancelarTarea()) {
            if (!practica) {
                QuinoTools.salvarPruebaEnRegistro(test.getParentView(), test, prueba);
            }
            ResultView res = new ResultView(test.getParentView(), true, false);
            test.setVisible(false);
            res.setVisible(true);
        }
    }

    @Override
    protected void panelsClear() {
        panel1.clear();
        panel2.clear();
    }

    @Override
    protected void panelsRepaint() {
        panel1.repaint();
        panel2.repaint();
    }

    @Override
    protected void panelsRellenar() {
        panel1.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel2.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
    }

    @Override
    protected void moverPuntos() {
<<<<<<< HEAD
        double desplazamientX = test.getLocation().getX();
        double desplazamientoY = test.getLocation().getY();

=======
>>>>>>> b4b1a9087c50544423bd17c82b4fe43f6dfcf7e4
        switch (ensayo.getPanelEstimulo()) {
            case 0: {
                panelsRepaint();
            }
            break;
            case 1: {
                moverPuntoYRepintar(panel1);
            }
            break;
            case 2: {
                moverPuntoYRepintar(panel2);
            }
            break;
        }
    }

    @Override
    protected double buscarAngulo() {
        double angulo = 0;
<<<<<<< HEAD
        
        if (ensayo.getPanelEstimulo() > 0) {
            if (ensayo.getPanelEstimulo() == 1) {
                p2 = panel1.MidPunto(false);
=======

        if (ensayo.getPanelEstimulo() > 0) {
            if (ensayo.getPanelEstimulo() == 1) {
                angulo = panel1.promedioAngulo();
>>>>>>> b4b1a9087c50544423bd17c82b4fe43f6dfcf7e4
            } else {
                angulo = panel2.promedioAngulo();
            }
            return angulo;
        }
        return 0;
    }
}
