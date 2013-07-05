/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import quino.util.Aleatorio;
import quino.clases.config.ConfigPrueba;

/**
 *
 * @author Felipao
 */
public class Ensayo {

    private ConfigPrueba configuracion;
    private int panelEstimulo;
    private int tiempoRespuesta;
    private boolean error;
    private int key;
    private String descripcion;

    public Ensayo() {
    }

    public Ensayo(ConfigPrueba configuracion) {
        error = false;
        Aleatorio al = new Aleatorio();
        panelEstimulo = al.nextInt(0, 3);
        this.configuracion = configuracion;
        tiempoRespuesta = 0;
    }

    public Ensayo(ConfigPrueba configuracion, int panelEstimulo, int tiempoRespuesta, boolean error, int key, String descripcion) {
        this.configuracion = configuracion;
        this.panelEstimulo = panelEstimulo;
        this.tiempoRespuesta = tiempoRespuesta;
        this.error = error;
        this.key = key;
        this.descripcion = descripcion;
    }

    public ConfigPrueba getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfigPrueba configuracion) {
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
