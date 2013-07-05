/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

/**
 *
 * @author Felipao
 */
public class ConfigPrueba {

    protected double tiempoMovimiento;
    protected int densidad;
    protected int cantidad;
    protected int direccion;
    protected boolean asincronico;
    protected boolean control;

    public ConfigPrueba(double tiempoMovimiento, int densidad, int cantidad, int direccion, boolean asin, boolean control) throws Exception {
        if (tiempoMovimiento < 50 || tiempoMovimiento > 200) {
            throw new Exception("El tiempo de movimiento debe ser mayor que 50 y menor que 200");
        } else {
            this.tiempoMovimiento = tiempoMovimiento;
        }
        this.densidad = densidad;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.asincronico = asin;
        this.control = control;
    }

    public ConfigPrueba() {
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
