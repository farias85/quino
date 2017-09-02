/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 04/10/2014.
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
import quino.clases.config.ConfigEnsayoVelocidad;
import quino.clases.model.Velocidad;
import quino.util.test.Prueba;
import quino.util.QuinoTools;
import quino.view.test.ResultView;
import quino.view.test2nd.VelocidadTestView;

public class VelocidadTimer extends AbstractNoMoveTimer {

    private final JPanel jpanel1;
    private final JPanel jpanel2;
    private final VelocidadTestView test;
    private ConfigEnsayoVelocidad configEnsayo;
    private final int radio = 150;

    public VelocidadTimer(Prueba prueba, JPanel panel1, JPanel panel2,
            VelocidadTestView test, boolean practica) {
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

            if (ensayo.getConfiguracion() instanceof ConfigEnsayoVelocidad) {
                this.configEnsayo = ((ConfigEnsayoVelocidad) ensayo.getConfiguracion());
            } else {
                System.err.println("El ensayo no es de tipo ConfigEnsayoVelocidad en la clase VelocidadTimer");
            }

            panelsRepaint();
        }
    }

    @Override
    protected void execEsperandoRespuesta() {
        if (!inOut) {
            inOut = true;
            System.out.println("preparado " + getTiempoTranscurrido());

            //panelsRellenar();
            //panelsRepaint();

            keyPress = new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
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
                            resultado.setDescripcion("Opción incorrecta");
                        }

                        puedeTeclear = false;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }
            };

            test.addKeyListener(keyPress);
            puedeTeclear = true;
        }

        panelsRellenar();
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

    private void runMatrix(JPanel jpanel, ConfigEnsayoVelocidad cee, Velocidad factor) {

        Mat mtx = new Mat(470, 460, CvType.CV_8SC1, new Scalar(0));
        Point centro = new Point(mtx.width() / 2, mtx.height() / 2);
        
        for (int i = 0; i < mtx.cols(); i++) {
            double periodo = i / cee.getFs();

            for (int j = 0; j < mtx.rows(); j++) {
                Point point = new Point(i, j);

                if (circleLocation(point, centro)) {
                    double intensidad = cee.getIntensidadMedia() + cee.getIntensidadMax() //40.8
                            * Math.cos(2.0 * Math.PI * (cee.getFspa_cpp_x() * (i + factor.getCount())
                            + cee.getFspa_cpp_y() * (j + factor.getCount()) + periodo));

                    mtx.put(j, i, (byte) intensidad);
                }
            }
        }

        jpanel.getGraphics().drawImage(QuinoTools.matToBufferedImage(mtx), 0, 0, jpanel);

        factor.go();
        System.out.println("Factor: " + factor.getCount());
    }

    private boolean circleLocation(Point point, Point centro) {
        double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, centro.x, centro.y);
        return distancia < radio;
    }

    @Override
    protected void panelsRellenar() {
        
        Velocidad lento = configEnsayo.getVelocidadSecundaria();
        Velocidad rapido = configEnsayo.getVelocidadPrimaria();
        
        switch (ensayo.getConfiguracion().getPanelEstimulo()) {
            case 0: {
                runMatrix(jpanel1, configEnsayo, lento);
                runMatrix(jpanel2, configEnsayo, lento);
            }
            break;
            case 1: {
                runMatrix(jpanel1, configEnsayo, rapido);
                runMatrix(jpanel2, configEnsayo, lento);
            }
            break;
            case 2: {
                runMatrix(jpanel1, configEnsayo, lento);
                runMatrix(jpanel2, configEnsayo, rapido);
            }
            break;
        }
    }
}
