/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

/**
 * Representa el resultado final luego de la ejecución de un ensayo
 * @author Felipe Rodriguez Arias
 */
public class Resultado {

    /**
     * Tiempo de respuesta a la acción del movimiento
     */
    private int tiempoRespuesta;
    /**
     * True si el resultado del ensayo es un error
     */
    private boolean error;
    /**
     * La descripción del resultado o del error
     */
    private String descripcion = "";
    /**
     * La tecla presionada luego del ensayo
     */
    private int key;
    /**
     * Velocidad del movimiento de los puntos
     */
    private double velocidad;
    /**
     * El angulo promedio de movimiento de los puntos
     */
    private double angulo;

    public Resultado() {
    }

    public Resultado(int tiempoRespuesta, boolean error, String descripcion,
            int key, double velocidad, double angulo) {
        this.tiempoRespuesta = tiempoRespuesta;
        this.error = error;
        this.descripcion = descripcion;
        this.key = key;
        this.velocidad = velocidad;
        this.angulo = angulo;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
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

    public int getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(int tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }
}
