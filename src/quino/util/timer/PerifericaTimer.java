/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import quino.util.QuinoJPanel;
import quino.util.CentralJPanel;
import quino.util.Punto;
import quino.clases.config.Configuracion;
import quino.clases.model.Prueba;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private boolean practica;
    private PerifericaTestView test;
    private KeyListener keyPress;

    public PerifericaTimer(Prueba prueba, Configuracion configuracion,
            QuinoJPanel panel1, QuinoJPanel panel2, CentralJPanel panel3,
            PerifericaTestView test, boolean practica) {
        super(prueba, configuracion);

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

        if (ensayo.getKey() == 0 && panelEstimulo > 0) {
            ensayo.setError(true);
            ensayo.setDescripcion("Omisión");
        }

        obtenerResultado();

        if (cancelarTarea()) {
            if (!practica) {
                test.GuardarPrueba(prueba);
            }
            ResultView res = new ResultView(test.getParet(), true, prueba);
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
        panel1.Rellenar(configuracion.getDensidad(), configuracion.getCantidad());
        panel2.Rellenar(configuracion.getDensidad(), configuracion.getCantidad());
    }

    @Override
    protected void moverPuntos() {
        switch (panelEstimulo) {
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
        Punto p1 = null;
        Punto p2 = null;
        double angulo = 0;
        
        if (panelEstimulo > 0) {
            if (panelEstimulo == 1) {
                p2 = panel1.MidPunto(false);
            } else {
                p2 = panel2.MidPunto(false);
            }
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            double x = d.getWidth() / 2;
            double y = d.getHeight() / 2;
            p1 = new Punto(x, y);
            angulo = ensayo.CalcularAngulo(p1, p2);
        }

        return angulo;
    }
}
