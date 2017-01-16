/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

/**
 *
 * @author farias
 */
public class ConfigEnsayoEnrejado extends ConfigEnsayoSinusoide {

    /**
     * True cuando la matrix enrejada se va a mover, falso en caso contrario
     */
    protected boolean onMove = false;

    public ConfigEnsayoEnrejado() {
        super();
    }

    //No se puse la velocidad ni la frecuencia de muestreo en el constructor
    //xq la prueba de Orientacion hereda de esta prueba y ahi no hay movimiento
    //los valores hay que setearlos luego del constructor
    public ConfigEnsayoEnrejado(int direccion, double ppi, boolean onMove, 
            double contrat, double intensidadMedia) {
        super(direccion, ppi, contrat, intensidadMedia);
        
        this.onMove = onMove;
        this.key = onMove ? 32 : 0;
    }

    public boolean isOnMove() {
        return onMove;
    }

    public void setOnMove(boolean onMove) {
        this.onMove = onMove;
    }
}
