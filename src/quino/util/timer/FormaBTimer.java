/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import quino.util.JPanelQuino;
import quino.util.JPanelCentral;
import quino.util.test.Prueba;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.util.QuinoTools;
import quino.view.test.FormaBTestView;
import quino.view.test.ResultView;

/**
 *
 * @author Felipao
 */
public class FormaBTimer extends AbstractFormaABTimer {

    private JPanelQuino panel1;
    private JPanelQuino panel2;
    private JPanelCentral panel3;
    private FormaBTestView test;

    public FormaBTimer(Prueba prueba,
            JPanelQuino panel1, JPanelQuino panel2, JPanelCentral panel3,
            FormaBTestView test, boolean practica) {
        super(prueba, practica);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
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
            System.out.println("en espera " + getTiempoTranscurrido());

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
            System.out.println("preparado " + getTiempoTranscurrido());

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
            System.out.println("ejecutando el movimiento " + getTiempoTranscurrido());

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
            System.out.println("esperando respuesta " + getTiempoTranscurrido());
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
        System.out.println("terminado " + getTiempoTranscurrido());
        test.removeKeyListener(keyPress);

        if (resultado.getKey() == 0 && ensayo.getConfiguracion().getPanelEstimulo() > 0) {
            resultado.setError(true);
            resultado.setDescripcion("Omisión");
        }

        obtenerResultado();

        if (cancelarTarea()) {
            if (!practica) {
                QuinoTools.salvarPruebaEnRegistro(test.getParentView(), test, prueba);
            }
            ResultView res = new ResultView(test.getParentView(), true);
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
        panel1.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel2.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
    }

    @Override
    protected void moverPuntos() {
        //double desplazamientoY = test.getLocation().getY();
        switch (ensayo.getConfiguracion().getPanelEstimulo()) {
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
        if (ensayo.getConfiguracion().getPanelEstimulo() > 0) {
            if (ensayo.getConfiguracion().getPanelEstimulo() == 1) {
                angulo = panel1.promedioAngulo();
            } else {
                angulo = panel2.promedioAngulo();
            }
            return angulo;
        }
        return 0;
    }
}
