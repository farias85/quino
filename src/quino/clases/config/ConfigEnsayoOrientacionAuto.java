/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

import quino.util.Aleatorio;

/**
 *
 * @author admin
 */
public class ConfigEnsayoOrientacionAuto extends ConfigEnsayoOrientacion {

    public ConfigEnsayoOrientacionAuto() {
        super();

        Aleatorio random = new Aleatorio();
        onMove = random.nextInt(0, 15) % 3 == 0 ? false : true;
        ppi = random.nextInt(10, 50);
        this.getConfiguracionXDireccion(random.nextInt(1, 8));

        this.key = onMove ? 32 : 0;
    }
}
