/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import quino.util.test.Prueba;
import quino.util.QuinoTools;
import quino.view.test.ResultView;
import quino.view.test2nd.GaborTestView;

/**
 *
 * @author farias
 */
public class GaborTimer extends AbstractSinusoideTimer {
    
    private Point centro;
    private double contrat = 0.99;
    //central stimulus
    private double ftemp = 2;                      //temporal frequency in cicles / seconds (Hz)
    private double gaussianStdpix = 40;           //gaussian standar deviation [pixel]
    //periferal stimulis
    private double fspa_cpi_x_per = 0;             //spatial frequency in x, cicles / inch
    private double fspa_cpi_y_per = 1.0;           //spatial frequency in y, cicles / inch
    private double ftemp_per = 2;                  //temporal frequency in cicles / seconds (Hz)
    private int radio1 = 75;                       //internal radious
    private int radio2 = 180;                      //external radious
    private double fspa_cpp_x_per;                 //spatial frequency in x,cicles / pixels
    private double fspa_cpp_y_per;                 //spatial frequency in x,cicles / pixels
    private byte intensidadMedia = (byte) 128;
    private byte intensidadMax;
    private GaborTestView test;

    public GaborTimer(Prueba prueba, boolean practica, JPanel jPanel, GaborTestView test) {
        super(prueba, practica);

        this.test = test;
        this.jPanel = jPanel;
        mtx = new Mat(470, 460, CvType.CV_8SC1, new Scalar(0));

        fs = 90;
        fspa_cpi_x = 1;                //spatial frequency in x, cicles / inch
        fspa_cpi_y = 0.0;               //spatial frequency in y, cicles / inch
        //ppi = 26;
        centro = new Point(mtx.width() / 2, mtx.height() / 2);

        fspa_cpp_x = fspa_cpi_x / ppi;
        fspa_cpp_y = fspa_cpi_y / ppi;
        fspa_cpp_x_per = fspa_cpi_x_per / ppi;
        fspa_cpp_y_per = fspa_cpi_y_per / ppi;

        intensidadMax = (byte) (contrat * intensidadMedia);
    }

    @Override
    protected void execEnEspera() {
        if (inOut) {
            inOut = false;
            System.out.println("en espera " + tiempoTranscurrido);

            inicializarEnsayo();
        }
    }

    @Override
    protected void execEsperandoRespuesta() {
        if (!inOut) {
            inOut = true;
            System.out.println("preparado " + tiempoTranscurrido);

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

        runMatrix();
        tiempoTranscurrido += 100;
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

    private void runMatrix() {

        for (int i = 0; i < mtx.cols(); i++) {
            double periodo = i / fs;

            //I1 = Imax* exp(- (( x - x0).^2 + (y - y0).^2) / (2*(gaussianStdpix).^2) );
            for (int j = 0; j < mtx.rows(); j++) {
                Point point = new Point(i, j);

                double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, centro.x, centro.y);

                if (distancia < radio2) {
                    double intensidad1 = 0;
                    intensidad1 = Math.pow(i - centro.x, 2) + Math.pow(j - centro.y, 2);
                    intensidad1 /= 2 * Math.pow(gaussianStdpix, 2);
                    intensidad1 = intensidadMax * Math.exp(-intensidad1);
                    intensidad1 *= Math.cos(2.0 * Math.PI * (fspa_cpp_x * (i + count)
                            + fspa_cpp_y * (j + count) + periodo * ftemp));

                    double intensidad2 = intensidadMedia + intensidadMax * Math.cos(2.0 * Math.PI * (fspa_cpp_x_per * (i + count)
                            + fspa_cpp_y_per * (j + count) + periodo * ftemp_per));

                    mtx.put(j, i, gaborLocation(point) ? (byte) intensidad1 : (byte) intensidad2);
                }
            }
        }

        Image image = QuinoTools.matToBufferedImage(mtx);
        jPanel.getGraphics().drawImage(image, 0, 0, jPanel);

        System.out.println(count + " esto es una prueba");
        count++;
    }

    private boolean gaborLocation(Point point) {
        double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, centro.x, centro.y);
        return !(distancia > radio1 && distancia < radio2);
    }
}
