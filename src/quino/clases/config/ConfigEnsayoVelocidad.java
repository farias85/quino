/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

import quino.clases.model.Velocidad;

/**
 *
 * @author produccion
 */
public class ConfigEnsayoVelocidad extends ConfigEnsayoEnrejado {
    
    private Velocidad velocidadPrimaria = new Velocidad(24);
    private Velocidad velocidadSecundaria = new Velocidad(1);
    private double frecuenciaMuestreo = 8;
    
    public ConfigEnsayoVelocidad(int direccion, double ppi, boolean onMove,
            double contrat, double intensidadMedia, Velocidad primaria, 
            Velocidad secundaria, double frecuenciaMuestro) {
        super(direccion, ppi, onMove, contrat, intensidadMedia);
        
        this.velocidadPrimaria = primaria;
        this.velocidadSecundaria = secundaria;
        this.frecuenciaMuestreo = frecuenciaMuestro;
    }

    public ConfigEnsayoVelocidad() {
        super();
    }
    
    public Velocidad getVelocidadPrimaria() {
        return velocidadPrimaria;
    }

    public void setVelocidadPrimaria(Velocidad velocidadPrimaria) {
        this.velocidadPrimaria = velocidadPrimaria;
    }

    public Velocidad getVelocidadSecundaria() {
        return velocidadSecundaria;
    }

    public void setVelocidadSecundaria(Velocidad velocidadSecundaria) {
        this.velocidadSecundaria = velocidadSecundaria;
    }
    
    public double getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }

    public void setFrecuenciaMuestreo(double frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }

}
