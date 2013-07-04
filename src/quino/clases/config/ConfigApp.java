/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

/**
 *
 * @author farias
 */
public class ConfigApp {

    public static int CANT_ENSAYOS = 3;
    public static double TIEMPO_DURACION = 6000;

    public static double PC_EN_ESPERA = 30;
    public static double PC_PREPARADO = 40;
    public static double PC_ESPERANDO_RESPUESTA = 30;

    private int cantEnsayos;
    private double tiempoDuracion;
    private double pcEnEspera;
    private double pcPreparado;
    private double pcEsperandoRespuesta;

    public ConfigApp() {
    }

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
