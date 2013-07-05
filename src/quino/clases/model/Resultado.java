/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

/**
 *
 * @author Felipao
 */
public class Resultado {

    private double velocidadMovimiento;
    private int tiempoRespuesta;
    private int direccion;
    private int densidad;
    private int cantPuntos;
    private boolean error;
    private int numEnsayo;
    private int panelEstimulo;
    private boolean asincronico;
    private String descripcion;
    private int key;
    private boolean control;
    private double velocidad;
    private double angulo;

    public Resultado() {
    }

    public Resultado(double velocidadMovimiento, int tiempoRespuesta, int direccion,
            int densidad, int cantPuntos, boolean error, int numEnsayo, int panelEstimulo,
            boolean asincronico, String descripcion, int key, boolean control,
            double velocidad, double angulo) {

        this.velocidadMovimiento = velocidadMovimiento;
        this.tiempoRespuesta = tiempoRespuesta;
        this.direccion = direccion;
        this.densidad = densidad;
        this.cantPuntos = cantPuntos;
        this.error = error;
        this.numEnsayo = numEnsayo;
        this.panelEstimulo = panelEstimulo;
        this.asincronico = asincronico;
        this.descripcion = descripcion;
        this.key = key;
        this.control = control;
        this.velocidad = velocidad;
        this.angulo = angulo;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public boolean isAsincronico() {
        return asincronico;
    }

    public void setAsincronico(boolean asincronico) {
        this.asincronico = asincronico;
    }

    public int getCantPuntos() {
        return cantPuntos;
    }

    public void setCantPuntos(int cantPuntos) {
        this.cantPuntos = cantPuntos;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
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

    public int getNumEnsayo() {
        return numEnsayo;
    }

    public void setNumEnsayo(int numEnsayo) {
        this.numEnsayo = numEnsayo;
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

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getVelocidadMovimiento() {
        return velocidadMovimiento;
    }

    public void setVelocidadMovimiento(double velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }
}
