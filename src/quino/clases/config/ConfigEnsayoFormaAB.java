/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import quino.util.QuinoTools;

/**
 * Representa la configuración de una prueba
 * @author Felipe Rodriguez Arias
 */
public class ConfigEnsayoFormaAB extends ConfigEnsayo {

    /**
     * Tiempo de movimiento de los puntos, el valor debe estar entre
     * 50 y 200
     */
    protected double tiempoMovimiento;
    /**
     * Densidad de puntos a representar en la prueba
     */
    protected int densidad;
    /**
     * Cantidad de puntos a mover respecto a la densidad
     */
    protected int cantidad;
    /**
     * Dirección del movimiento
     */
    protected int direccion;
    /**
     * Verdadero si el movimiento a ejecutarse es asincrónico
     */
    protected boolean asincronico;
    /**
     * Verdadero si se ejercerá control en el movimiento.
     */
    protected boolean control;

    public ConfigEnsayoFormaAB() {
        super();
    }

    public ConfigEnsayoFormaAB(double tiempoMovimiento, int densidad, int cantidad,
            int direccion, boolean asincronico, boolean control) {
        super(control ? QuinoTools.getKeyDireccion(direccion) : 32);
        
        this.tiempoMovimiento = tiempoMovimiento;
        this.densidad = densidad;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.asincronico = control ? false : asincronico;
        this.control = control;

        //32 es el ascci de la barra espaciadora
        //this.key == control ? QuinoTools.getKeyDireccion(direccion) : 32;
    }

    /**
     * Constrctor para crear una configuracion de prueba
     * El tiempo de movimiento de los puntos, el valor debe estar entre
     * 50 y 200
     * @param tiempoMovimiento el valor debe estar entre 50 y 200
     * @param densidad
     * @param cantidad
     * @param direccion
     * @param asincronico
     * @param control
     * @throws Exception
     */
    public ConfigEnsayoFormaAB(double tiempoMovimiento, int densidad, int cantidad,
            int direccion, boolean asincronico, boolean control, int key)
            throws Exception {
        super(key);

        if (tiempoMovimiento < 50 || tiempoMovimiento > 200) {
            throw new Exception("El tiempo de movimiento debe ser mayor que 50 y menor que 200");
        } else {
            this.tiempoMovimiento = tiempoMovimiento;
        }
        this.densidad = densidad;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.asincronico = asincronico;
        this.control = control;
        this.key = key;
    }

    public boolean isAsincronico() {
        return asincronico;
    }

    public void setAsincronico(boolean asincronico) {
        this.asincronico = asincronico;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public double getTiempoMovimiento() {
        return tiempoMovimiento;
    }

    public void setTiempoMovimiento(double tiempoMovimiento) {
        this.tiempoMovimiento = tiempoMovimiento;
    }
}
