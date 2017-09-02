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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 04/10/2013.
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

public class ShapeTimer extends AbstractNoMoveTimer {

    private final JPanelShape panel1;
    private final JPanelShape panel2;
    private final ShapeDetectTestView test;
    private ConfigEnsayoShapeDetect configEnsayo;

    public ShapeTimer(Prueba prueba,
            JPanelShape panel1, JPanelShape panel2,
            ShapeDetectTestView test, boolean practica) {
        super(prueba, practica);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.test = test;
    }

    @Override
    protected void execEnEspera() {
        if (inOut) {
            inOut = false;
            System.out.println("en espera " + getTiempoTranscurrido());

            if (ensayo.getConfiguracion() instanceof ConfigEnsayoShapeDetect) {
                this.configEnsayo = ((ConfigEnsayoShapeDetect) ensayo.getConfiguracion());
            } else {
                System.err.println("El ensayo no es de tipo ConfigEnsayoShapeDetect en la clase ShapeTimer");
            }

            panelsClear();
            panelsRepaint();
        }
    }

    @Override
    protected void execEsperandoRespuesta() {
        if (!inOut) {
            inOut = true;
            System.out.println("preparado " + getTiempoTranscurrido());

            panelsRellenar();
            panelsRepaint();

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
                            resultado.setDescripcion("Dirección incorrecta");
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
        switch (ensayo.getConfiguracion().getPanelEstimulo()) {
            case 0:
                panel1.rellenar(configEnsayo.getDensidad());
                panel2.rellenar(configEnsayo.getDensidad());
                break;
            case 1:
                panel1.rellenarShape(configEnsayo.getDensidad(), configEnsayo.getPcShape(), configEnsayo.getTolerancia(), configEnsayo.getNumFigura());
                panel2.rellenar(configEnsayo.getDensidad());
                break;
            case 2:
                panel1.rellenar(configEnsayo.getDensidad());
                panel2.rellenarShape(configEnsayo.getDensidad(), configEnsayo.getPcShape(), configEnsayo.getTolerancia(), configEnsayo.getNumFigura());
                break;
        }
    }
}
