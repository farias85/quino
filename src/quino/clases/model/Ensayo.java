/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import quino.clases.config.ConfigEnsayo;

/**
 * Representa cada ensayo dentro de una prueba.
 * @author Felipe Rodriguez Arias
 */
public class Ensayo {

    /**
     * Configuracion de la prueba a la q pertenece
     */
    private ConfigEnsayo configuracion;
    /**
     * El resultado del ensayo
     */
    private Resultado resultado;

    public Ensayo() {
    }

    public Ensayo(ConfigEnsayo configuracion) {
        this.configuracion = configuracion;
        resultado = new Resultado();
    }

    public Ensayo(ConfigEnsayo configuracion, Resultado resultado) {
        this.configuracion = configuracion;
        this.resultado = resultado;
    }

    public ConfigEnsayo getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfigEnsayo configuracion) {
        this.configuracion = configuracion;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}
