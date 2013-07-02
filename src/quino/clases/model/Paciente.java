/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.model;

import java.io.Serializable;

/**
 *
 * @author Davisito
 */
public class Paciente implements Serializable{
    private String nombre;
    private int edad;
    private String sexo;
    private String escolaridad;
    private String no_historia;
    private long  CI;
    private String ficha;
    private Prueba prueba;
    private Prueba fobeal;

    public Paciente(String nombre, int edad, String sexo, String escolaridad, String no_historia, long  CI, String ficha) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.escolaridad = escolaridad;
        this.no_historia = no_historia;
        this.CI = CI;
        this.ficha = ficha;
        prueba= null;
        fobeal=null;
    }
    public Paciente()
    {}
    

    public long  getCI() {
        return CI;
    }

    public int getEdad() {
        return edad;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public String getFicha() {
        return ficha;
    }

    public String getNo_historia() {
        return no_historia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public Prueba getPrueba() throws Exception{
        if(prueba==null)
            throw  new Exception("A este paciente no se le ha realizado este tipo de prueba");
        return prueba;
    }
    public Prueba Prueba(){
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public Prueba getFobeal() throws Exception {
        if(fobeal==null)
            throw  new Exception("A este paciente no se le ha realizdo este tipo de prueba");
        return fobeal;
    }

    public void setFobeal(Prueba fobeal) {
        this.fobeal = fobeal;
    }


    public void setCI(long CI) {
        this.CI = CI;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public void setNo_historia(String no_historia) {
        this.no_historia = no_historia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
