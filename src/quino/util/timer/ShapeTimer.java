/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import quino.clases.model.ShapeJPanel;
import quino.clases.model.Prueba;
import quino.util.Aleatorio;
import quino.view.edition2nd.ShapeDetectTestView;

/**
 *
 * @author Administrador
 */
public class ShapeTimer extends AbstractQuinoTimer {

    private ShapeJPanel panel1;
    private ShapeJPanel panel2;
    private ShapeDetectTestView test;

    public ShapeTimer(Prueba prueba,
            ShapeJPanel panel1, ShapeJPanel panel2,
            ShapeDetectTestView test, boolean practica) {
        super(prueba);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.test = test;
        this.practica = practica;
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
        if (inOut) {
            inOut = false;
            System.out.println("en espera " + tiempoTranscurrido);

            panelsClear();
            panelsRepaint();
        }
    }

    @Override
    protected void execPreparado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void execEjecutandoMovimiento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void execEsperandoRespuesta() {
        if (!inOut) {
            inOut = true;
            System.out.println("preparado " + tiempoTranscurrido);

            panelsRellenar();
            panelsRepaint();

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
        //throw new UnsupportedOperationException("Not supported yet.");
        if (cancelarTarea()) {
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
        Aleatorio random = new Aleatorio();
        int mostrar = random.nextInt(2, 4);
        if (mostrar % 2 == 0) {
            int seleccionarPanel = random.nextInt(1, 2);
            if (seleccionarPanel == 1) {
                panel1.rellenarShape(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                panel2.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
            } else {
                panel1.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                panel2.rellenarShape(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
            }
        } else {
            panel1.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
            panel2.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
        }

        //Eliminar esto
        panel2.rellenarShape(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
    }
}
