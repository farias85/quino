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
 * @author Felipao
 */
public class Ensayo {

    private Configuracion configuracion;
    private int panelEstimulo;
    private int tiempoRespuesta;
    private boolean error;
    private int key;
    private String descripcion;

    public Ensayo() {
    }

    public Ensayo(Configuracion configuracion) {
        error = false;
        Aleatorio al = new Aleatorio();
        panelEstimulo = al.nextInt(0, 3);
        this.configuracion = configuracion;
        tiempoRespuesta = 0;
    }

    public Ensayo(Configuracion configuracion, int panelEstimulo, int tiempoRespuesta, boolean error, int key, String descripcion) {
        this.configuracion = configuracion;
        this.panelEstimulo = panelEstimulo;
        this.tiempoRespuesta = tiempoRespuesta;
        this.error = error;
        this.key = key;
        this.descripcion = descripcion;
    }

    public double getDistancia(Punto p1, Punto p2) {
        double varx = Math.pow((p2.getX() - p1.getX()), 2);
        double vary = Math.pow((p2.getY() - p1.getY()), 2);
        int res = Toolkit.getDefaultToolkit().getScreenResolution();
        double distancia = (Math.sqrt(varx + vary) / res) * 2.5;
        return distancia;
    }

    public double getAngulo(Punto p1, Punto p2) {
        double distancia = getDistancia(p1, p2);
        double aRad = Math.atan2(distancia, 60);
        double angulo = Math.toDegrees(aRad);
        return Math.rint(angulo * 100) / 100;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getPanelEstimulo() {
        return panelEstimulo;
    }

    public void setPanelEstimulo(int panelEstimulo) {
        this.panelEstimulo = panelEstimulo;
    }

    public int getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(int tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }
}
