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
public class ConfigEnsayoFormaABAuto extends ConfigEnsayoFormaAB {

    public ConfigEnsayoFormaABAuto(double tiempoMovimiento, int densidad, int cantidad,
            int direccion, boolean asincronico, boolean control, int panelEstimulo) {
        super(tiempoMovimiento, densidad, cantidad, direccion, asincronico, control, panelEstimulo);
    }

    public ConfigEnsayoFormaABAuto(double tiempoMovimiento, int densidad, int cantidad,
            int direccion, boolean asincronico, boolean control, int key, int panelEstimulo)
            throws Exception {
        super(tiempoMovimiento, densidad, cantidad, direccion, asincronico,
                control, key, panelEstimulo);
    }

    public ConfigEnsayoFormaABAuto() {
        super();
    }

    public ConfigEnsayoFormaABAuto(boolean control, int panelEstimulo) {
        super(0, 0, 0, 0, false, control, 0);

        Aleatorio random = new Aleatorio();
        densidad = random.nextInt(250, 1500);

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

        this.panelEstimulo = panelEstimulo;
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
