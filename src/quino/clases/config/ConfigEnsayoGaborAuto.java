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
public class ConfigEnsayoGaborAuto extends ConfigEnsayoGabor {

    public ConfigEnsayoGaborAuto() {
        super();

        Aleatorio random = new Aleatorio();
        ppi = QuinoTools.FRECUENCIA_ESPACIAL[random.nextInt(0, QuinoTools.FRECUENCIA_ESPACIAL.length - 1)];
        //ppi = QuinoTools.getPPiXFrecuenciaEspacial(ppi);
        
        ppi2 = QuinoTools.FRECUENCIA_ESPACIAL[random.nextInt(0, QuinoTools.FRECUENCIA_ESPACIAL.length - 1)];
        //ppi2 = QuinoTools.getPPiXFrecuenciaEspacial(ppi2);
        
        int dir = random.nextInt(1, 4);
        this.getConfiguracionXDireccion(dir);
        this.key = getKey2Press(direccion);
        
        float vprimaria = QuinoTools.VELOCIDAD[random.nextInt(0, QuinoTools.VELOCIDAD.length - 1)];
        
        this.velocidadPrimaria = new Velocidad(vprimaria);
        this.frecuenciaMuestreo = QuinoTools.FRECUENCIA_TEMPORAL[random.nextInt(0, QuinoTools.FRECUENCIA_TEMPORAL.length - 1)];
        
        this.radio1 = random.nextInt(50, 80);
        this.radio2 = random.nextInt(150, 180);
    }
}
