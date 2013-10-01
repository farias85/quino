/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import quino.clases.model.ShapeJPanel;
import quino.clases.model.Prueba;
import quino.util.QuinoTools;
import quino.view.prueba.ResultView;
import quino.view.prueba2nd.ShapeDetectTestView;

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
        super(prueba, practica);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.test = test;
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
            inicializarEnsayo();
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
                        int k = e.getKeyCode();
                        System.out.println("tecla presionada: " + k);

                        resultado.setKey(k);

                        if (ensayo.getPanelEstimulo() > 0) {
                            resultado.setTiempoRespuesta(tiempoTranscurrido - (enEspera + 1));
                        }

                        if (ensayo.getPanelEstimulo() == 0) {
                            resultado.setError(true);
                            resultado.setDescripcion("No hubo estímulo");
                        } else if (ensayo.getConfiguracion().getKey() != resultado.getKey()) {
                            resultado.setError(true);
                            resultado.setDescripcion("No se ha presionado la tecla esperada");
                        }

                        puedeTeclear = false;
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

        ensayo.setResultado(resultado);

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
        switch (ensayo.getPanelEstimulo()) {
            case 0:
                panel1.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                panel2.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                break;
            case 1:
                panel1.rellenarShape(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                panel2.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                break;
            case 2:
                panel1.rellenar(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                panel2.rellenarShape(ensayo.getConfiguracion().getDensidad(), ensayo.getConfiguracion().getCantidad());
                break;
        }
    }
}
