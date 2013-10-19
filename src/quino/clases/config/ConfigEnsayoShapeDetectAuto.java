/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import quino.util.Aleatorio;

/**
 *
 * @author farias
 */
public class ConfigEnsayoShapeDetectAuto extends ConfigEnsayoShapeDetect {

    public ConfigEnsayoShapeDetectAuto(int key, int panelEstimulo, int densidad, int tolerancia, double pcShape, int numFigura) {
        super(key, panelEstimulo, densidad, tolerancia, pcShape, numFigura);
    }

    public ConfigEnsayoShapeDetectAuto() {
        super();

        Aleatorio random = new Aleatorio();
        densidad = random.nextInt(1000, 1500);
        tolerancia = random.nextInt(0, 1);
        pcShape = random.nextInt(8, 12);
        numFigura = random.nextInt(1, 4);

        panelEstimulo = random.nextInt(0, 2);

        switch (panelEstimulo) {
            case 0:
                key = 0; //ninguna tecla
                break;
            case 1:
                key = 37;//tecla de la flecha izquierda
                break;
            case 2:
                key = 39;//tecla de la flecha derecha
                break;
        }
    }
}
