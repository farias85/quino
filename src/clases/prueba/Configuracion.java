/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.prueba;

import java.awt.Toolkit;

/**
 *
 * @author davisito
 */
public class Configuracion {
    protected int tiempo_movimiento;
    protected int densidad;
    protected int cantidad;
    protected int direccion;    
    protected boolean asincronico;
    protected int resto;
    protected boolean control;
    protected double velocidad;

    public int getResto() {
        return resto;
    }

    public void setResto(int resto) {
        this.resto = resto;
    }

    public Configuracion(int tiempo_movimiento, int densidad, int cantidad, int direccion, boolean asin, boolean control) throws Exception{
       if(tiempo_movimiento<50 || tiempo_movimiento>200){
           throw new Exception("El tiempo de movimiento debe ser mayor que 50 y menor que 200");
       }
       else
           this.tiempo_movimiento = tiempo_movimiento;
        this.densidad = densidad;
        this.cantidad = cantidad;
        this.direccion = direccion;        
        this.asincronico = asin;
        this.control=control;       
    }

    public Configuracion() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getDensidad() {
        return densidad;
    }

    public int getDireccion() {
        return direccion;
    }

    public int getTiempo_movimiento() {
        return tiempo_movimiento;
    }

    public boolean isAsincronico() {
        return asincronico;
    }

    public void setAsincronico(boolean asincronico) {
        this.asincronico = asincronico;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    public boolean isControl() {
        return control;
    }
    public double CalcularVelocidad(){
        double distancia =(4.00/Toolkit.getDefaultToolkit().getScreenResolution())*2.5;
        this.velocidad = distancia/tiempo_movimiento;
        this.velocidad = Math.rint(velocidad*1000)/1000;
        return velocidad;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
  }


