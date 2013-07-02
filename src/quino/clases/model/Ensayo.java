/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import quino.util.Aleatorio;
import quino.util.Punto;
import java.awt.Toolkit;
import quino.clases.config.Configuracion;

/**
 *
 * @author davisito
 */
public class Ensayo {

    private Configuracion configuracion;
    private int p_estimulo;
    private int t_respuesta;
    private boolean error;
    private int key;
    private String descripcion;

    public Ensayo(Configuracion configuracion) {
        error = false;
        Aleatorio al = new Aleatorio();
        p_estimulo = al.nextInt(0, 3);
        this.configuracion = configuracion;
        t_respuesta = 0;
        //descripcion = "No se cometieron errores en el Ensayo";
    }

    public boolean isError() {
        return error;
    }

    public int getP_estimulo() {
        return p_estimulo;
    }

    /*public void setP_estimulo1(int p_estimulo) {
        this.p_estimulo = p_estimulo;
    }*/

    public void setError(boolean error) {
        this.error = error;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public int getT_respuesta() {
        return t_respuesta;
    }

    public void setT_respuesta(int t_respuesta) {
        this.t_respuesta = t_respuesta;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @param p1 Punto 1
     * @param p2 Punto 2
     * @return la distancia efectiva
     */
    public double Distancia(Punto p1, Punto p2) {
        double varx = Math.pow((p2.getX() - p1.getX()), 2);
        double vary = Math.pow((p2.getY() - p1.getY()), 2);
        int res = Toolkit.getDefaultToolkit().getScreenResolution();
        double distancia = (Math.sqrt(varx + vary) / res) * 2.5;
        return distancia;
    }

    public double CalcularAngulo(Punto p1, Punto p2) {
        double distancia = Distancia(p1, p2);
        double aRad = Math.atan2(distancia, 60);
        double angulo = Math.toDegrees(aRad);
        return Math.rint(angulo * 100) / 100;
    }
}
