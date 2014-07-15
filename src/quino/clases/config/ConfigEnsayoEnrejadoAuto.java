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
public class ConfigEnsayoEnrejadoAuto extends ConfigEnsayoEnrejado {

    public ConfigEnsayoEnrejadoAuto() {
        super();

        Aleatorio random = new Aleatorio();
        onMove = random.nextInt(0, 15) % 3 == 0 ? false : true;
        ppi = random.nextInt(2, 50);
        this.getConfiguracionXDireccion(random.nextInt(1, 8));

        this.key = onMove ? 32 : 0;
    }
}
