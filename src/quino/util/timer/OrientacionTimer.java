/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import quino.clases.config.ConfigEnsayoEnrejado;
import quino.util.Aleatorio;
import quino.util.test.Prueba;
import quino.util.QuinoTools;
import quino.view.test.ResultView;
import quino.view.test2nd.OrientacionTestView;

/**
 *
 * @author Administrador
 */
public class OrientacionTimer extends AbstractNoMoveTimer {

    private JPanel jpanel1;
    private JPanel jpanel2;
    private OrientacionTestView test;
    private ConfigEnsayoEnrejado configEnsayo;
    private int radio = 150;

    public OrientacionTimer(Prueba prueba, JPanel panel1, JPanel panel2,
            OrientacionTestView test, boolean practica) {
        super(prueba, practica);

        this.jpanel1 = panel1;
        this.jpanel2 = panel2;
        this.test = test;
    }

    @Override
    protected void execEnEspera() {
        if (inOut) {
            inOut = false;
            System.out.println("en espera " + getTiempoTranscurrido());

            if (ensayo.getConfiguracion() instanceof ConfigEnsayoEnrejado) {
                this.configEnsayo = ((ConfigEnsayoEnrejado) ensayo.getConfiguracion());
            } else {
                System.err.println("El ensayo no es de tipo ConfigEnsayoEnrejado en la clase OrientacionTimer");
            }

            panelsRepaint();
        }
    }

    @Override
    protected void execEsperandoRespuesta() {
        if (!inOut) {
            inOut = true;
            System.out.println("preparado " + getTiempoTranscurrido());

            panelsRellenar();
            //panelsRepaint();

            keyPress = new KeyListener() {

                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void keyPressed(KeyEvent e) {
                    if (puedeTeclear) {
                        int k = e.getKeyCode();
                        System.out.println("tecla presionada: " + k);

                        resultado.setKey(k);

                        if (ensayo.getConfiguracion().getPanelEstimulo() > 0) {
                            resultado.setTiempoRespuesta((int) (getTiempoTranscurrido() - (enEspera + 1)));
                        }

                        if (ensayo.getConfiguracion().getPanelEstimulo() == 0) {
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
        System.out.println("terminado " + getTiempoTranscurrido());
        test.removeKeyListener(keyPress);

        if (resultado.getKey() == 0 && ensayo.getConfiguracion().getPanelEstimulo() > 0) {
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

    @Override
    protected void panelsRepaint() {
        jpanel1.repaint();
        jpanel2.repaint();
    }

    private void runMatrix(JPanel jpanel, ConfigEnsayoEnrejado cee) {
        Mat mtx = new Mat(470, 460, CvType.CV_8SC1, new Scalar(0));
        Point centro = new Point(mtx.width() / 2, mtx.height() / 2);

        for (int i = 0; i < mtx.cols(); i++) {
            double periodo = i / cee.getFs();

            for (int j = 0; j < mtx.rows(); j++) {
                Point point = new Point(i, j);

                if (circleLocation(point, centro)) {
                    double intensidad = cee.getIntensidadMedia() + cee.getIntensidadMax() //40.8
                            * Math.cos(2.0 * Math.PI * (cee.getFspa_cpp_x() * i
                            + cee.getFspa_cpp_y() * j + periodo));

                    mtx.put(j, i, (byte) intensidad);
                }
            }
        }

        jpanel.getGraphics().drawImage(QuinoTools.matToBufferedImage(mtx), 0, 0, jpanel);
    }

    private boolean circleLocation(Point point, Point centro) {
        double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, centro.x, centro.y);
        return distancia < radio;
    }

    @Override
    protected void panelsRellenar() {
        ConfigEnsayoEnrejado patronEstatico = new ConfigEnsayoEnrejado(3, configEnsayo.getPpi(), false);
        Aleatorio random = new Aleatorio();
        ConfigEnsayoEnrejado patronPrueba = new ConfigEnsayoEnrejado(random.nextInt(5, 6), configEnsayo.getPpi(), false);

        switch (ensayo.getConfiguracion().getPanelEstimulo()) {
            case 0:
                runMatrix(jpanel1, patronEstatico);
                runMatrix(jpanel2, patronEstatico);
                break;
            case 1:
                runMatrix(jpanel1, patronPrueba);
                runMatrix(jpanel2, patronEstatico);
                break;
            case 2:
                runMatrix(jpanel1, patronEstatico);
                runMatrix(jpanel2, patronPrueba);
                break;
        }
    }
}
