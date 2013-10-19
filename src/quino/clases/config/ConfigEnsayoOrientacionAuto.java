/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

import quino.util.Aleatorio;

/**
 *
 * @author Felipe Rodriguez Arias
 */
public class ConfigEnsayoOrientacionAuto extends ConfigEnsayoOrientacion {

    public ConfigEnsayoOrientacionAuto() {
        super();

        Aleatorio random = new Aleatorio();
        onMove = false;
        ppi = random.nextInt(10, 50);
        this.getConfiguracionXDireccion(random.nextInt(5, 6));

        this.key = onMove ? 32 : 0;
    }
}
