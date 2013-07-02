/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.model;

/**
 *
 * @author Felipao
 */
public class Paciente {
    private String nombre;
    private int edad;
    private String sexo;
    private String escolaridad;
    private String historia;
    private long  ci;
    private String ficha;
    private Prueba periferica;
    private Prueba foveal;

    public Paciente() {
    }

    public Paciente(String nombre, int edad, String sexo, String escolaridad, String historia, long ci, 
            String ficha, Prueba periferica, Prueba foveal) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.escolaridad = escolaridad;
        this.historia = historia;
        this.ci = ci;
        this.ficha = ficha;
        this.periferica = periferica;
        this.foveal = foveal;
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

    public Prueba getFoveal() {
        return foveal;
    }

    public void setFoveal(Prueba foveal) {
        this.foveal = foveal;
    }

    public Prueba getPeriferica() {
        return periferica;
    }

    public void setPeriferica(Prueba periferica) {
        this.periferica = periferica;
    }
}
