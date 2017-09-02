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

import quino.util.JPanelQuino;
import quino.util.JPanelCentral;
import quino.util.test.Prueba;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.util.QuinoTools;
import quino.view.test.FormaATestView;
import quino.view.test.ResultView;

public class FormaAlTimer extends AbstractFormaABTimer {

    private final JPanelQuino panel1;
    private final JPanelQuino panel2;
    private final JPanelQuino panel3;
    private final JPanelQuino panel4;
    private final JPanelQuino panel5;
    private final JPanelQuino panel6;
    private final JPanelQuino panel7;
    private final JPanelQuino panel8;
    private final JPanelCentral panel9;
    private final FormaATestView test;

    public FormaAlTimer(Prueba prueba, JPanelQuino panel1,
            JPanelQuino panel2, JPanelQuino panel3, JPanelQuino panel4, JPanelQuino panel5,
            JPanelQuino panel6, JPanelQuino panel7, JPanelQuino panel8, JPanelCentral panel9,
            FormaATestView test, boolean practica) {
        super(prueba, practica);

        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.panel4 = panel4;
        this.panel5 = panel5;
        this.panel6 = panel6;
        this.panel7 = panel7;
        this.panel8 = panel8;
        this.panel9 = panel9;

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

            panel9.setColor(Color.BLACK);
            panel9.repaint();

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

            panel9.setColor(Color.GREEN);
            panel9.repaint();

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
            inOut = true;
            System.out.println("esperando respuesta " + getTiempoTranscurrido());

            keyPress = new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (puedeTeclear) {
                        capturarEventoTeclado(e);
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
    protected void moverPuntos() {

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
            case 3: {
                moverPuntoYRepintar(panel3);
            }
            break;
            case 4: {
                moverPuntoYRepintar(panel4);
            }
            break;
            case 5: {
                moverPuntoYRepintar(panel5);
            }
            break;
            case 6: {
                moverPuntoYRepintar(panel6);
            }
            break;
            case 7: {
                moverPuntoYRepintar(panel7);
            }
            break;
            case 8: {
                moverPuntoYRepintar(panel8);
            }
            break;
        }
    }

    @Override
    protected double buscarAngulo() {
        double angulo = 0;

        if (ensayo.getConfiguracion().getPanelEstimulo() > 0) {
            switch (ensayo.getConfiguracion().getPanelEstimulo()) {
                case 1:
                    angulo = panel1.promedioAngulo();
                    break;
                case 2:
                    angulo = panel2.promedioAngulo();
                    break;
                case 3:
                    angulo = panel3.promedioAngulo();
                    break;
                case 4:
                    angulo = panel4.promedioAngulo();
                    break;
                case 5:
                    angulo = panel5.promedioAngulo();
                    break;
                case 6:
                    angulo = panel6.promedioAngulo();
                    break;
                case 7:
                    angulo = panel7.promedioAngulo();
                    break;
                case 8:
                    angulo = panel8.promedioAngulo();
                    break;
            }

            return angulo;
        }

        return 0;
    }

    @Override
    protected void panelsClear() {
        panel1.clear();
        panel2.clear();
        panel3.clear();
        panel4.clear();
        panel5.clear();
        panel6.clear();
        panel7.clear();
        panel8.clear();
    }

    @Override
    protected void panelsRepaint() {
        panel1.repaint();
        panel2.repaint();
        panel3.repaint();
        panel4.repaint();
        panel5.repaint();
        panel6.repaint();
        panel7.repaint();
        panel8.repaint();
    }

    @Override
    protected void panelsRellenar() {
        panel1.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel2.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel3.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel4.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel5.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel6.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel7.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
        panel8.rellenar(((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getDensidad(), ((ConfigEnsayoFormaAB)ensayo.getConfiguracion()).getCantidad());
    }
}
