/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

import quino.clases.model.Velocidad;
import quino.util.Aleatorio;

/**
 *
 * @author produccion
 */
public class ConfigEnsayoVelocidadAuto extends ConfigEnsayoVelocidad {

    public ConfigEnsayoVelocidadAuto() {
        super();

        Aleatorio random = new Aleatorio();
        ppi = random.nextInt(10, 50);

        int rnd = random.nextInt(0, 8);
        this.getConfiguracionXDireccion(rnd);

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
        
        int vprimaria = random.nextInt(15, 25);
        int vsecundaria = random.nextInt(1, 3);
        int fmuestreo = random.nextInt(15, 20);
        
        this.velocidadPrimaria = new Velocidad(vprimaria);
        this.velocidadSecundaria = new Velocidad(vsecundaria);
        this.frecuenciaMuestreo = fmuestreo;
        
        this.intensidadMedia = random.nextInt(120, 130);
        this.contrat = (double)(random.nextInt(70, 99)) / 100;

        onMove = true;
    }
}
