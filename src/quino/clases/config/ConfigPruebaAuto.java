/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import quino.util.Aleatorio;

/**
 *
 * @author davisito
 */
public class ConfigPruebaAuto extends ConfigPrueba {

    public ConfigPruebaAuto(boolean control) {
        Aleatorio r = new Aleatorio();
        Aleatorio ran = new Aleatorio();
        int x = ran.nextInt(1, 19);
        this.densidad = x * 100;
        this.direccion = ran.nextInt(0, 8);
        int a = r.nextInt(5, 80);
        this.cantidad = (a * densidad) / 100;
        /* if(fob){
        this.cantidad = (a*densidad/8)/100;
        }*/
        this.control = control;

        if (this.direccion == 0 & !control) {
            this.asincronico = true;
            //this.direccion=0;
        } else if (control) {
            this.asincronico = false;
            this.direccion = ran.nextInt(1, 8);
        }
        tiempoMovimiento = ran.nextInt(50, 200);
    }

    public void Re_Cantidad() {
        Aleatorio r = new Aleatorio();
        int a = r.nextInt(5, 80);
        this.cantidad = (a * densidad) / 100;
    }
}
