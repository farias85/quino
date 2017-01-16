/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

import quino.util.Aleatorio;
import quino.util.QuinoTools;

/**
 *
 * @author Felipe Rodriguez Arias
 */
public class ConfigEnsayoOrientacionAuto extends ConfigEnsayoOrientacion {

    public ConfigEnsayoOrientacionAuto() {
        super();

        Aleatorio random = new Aleatorio();
        onMove = false;
        ppi = QuinoTools.FRECUENCIA_ESPACIAL[random.nextInt(0, QuinoTools.FRECUENCIA_ESPACIAL.length - 1)];
        ppi = QuinoTools.getPPiXFrecuenciaEspacial(ppi);
        
        this.getConfiguracionXDireccion(random.nextInt(5, 6));

        this.key = onMove ? 32 : 0;
    }
}
