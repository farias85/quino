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

    public ConfigEnsayoEnrejado(int direccion, double ppi, boolean onMove) {
        super(direccion, ppi);
        this.onMove = onMove;
        this.key = onMove ? 32 : 0;
    }

    public ConfigEnsayoEnrejado(int direccion, double ppi, boolean onMove, double contrat, double intensidadMedia) {
        super(direccion, ppi, contrat, intensidadMedia);
        this.onMove = onMove;
        this.key = onMove ? 32 : 0;
    }

    public ConfigEnsayoEnrejado(int key, int panelEstimulo, int fs, double fspa_cpi_x, 
            double fspa_cpi_y, double ppi, double fspa_cpp_x, double fspa_cpp_y, 
            boolean sentidoUpLeft, int direccion, boolean onMove, double contrat,
            double intensidadMedia) {
        super(key, panelEstimulo, fs, fspa_cpi_x, fspa_cpi_y, ppi, fspa_cpp_x, 
                fspa_cpp_y, sentidoUpLeft, direccion, contrat, intensidadMedia);
        this.onMove = onMove;
    }

    public boolean isOnMove() {
        return onMove;
    }

    public void setOnMove(boolean onMove) {
        this.onMove = onMove;
    }
}
