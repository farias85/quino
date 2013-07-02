/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.model;

import java.io.Serializable;

/**
 *
 * @author Casa
 */
public class Resultado implements Serializable{
     private int velocidad_mov;
    private int t_respuesta;
    private int direccion;
    private int densidad;
    private int cant_puntos;
    private boolean error;
    private int num_ensayo;
    private int p_estimulo;
    private boolean asincronico;
    private String descripcion;
    private int key;
    private boolean control;
    private double velocidad;
    private double angulo;

    public Resultado(int velocidad_mov, int t_respuesta, int direccion, int densidad, int cant_puntos, boolean error, int num_ensayo, boolean asin, int p_estimulo, String descrip, int key, boolean control, double velocidad, double angulo) {
        this.velocidad_mov = velocidad_mov;
        this.t_respuesta = t_respuesta;
        this.direccion = direccion;
        this.densidad = densidad;
        this.cant_puntos = cant_puntos;
        this.error = error;
        this.num_ensayo = num_ensayo;
        this.asincronico = asin;
        this.p_estimulo = p_estimulo;
        this.descripcion = descrip;
        this.key = key;
        this.control = control;
        this.velocidad = velocidad;
        this.angulo = angulo;
    }

    public int getCant_puntos() {
        return cant_puntos;
    }

    public int getDensidad() {
        return densidad;
    }

    public int getDireccion() {
        return direccion;
    }

    public boolean isError() {
        return error;
    }

    public int getNum_ensayo() {
        return num_ensayo;
    }

    public int getT_respuesta() {
        return t_respuesta;
    }

    public int getVelocidad_mov() {
        return velocidad_mov;
    }

    public int getP_estimulo() {
        return p_estimulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getKey() {
        return key;
    }

    public boolean isControl() {
        return control;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public double getAngulo() {
        return angulo;
    }


}
