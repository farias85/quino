/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import quino.util.QuinoJPanel;
import quino.util.CentralJPanel;
import quino.util.test.Prueba;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import quino.util.QuinoTools;
import quino.view.test.FovealTestView;
import quino.view.test.ResultView;

/**
 *
 * @author Felipao
 */
public class FovealTimer extends AbstractQuinoTimer {

    private QuinoJPanel panel1;
    private QuinoJPanel panel2;
    private QuinoJPanel panel3;
    private QuinoJPanel panel4;
    private QuinoJPanel panel5;
    private QuinoJPanel panel6;
    private QuinoJPanel panel7;
    private QuinoJPanel panel8;
    private CentralJPanel panel9;
    private FovealTestView test;

    public FovealTimer(Prueba prueba, QuinoJPanel panel1,
            QuinoJPanel panel2, QuinoJPanel panel3, QuinoJPanel panel4, QuinoJPanel panel5,
            QuinoJPanel panel6, QuinoJPanel panel7, QuinoJPanel panel8, CentralJPanel panel9,
            FovealTestView test, boolean practica) {
        super(prueba, practica);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.panel4 = panel4;
        this.panel5 = panel5;
        this.panel6 = panel6;
        this.panel7 = panel7;
        this.panel8 = panel8;
        this.panel9 = panel9;
        
        this.test = test;
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

            panel9.setColor(Color.BLACK);
            panel9.repaint();

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

            panel9.setColor(Color.GREEN);
            panel9.repaint();

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

            inicializarEnsayo();
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
            inOut = true;
            System.out.println("esperando respuesta " + tiempoTranscurrido);

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
            ResultView res = new ResultView(test.getParentView(), true, true);
            test.setVisible(false);
            res.setVisible(true);
        }
    }

    @Override
    protected void moverPuntos() {

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
            case 3: {
                moverPuntoYRepintar(panel3);
            }
            break;
            case 4: {
                moverPuntoYRepintar(panel4);
            }
            break;
            case 5: {
                moverPuntoYRepintar(panel5);
            }
            break;
            case 6: {
                moverPuntoYRepintar(panel6);
            }
            break;
            case 7: {
                moverPuntoYRepintar(panel7);
            }
            break;
            case 8: {
                moverPuntoYRepintar(panel8);
            }
            break;
        }
    }

    @Override
    protected double buscarAngulo() {
        double angulo = 0;

        if (ensayo.getPanelEstimulo() > 0) {
            switch (ensayo.getPanelEstimulo()) {
                case 1:
                    angulo = panel1.promedioAngulo();
                    break;
                case 2:
                    angulo = panel2.promedioAngulo();
                    break;
                case 3:
                    angulo = panel3.promedioAngulo();
                    break;
                case 4:
                    angulo = panel4.promedioAngulo();
                    break;
                case 5:
                    angulo = panel5.promedioAngulo();
                    break;
                case 6:
                    angulo = panel6.promedioAngulo();
                    break;
                case 7:
                    angulo = panel7.promedioAngulo();
                    break;
                case 8:
                    angulo = panel8.promedioAngulo();
                    break;
            }

            return angulo;
        }

        return 0;
    }

    @Override
    protected void panelsClear() {
        panel1.clear();
        panel2.clear();
        panel3.clear();
        panel4.clear();
        panel5.clear();
        panel6.clear();
        panel7.clear();
        panel8.clear();
    }

    @Override
    protected void panelsRepaint() {
        panel1.repaint();
        panel2.repaint();
        panel3.repaint();
        panel4.repaint();
        panel5.repaint();
        panel6.repaint();
        panel7.repaint();
        panel8.repaint();
    }

    @Override
    protected void panelsRellenar() {
        panel1.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel2.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel3.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel4.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel5.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel6.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel7.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        panel8.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
    }
}
