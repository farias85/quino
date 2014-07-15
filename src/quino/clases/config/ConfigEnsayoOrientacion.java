/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

/**
 *
 * @author admin
 */
public class ConfigEnsayoOrientacion extends ConfigEnsayoEnrejado {

    public ConfigEnsayoOrientacion(int key, int panelEstimulo, int fs, double fspa_cpi_x,
            double fspa_cpi_y, int ppi, double fspa_cpp_x, double fspa_cpp_y, boolean sentidoUpLeft,
            int direccion, boolean onMove, double contrat, double intensidadMedia) {
        super(key, panelEstimulo, fs, fspa_cpi_x, fspa_cpi_y, ppi, fspa_cpp_x,
                fspa_cpp_y, sentidoUpLeft, direccion, onMove, contrat, intensidadMedia);
    }

    public ConfigEnsayoOrientacion(int direccion, int ppi, boolean onMove,
            double contrat, double intensidadMedia) {
        super(direccion, ppi, onMove, contrat, intensidadMedia);
    }

    public ConfigEnsayoOrientacion(int direccion, int ppi, boolean onMove) {
        super(direccion, ppi, onMove);
    }

    public ConfigEnsayoOrientacion() {
        super();
    }
}
