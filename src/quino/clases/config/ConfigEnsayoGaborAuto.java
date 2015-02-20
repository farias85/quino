/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import quino.clases.model.Velocidad;
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
        ppi2 = random.nextInt(10, 50);
        int dir = random.nextInt(1, 4);
        this.getConfiguracionXDireccion(dir);
        this.key = getKey2Press(direccion);
        
        int vprimaria = random.nextInt(15, 25);
        int fmuestreo = random.nextInt(1, 20);
        
        this.velocidadPrimaria = new Velocidad(vprimaria);
        this.frecuenciaMuestreo = fmuestreo;
        
        this.radio1 = random.nextInt(50, 80);
        this.radio2 = random.nextInt(150, 180);
    }
}
