/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import java.text.DecimalFormat;

/**
 * Clase para la configuración del sistema. La clase permite su utilización de
 * forma estática, no es necesario construir objetos de esta clase para su
 * utilización.
 * @author Felipe Rodriguez Arias
 */
public class ConfigApp {

    /**
     * Directorio de las miscelaneas del proyecto, íconos, imagenes, etc...
     */
    public static final String RESOURCES_LOCATION = "/quino/view/main/icons/";
    
    /**
     * Directorio de el archivo de base de datos interno del sistema
     */
    public static final String REGISTRO_FILE_NAME = "quino.config/registro.xml";

    /**
     * Directorio de el archivo de configuración del sistema
     */
    public static final String CONFIG_FILE_NAME = "quino.config/configuracion.xml";

    /**
     * Para darle formato a los tipos de datos double... No se está usando...
     */
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,##");

    /**
     * Cantaidad de ensayos de prueba para el entrenamiento, su valor puede
     * cambiar si al cargar la configuración existe otro nuevo valor
     */
    public static int CANT_ENSAYOS = 1;

    /**
     * Tiempo de duración general de un ensayo en una prueba
     */
    public static double TIEMPO_DURACION = 6000;

    /**
     * Porciento de duración para esta etapa respecto al tiempo
     * de duración general TIEMPO_DURACION, en la configuración del ensayo.
     * Es el tiempo de espera antes de ejecutar un ensayo
     */
    public static double PC_EN_ESPERA = 30;

    /**
     * Porciento de duración para esta etapa respecto al tiempo
     * de duración general TIEMPO_DURACION, en la configuración del ensayo.
     * Representa el primer lapso de luego de el tiempo de espera, y antes de
     * ejecutarse o no el movimiento
     */
    public static double PC_PREPARADO = 40;

    /**
     * Porciento de duración para esta etapa respecto al tiempo
     * de duración general TIEMPO_DURACION, en la configuración del ensayo.
     * Representa el tiempo de espera luego de ejecutado el movimiento
     */
    public static double PC_ESPERANDO_RESPUESTA = 30;

    /**
     * Cantaidad de ensayos de prueba para el entrenamiento, su valor puede
     * cambiar si al cargar la configuración existe otro nuevo valor
     */
    private int cantEnsayos;

    /**
     * Tiempo de duración general de un ensayo en una prueba
     */
    private double tiempoDuracion;

    /**
     * Porciento de duración para esta etapa respecto al tiempo
     * de duración general TIEMPO_DURACION, en la configuración del ensayo.
     * Es el tiempo de espera antes de ejecutar un ensayo
     */
    private double pcEnEspera;

    /**
     * Porciento de duración para esta etapa respecto al tiempo
     * de duración general TIEMPO_DURACION, en la configuración del ensayo.
     * Representa el primer lapso de luego de el tiempo de espera, y antes de
     * ejecutarse o no el movimiento
     */
    private double pcPreparado;

    /**
     * Porciento de duración para esta etapa respecto al tiempo
     * de duración general TIEMPO_DURACION, en la configuración del ensayo.
     * Representa el tiempo de espera luego de ejecutado el movimiento
     */
    private double pcEsperandoRespuesta;

    public ConfigApp() {
    }

    /**
     * Este constructor solo debe ser invocado para obtener el objeto q se
     * guardará en el archivo configuración.xml. Utilizar siempre los valores
     * estáticos
     * @param cantEnsayos
     * @param tiempoDuracion
     * @param pcEnEspera
     * @param pcPreparado
     * @param pcEsperandoRespuesta
     */
    public ConfigApp(int cantEnsayos, double tiempoDuracion, double pcEnEspera, double pcPreparado, double pcEsperandoRespuesta) {
        this.cantEnsayos = cantEnsayos;
        this.tiempoDuracion = tiempoDuracion;
        this.pcEnEspera = pcEnEspera;
        this.pcPreparado = pcPreparado;
        this.pcEsperandoRespuesta = pcEsperandoRespuesta;

        CANT_ENSAYOS = cantEnsayos;
        TIEMPO_DURACION = tiempoDuracion;
        PC_EN_ESPERA = pcEnEspera;
        PC_PREPARADO = pcPreparado;
        PC_ESPERANDO_RESPUESTA = pcEsperandoRespuesta;
    }

    public int getCantEnsayos() {
        return cantEnsayos;
    }

    public void setCantEnsayos(int cantEnsayos) {
        this.cantEnsayos = cantEnsayos;
    }

    public double getPcEnEspera() {
        return pcEnEspera;
    }

    public void setPcEnEspera(double pcEnEspera) {
        this.pcEnEspera = pcEnEspera;
    }

    public double getPcEsperandoRespuesta() {
        return pcEsperandoRespuesta;
    }

    public void setPcEsperandoRespuesta(double pcEsperandoRespuesta) {
        this.pcEsperandoRespuesta = pcEsperandoRespuesta;
    }

    public double getPcPreparado() {
        return pcPreparado;
    }

    public void setPcPreparado(double pcPreparado) {
        this.pcPreparado = pcPreparado;
    }

    public double getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(double tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }
}
