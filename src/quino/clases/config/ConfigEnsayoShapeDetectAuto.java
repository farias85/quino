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

    public ConfigEnsayoShapeDetectAuto(int key, int densidad, int tolerancia, double pcShape, int numFigura) {
        super(densidad, tolerancia, pcShape, numFigura);
    }

    public ConfigEnsayoShapeDetectAuto() {
        super(0, 0, 0, 0);

        Aleatorio random = new Aleatorio();
        densidad = random.nextInt(1000, 1500);
        tolerancia = random.nextInt(0, 1);
        pcShape = random.nextInt(20, 21);
        numFigura = random.nextInt(1, 4);
    }
}
