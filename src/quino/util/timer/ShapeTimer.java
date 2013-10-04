/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import quino.clases.config.ConfigEnsayoShapeDetect;
import quino.util.JPanelShape;
import quino.util.test.Prueba;
import quino.util.QuinoTools;
import quino.view.test.ResultView;
import quino.view.test2nd.ShapeDetectTestView;

/**
 *
 * @author Administrador
 */
public class ShapeTimer extends AbstractNoMoveTimer {

    private JPanelShape panel1;
    private JPanelShape panel2;
    private ShapeDetectTestView test;
    private ConfigEnsayoShapeDetect configEnsayo;

    public ShapeTimer(Prueba prueba,
            JPanelShape panel1, JPanelShape panel2,
            ShapeDetectTestView test, boolean practica) {
        super(prueba, practica);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.test = test;

        if (ensayo.getConfiguracion() instanceof ConfigEnsayoShapeDetect) {
            this.configEnsayo = ((ConfigEnsayoShapeDetect) ensayo.getConfiguracion());
        } else {
            System.err.println("El ensayo no es de tipo ConfigEnsayoShapeDetect en la clase ShapeTimer");
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
                            resultado.setDescripcion("Dirección incorrecta");
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
                panel1.rellenar(configEnsayo.getDensidad());
                panel2.rellenar(configEnsayo.getDensidad());
                break;
            case 1:
                panel1.rellenarShape(configEnsayo.getDensidad(), configEnsayo.getPcShape(), configEnsayo.getTolerancia());
                panel2.rellenar(configEnsayo.getDensidad());
                break;
            case 2:
                panel1.rellenar(configEnsayo.getDensidad());
                panel2.rellenarShape(configEnsayo.getDensidad(), configEnsayo.getPcShape(), configEnsayo.getTolerancia());
                break;
        }
    }
}
