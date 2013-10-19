/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import quino.util.test.PruebaEnrejado;
import quino.util.test.PruebaFormaB;
import quino.util.test.PruebaFormaA;
import quino.util.test.PruebaGabor;
import quino.util.test.PruebaOrientacion;
import quino.util.test.PruebaShape;

/**
 * Representa al paciente al cual se le realizan las pruebas
 * @author Felipao
 */
public class Paciente {

    /**
     * Nombre completo del paciente
     */
    private String nombre;
    /**
     * Edad del paciente
     */
    private int edad;
    /**
     * Sexo del paciente
     */
    private String sexo;
    /**
     * Escolaridad del paciente. Se refiere al nivel académico obtenido.
     */
    private String escolaridad;
    /**
     * Identificador de historia clinica.
     */
    private String historia;
    /**
     * Carné de identidad del paciente
     */
    private long ci;
    /**
     * Ficha del paciente, en ella se plasma el seguimiento del paciente
     * o alguna descripción que se quiera apuntar
     */
    private String ficha;
    /**
     * Nombre de la escuela del paciente.
     */
    private String escuela;
    /*
     * Prueba periférica realizada al paciente.
     */
    private PruebaFormaB periferica;
    /**
     * Prueba foveal realizada al paciente
     */
    private PruebaFormaA foveal;
    /**
     * Prueba de detección de forma realizada al paciente
     */
    private PruebaShape forma;
    /**
     * Prueba de campana de gabor realizada al paciente
     */
    private PruebaGabor gabor;
    /**
     * Prueba enrejado realizada al paciente
     */
    private PruebaEnrejado enrejado;

    /**
     * Prueba de tipo detección de Orientación
     */
    private PruebaOrientacion orientacion;

    public Paciente() {
    }

    public Paciente(String nombre, int edad, String sexo, String escolaridad,
            String historia, long ci, String ficha, String escuela, PruebaFormaB periferica,
            PruebaFormaA foveal, PruebaShape forma, PruebaGabor gabor,
            PruebaEnrejado enrejado, PruebaOrientacion orientacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.escolaridad = escolaridad;
        this.historia = historia;
        this.ci = ci;
        this.ficha = ficha;
        this.escuela = escuela;
        this.periferica = periferica;
        this.foveal = foveal;
        this.forma = forma;
        this.gabor = gabor;
        this.enrejado = enrejado;
        this.orientacion = orientacion;
    }

    public Paciente(String nombre, int edad, String sexo, String escolaridad, String historia, long ci, String ficha, String escuela) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.escolaridad = escolaridad;
        this.historia = historia;
        this.ci = ci;
        this.ficha = ficha;
        this.escuela = escuela;
    }

    public long getCi() {
        return ci;
    }

    public void setCi(long ci) {
        this.ci = ci;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public PruebaFormaA getFoveal() {
        return foveal;
    }

    public void setFoveal(PruebaFormaA foveal) {
        this.foveal = foveal;
    }

    public PruebaFormaB getPeriferica() {
        return periferica;
    }

    public void setPeriferica(PruebaFormaB periferica) {
        this.periferica = periferica;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public PruebaEnrejado getEnrejado() {
        return enrejado;
    }

    public void setEnrejado(PruebaEnrejado enrejado) {
        this.enrejado = enrejado;
    }

    public PruebaShape getForma() {
        return forma;
    }

    public void setForma(PruebaShape forma) {
        this.forma = forma;
    }

    public PruebaGabor getGabor() {
        return gabor;
    }

    public void setGabor(PruebaGabor gabor) {
        this.gabor = gabor;
    }

    public PruebaOrientacion getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(PruebaOrientacion orientacion) {
        this.orientacion = orientacion;
    }
}
