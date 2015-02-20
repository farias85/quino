/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayoVelocidad;
import quino.clases.config.ConfigEnsayoVelocidadAuto;
import quino.clases.model.Ensayo;
import quino.util.Aleatorio;

/**
 *
 * @author produccion
 */
public class PruebaVelocidad extends PruebaMultiEnsayo {

    public PruebaVelocidad() {
        super();
    }

    public PruebaVelocidad(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaVelocidad(ConfigEnsayoVelocidad configEnsayo, int cantEnsayos) {
        super(cantEnsayos);
        Aleatorio random = new Aleatorio();
        ConfigEnsayoVelocidad cev = new ConfigEnsayoVelocidad();

        for (int i = 0; i < cantEnsayos; i++) {

            if (configEnsayo instanceof ConfigEnsayoVelocidadAuto) {
                cev = new ConfigEnsayoVelocidadAuto();
            } else if (configEnsayo instanceof ConfigEnsayoVelocidad) {

                int direccion = random.nextInt(0, 8);

                //BORRAR número, dirección
                cev = new ConfigEnsayoVelocidad(1,
                        configEnsayo.getPpi(), true, configEnsayo.getContrat(),
                        configEnsayo.getIntensidadMedia(), configEnsayo.getVelocidadPrimaria(), 
                        configEnsayo.getVelocidadSecundaria(), configEnsayo.getFrecuenciaMuestreo());

            }

            int panelEstimulo = random.nextInt(0, 2);
            cev.setPanelEstimulo(panelEstimulo);

            switch (panelEstimulo) {
                case 0:
                    cev.setKey(0); //ninguna tecla
                    break;
                case 1:
                    cev.setKey(37);//tecla de la flecha izquierda
                    break;
                case 2:
                    cev.setKey(39);//tecla de la flecha derecha
                    break;
            }

            Ensayo ensayo = new Ensayo(cev);
            ensayos.add(ensayo);
        }
    }

    @Override
    public String toString() {
        return "Velocidad";
    }
}
