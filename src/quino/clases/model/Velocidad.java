/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

/**
 *
 * @author farias
 */
public class Velocidad {

    double count = 0;
    double aceleracion = 0;

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public Velocidad(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public void go() {
        count += aceleracion;
    }

    public double getCount() {
        return count;
    }
}
