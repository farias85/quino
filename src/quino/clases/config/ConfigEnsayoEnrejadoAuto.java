/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import quino.clases.model.Velocidad;
import quino.util.Aleatorio;
import quino.util.QuinoTools;

/**
 *
 * @author farias
 */
public class ConfigEnsayoEnrejadoAuto extends ConfigEnsayoEnrejado {

    public ConfigEnsayoEnrejadoAuto() {
        super();

        Aleatorio random = new Aleatorio();
        onMove = random.nextInt(0, 15) % 3 != 0;
        
        ppi = QuinoTools.FRECUENCIA_ESPACIAL[random.nextInt(0, QuinoTools.FRECUENCIA_ESPACIAL.length - 1)];
        ppi = QuinoTools.getPPiXFrecuenciaEspacial(ppi);
        
        this.getConfiguracionXDireccion(random.nextInt(1, 8));

        this.key = onMove ? 32 : 0;
        float vprimaria = QuinoTools.VELOCIDAD[random.nextInt(0, QuinoTools.VELOCIDAD.length - 1)];
        this.velocidadPrimaria = new Velocidad(vprimaria);
        this.frecuenciaMuestreo = QuinoTools.FRECUENCIA_TEMPORAL[random.nextInt(0, QuinoTools.FRECUENCIA_TEMPORAL.length - 1)];
    }
}
