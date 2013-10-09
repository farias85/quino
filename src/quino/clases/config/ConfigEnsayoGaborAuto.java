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
public class ConfigEnsayoGaborAuto extends ConfigEnsayoGabor {

    public ConfigEnsayoGaborAuto() {
        super();

        Aleatorio random = new Aleatorio();
        ppi = random.nextInt(10, 50);
        ppi = 20;
        int dir = random.nextInt(1, 4);
        this.getConfiguracionXDireccion(dir);
        this.key = getKey2Press(direccion);
    }
}
