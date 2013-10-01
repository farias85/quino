/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import java.util.ArrayList;


import java.util.Date;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoAuto;
import quino.util.Aleatorio;

/**
 * Representa una prueba a efectuar. Es una clase abstracta pq cada vez q se
 * crea una prueba es de tipo Foveal o Periferica. Se hace para quitarse el
 * atributo booleano isFoveal y obligar al desarrollador a determinar mediante
 * un tipo especifico, la prueba q quiere realizar
 * @author Felipe Rodriguez Arias
 */
public abstract class Prueba {

    /**
     * Cantidad de ensayos de la prueba a ejcutarse
     */
    private int cantEnsayos;
    /**
     * Fecha de en que se ejecutó la prueba
     */
    private Date fecha;
    /**
     * Lista de los ensayos de la prueba y su configuracion
     */
    private ArrayList<Ensayo> ensayos;

    public Prueba() {
    }

    /**
     * En este contructor se crea la prueba y ademas la configuración para cada
     * ensayo. Si la configuracion es automatica se establece una configuracion
     * diferente en cada prueba, si la configuración es estandar todos los
     * ensayos tienen la misma configuración
     * @param cantEnsayos Cantidad de ensayos de la prueba
     * @param configEnsayo Configuración del ensayo
     */
    public Prueba(int cantEnsayos, ConfigEnsayo configEnsayo) {
        this.cantEnsayos = cantEnsayos;
        fecha = new Date();
        ensayos = new ArrayList<Ensayo>();

        for (int i = 0; i < cantEnsayos; i++) {
            if (configEnsayo instanceof ConfigEnsayoAuto) {
                configEnsayo = new ConfigEnsayoAuto(configEnsayo.isControl());
            }
            int panelEstimulo = -1;
            Aleatorio random = new Aleatorio();
            if (this instanceof PruebaFoveal) {
                panelEstimulo = random.nextInt(0, 8);
            }
            if (this instanceof PruebaPeriferica) {
                panelEstimulo = random.nextInt(0, 2);
            }

            if (this instanceof PruebaShape) {
                panelEstimulo = random.nextInt(0, 2);
                switch (panelEstimulo) {
                    case 0:
                        configEnsayo.setKey(0); //ninguna tecla
                        break;
                    case 1:
                        configEnsayo.setKey(37);//tecla de la flecha izquierda
                        break;
                    case 2:
                        configEnsayo.setKey(39);//tecla de la flecha derecha
                        break;
                }
            }

            Ensayo ensayo = new Ensayo(configEnsayo, panelEstimulo);

            ensayos.add(ensayo);
        }
    }

    public Prueba(int cantEnsayos, Date fecha, ArrayList<Ensayo> ensayos) {
        this.cantEnsayos = cantEnsayos;
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
     * Devuelve el promedio de la densidad de esta prueba
     * @return Promedio de la densidad de cada ensayo
     */
    public int densidadPromedio() {
        int sum = 0;
        int prom = 0;
        for (int i = 0; i < ensayos.size(); i++) {
            sum = sum + ensayos.get(i).getConfiguracion().getDensidad();
        }
        prom = sum / ensayos.size();
        return prom;
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

    public int getCantEnsayos() {
        return cantEnsayos;
    }

    public void setCantEnsayos(int cantEnsayos) {
        this.cantEnsayos = cantEnsayos;
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
