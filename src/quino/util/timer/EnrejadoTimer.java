/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import quino.clases.config.ConfigEnsayoEnrejado;
import quino.util.test.Prueba;
import quino.util.QuinoTools;
import quino.view.test.ResultView;
import quino.view.test2nd.EnrejadoTestView;

/**
 *
 * @author farias
 */
public class EnrejadoTimer extends AbstractSinusoideTimer {

    private EnrejadoTestView test;
    private ConfigEnsayoEnrejado configEnsayo;

    public EnrejadoTimer(Prueba prueba, boolean practica, JPanel jPanel, EnrejadoTestView test) {
        super(prueba, practica);

        this.test = test;
        this.jPanel = jPanel;
        mtx = new Mat(470, 660, CvType.CV_8SC1, new Scalar(255));
    }

    @Override
    protected void execEnEspera() {
        if (inOut) {
            inOut = false;
            System.out.println("en espera " + getTiempoTranscurrido());

            if (ensayo.getConfiguracion() instanceof ConfigEnsayoEnrejado) {
                this.configEnsayo = ((ConfigEnsayoEnrejado) ensayo.getConfiguracion());
            } else {
                System.err.println("El ensayo no es de tipo ConfigEnsayoEnrejado en la clase EnrejadoTimer");
            }

            jPanel.repaint();
        }
    }

    @Override
    protected void execEsperandoRespuesta() {
        if (!inOut) {
            inOut = true;
            System.out.println("preparado " + getTiempoTranscurrido());

            keyPress = new KeyListener() {

                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void keyPressed(KeyEvent e) {
                    if (puedeTeclear) {
                        int k = e.getKeyCode();
                        System.out.println("tecla presionada: " + k);

                        resultado.setKey(k);

                        if (configEnsayo.getDireccion() > 0) {
                            resultado.setTiempoRespuesta((int) (getTiempoTranscurrido() - (enEspera + 1)));
                        }

                        if (configEnsayo.getDireccion() == 0) {
                            resultado.setError(true);
                            resultado.setDescripcion("No hubo movimiento");
                        } else if (configEnsayo.getKey() != resultado.getKey()) {
                            resultado.setError(true);
                            resultado.setDescripcion("La tecla presionada no es la esperada");
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

            runMatrix();
        }

        if (configEnsayo.isOnMove()) {
            runMatrix();
        }

        //tiempoTranscurrido += 50;
    }

    @Override
    protected void execTerminado() {
        System.out.println("terminado " + getTiempoTranscurrido());
        test.removeKeyListener(keyPress);

        if (resultado.getKey() == 0 && configEnsayo.getDireccion() > 0) {
            resultado.setError(true);
            resultado.setDescripcion("Omisión");
        }

        ensayo.setResultado(resultado);

        if (cancelarTarea()) {
            if (!practica) {
                QuinoTools.salvarPruebaEnRegistro(test.getParentView(), test, prueba);
            }
            ResultView res = new ResultView(test.getParentView(), true);
            test.setVisible(false);
            res.setVisible(true);
        }
    }

    private void runMatrix() {
        for (int i = 0; i < mtx.cols(); i++) {
            double periodo = i / configEnsayo.getFs();

            for (int j = 0; j < mtx.rows(); j++) {
                double intensidad = 128 + 40.8
                        * Math.cos(2.0 * Math.PI * (configEnsayo.getFspa_cpp_x() * (i + count)
                        + configEnsayo.getFspa_cpp_y() * (j + count) + periodo));

                mtx.put(j, i, (byte) intensidad);
            }
        }

        Image image = QuinoTools.matToBufferedImage(mtx);
        jPanel.getGraphics().drawImage(image, 0, 0, jPanel);

        System.out.println(count + " enrejado");
        count += configEnsayo.isSentidoUpLeft() ? 1 : -1;
    }
}
