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

    public ConfigEnsayoEnrejado(int key, int panelEstimulo) {
        super(key, panelEstimulo);
    }

    public ConfigEnsayoEnrejado() {
        super();

        fs = 10;
        ppi = 80;
        fspa_cpi_x = 0;
        fspa_cpi_y = 1.0;

        fspa_cpp_x = fspa_cpi_x / ppi;
        fspa_cpp_y = fspa_cpi_y / ppi;
    }

    public ConfigEnsayoEnrejado(int fs, double fspa_cpi_x, double fspa_cpi_y, int ppi) {
        super(0, 0);
        
        this.fs = fs;
        this.fspa_cpi_x = fspa_cpi_x;
        this.fspa_cpi_y = fspa_cpi_y;
        this.ppi = ppi;
        
        this.fspa_cpp_x = fspa_cpi_x / ppi;
        this.fspa_cpp_y = fspa_cpi_y / ppi;
    }



    public ConfigEnsayoEnrejado(int key, int panelEstimulo, int fs, double fspa_cpi_x, double fspa_cpi_y, int ppi, double fspa_cpp_x, double fspa_cpp_y) {
        super(key, panelEstimulo, fs, fspa_cpi_x, fspa_cpi_y, ppi, fspa_cpp_x, fspa_cpp_y);
    }


}
