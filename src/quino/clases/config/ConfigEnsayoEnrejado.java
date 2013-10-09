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

    public ConfigEnsayoEnrejado() {
        super();
    }

    public ConfigEnsayoEnrejado(int direccion, int ppi, boolean onMove) {
        super(direccion, ppi, onMove);
    }

    public ConfigEnsayoEnrejado(int key, int panelEstimulo, int fs, double fspa_cpi_x, 
            double fspa_cpi_y, int ppi, double fspa_cpp_x, double fspa_cpp_y, 
            boolean sentidoUpLeft, int direccion, boolean onMove) {
        super(key, panelEstimulo, fs, fspa_cpi_x, fspa_cpi_y, ppi, fspa_cpp_x, 
                fspa_cpp_y, sentidoUpLeft, direccion, onMove);
    }
}
