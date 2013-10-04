/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;


import java.util.Date;
import quino.clases.model.Ensayo;

/**
 * Representa una prueba a efectuar. Es una clase abstracta pq cada vez q se
 * crea una prueba es de tipo Foveal o Periferica. Se hace para quitarse el
 * atributo booleano isFoveal y obligar al desarrollador a determinar mediante
 * un tipo especifico, la prueba q quiere realizar
 * @author Felipe Rodriguez Arias
 */
public abstract class Prueba {

    /**
     * Fecha de en que se ejecutó la prueba
     */
    protected Date fecha;
    /**
     * Lista de los ensayos de la prueba y su configuracion
     */
    protected ArrayList<Ensayo> ensayos;

    /**
     * En este contructor se crea la prueba y ademas la configuración para cada
     * ensayo. Si la configuracion es automatica se establece una configuracion
     * diferente en cada prueba, si la configuración es estandar todos los
     * ensayos tienen la misma configuración
     * @param cantEnsayos Cantidad de ensayos de la prueba
     * @param configEnsayo Configuración del ensayo
     */
    public Prueba() {
        fecha = new Date();
        ensayos = new ArrayList<Ensayo>();
    }

    public Prueba(Date fecha, ArrayList<Ensayo> ensayos) {
        this.fecha = fecha;
        this.ensayos = ensayos;
    }

    /**
     * Devuelve la cantidad de errores de la prueba
     * @return Cantidad de errores en esta prueba
     */
    public int cantErrores() {
        int count = 0;
        for (int i = 0; i < ensayos.size(); i++) {
            if (ensayos.get(i).getResultado().isError()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Devuelve el tiempo de respuesta promedio de los ensayos de esta prueba
     * @return Tiempo de respuesta promedio
     */
    public int tiempoRespuestaPromedio() {
        int sum = 0;
        int prom = 0;
        int cont = 0;
        for (int i = 0; i < ensayos.size(); i++) {
            if (ensayos.get(i).getResultado().getTiempoRespuesta() != 0) {
                sum = sum + ensayos.get(i).getResultado().getTiempoRespuesta();
                cont++;
            }
        }
        if (cont != 0) {
            prom = sum / cont;
        }
        return prom;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Ensayo> getEnsayos() {
        return ensayos;
    }

    public void setEnsayos(ArrayList<Ensayo> ensayos) {
        this.ensayos = ensayos;
    }
}
