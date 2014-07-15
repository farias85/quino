/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import quino.clases.config.ConfigEnsayoGabor;
import quino.util.test.Prueba;
import quino.util.QuinoTools;
import quino.view.test.ResultView;
import quino.view.test2nd.GaborTestView;

/**
 *
 * @author farias
 */
public class GaborTimer extends AbstractSinusoideTimer {

    private GaborTestView test;
    private ConfigEnsayoGabor configEnsayo;

    public GaborTimer(Prueba prueba, boolean practica, JPanel jPanel, GaborTestView test) {
        super(prueba, practica);

        this.test = test;
        this.jPanel = jPanel;
        mtx = new Mat(470, 460, CvType.CV_8SC1, new Scalar(0));
    }

    @Override
    protected void execEnEspera() {
        if (inOut) {
            inOut = false;
            System.out.println("en espera " + getTiempoTranscurrido());

            if (ensayo.getConfiguracion() instanceof ConfigEnsayoGabor) {
                this.configEnsayo = ((ConfigEnsayoGabor) ensayo.getConfiguracion());
            } else {
                System.err.println("El ensayo no es de tipo ConfigEnsayoGabor en la clase GaborTimer");
            }

            configEnsayo.setCentro(new Point(mtx.width() / 2, mtx.height() / 2));
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

        runMatrix();
        //tiempoTranscurrido += 100;
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

            //I1 = Imax* exp(- (( x - x0).^2 + (y - y0).^2) / (2*(gaussianStdpix).^2) );
            for (int j = 0; j < mtx.rows(); j++) {
                Point point = new Point(i, j);

                double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, configEnsayo.getCentro().x, configEnsayo.getCentro().y);

                if (distancia < configEnsayo.getRadio2()) {
                    //Circulo de adentro
                    double intensidad1 = 0;
                    intensidad1 = Math.pow(i - configEnsayo.getCentro().x, 2) + Math.pow(j - configEnsayo.getCentro().y, 2);
                    intensidad1 /= 2 * Math.pow(configEnsayo.getGaussianStdpix(), 2);
                    intensidad1 = configEnsayo.getIntensidadMax() * Math.exp(-intensidad1);
                    intensidad1 *= Math.cos(2.0 * Math.PI * (configEnsayo.getFspa_cpp_x() * (i + count)
                            + configEnsayo.getFspa_cpp_y() * (j + count) + periodo * configEnsayo.getFtemp()));
                    //intensidad1 += configEnsayo.getIntensidadMax();

                    //Circulo de afuera
                    double intensidad2 = configEnsayo.getIntensidadMedia() + configEnsayo.getIntensidadMax()
                            * Math.cos(2.0 * Math.PI * (configEnsayo.getFspa_cpp_x_per() * (i + count)
                            + configEnsayo.getFspa_cpp_y_per() * (j + count) + periodo * configEnsayo.getFtemp_per()));

                    mtx.put(j, i, gaborLocation(point) ? (byte) intensidad1 : (byte) intensidad2);
                }
            }
        }

        Image image = QuinoTools.matToBufferedImage(mtx);
        jPanel.getGraphics().drawImage(image, 0, 0, jPanel);

        System.out.println(count + " esto es run matrix");
        count += configEnsayo.isSentidoUpLeft() ? 1 : -1;
    }

    private boolean gaborLocation(Point point) {
        double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, configEnsayo.getCentro().x, configEnsayo.getCentro().y);
        return !(distancia > configEnsayo.getRadio1() && distancia < configEnsayo.getRadio2());
    }
}
