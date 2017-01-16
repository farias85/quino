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
 * @author produccion
 */
public class ConfigEnsayoVelocidadAuto extends ConfigEnsayoVelocidad {

    public ConfigEnsayoVelocidadAuto() {
        super();

        Aleatorio random = new Aleatorio();
        ppi = QuinoTools.FRECUENCIA_ESPACIAL[random.nextInt(0, QuinoTools.FRECUENCIA_ESPACIAL.length - 1)];
        ppi = QuinoTools.getPPiXFrecuenciaEspacial(ppi);

        int rnd = random.nextInt(0, 8);
        this.getConfiguracionXDireccion(rnd);

        float vprimaria = QuinoTools.VELOCIDAD[random.nextInt(0, QuinoTools.VELOCIDAD.length - 1)];
        float vsecundaria = QuinoTools.VELOCIDAD[random.nextInt(0, QuinoTools.VELOCIDAD.length - 1)];

        panelEstimulo = 0;
        if (vprimaria != vsecundaria) {
            panelEstimulo = random.nextInt(1, 2);
        }

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

        this.velocidadPrimaria = new Velocidad(vprimaria);
        this.velocidadSecundaria = new Velocidad(vsecundaria);
        this.frecuenciaMuestreo = QuinoTools.FRECUENCIA_TEMPORAL[random.nextInt(0, QuinoTools.FRECUENCIA_TEMPORAL.length - 1)];

        this.intensidadMedia = random.nextInt(120, 130);
        this.contrat = (double) (random.nextInt(70, 99)) / 100;

        onMove = true;
    }
}
