/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import quino.util.Aleatorio;
import quino.util.QuinoTools;

/**
 * Representa la configuración automática de una prueba.
 * Los valores de inicio de prueba se diseñan automaticamente.
 * @author Felipe Rodriguez Arias
 */
public class ConfigEnsayoAuto extends ConfigEnsayo {

    public ConfigEnsayoAuto() {
        super();
    }

    public ConfigEnsayoAuto(double tiempoMovimiento, int densidad, int cantidad,
            int direccion, boolean asincronico, boolean control) {
        super(tiempoMovimiento, densidad, cantidad, direccion, asincronico, control);
    }

    public ConfigEnsayoAuto(double tiempoMovimiento, int densidad, int cantidad,
            int direccion, boolean asincronico, boolean control, int key)
            throws Exception {
        super(tiempoMovimiento, densidad, cantidad, direccion, asincronico,
                control, key);
    }

    public ConfigEnsayoAuto(boolean control) {

        Aleatorio random = new Aleatorio();
        densidad = random.nextInt(50, 1500);
        direccion = random.nextInt(0, 8);

        int porciento = random.nextInt(5, 80);
        cantidad = porciento * densidad / 100;
        this.control = control;

        if (this.direccion == 0 & !control) {
            this.asincronico = true;
        } else {
            this.asincronico = false;
            this.direccion = random.nextInt(1, 8);
        }

        tiempoMovimiento = random.nextInt(50, 200);
        this.key = control ? QuinoTools.getKeyDireccion(direccion) : 32;
    }

    /**
     * Establece una nueva cantidad de puntos a mover en una prueba automática
     */
    public void reEstablecerCantidad() {
        Aleatorio r = new Aleatorio();
        int a = r.nextInt(5, 80);
        this.cantidad = (a * densidad) / 100;
    }
}
