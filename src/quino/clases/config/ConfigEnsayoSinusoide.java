/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

import java.awt.Toolkit;

/**
 *
 * @author farias
 */
public abstract class ConfigEnsayoSinusoide extends ConfigEnsayo {

    protected int fs;                     // sampling frequency in frames / s
    protected double fspa_cpi_x;          // spatial frequency in x, cicles / inch
    protected double fspa_cpi_y;          // spatial frequency in y, cicles / inch
    protected int ppi;                    //screen pixels x inch
    protected double fspa_cpp_x;          // spatial frequency in x,cicles / pixels
    protected double fspa_cpp_y;          // spatial frequency in x,cicles / pixels

    public ConfigEnsayoSinusoide() {
        super();

        ppi = Toolkit.getDefaultToolkit().getScreenResolution() / 3;
    }

    public ConfigEnsayoSinusoide(int key, int panelEstimulo) {
        super(key, panelEstimulo);
    }

    public ConfigEnsayoSinusoide(int key, int panelEstimulo, int fs, double fspa_cpi_x, double fspa_cpi_y, int ppi, double fspa_cpp_x, double fspa_cpp_y) {
        super(key, panelEstimulo);

        this.fs = fs;
        this.fspa_cpi_x = fspa_cpi_x;
        this.fspa_cpi_y = fspa_cpi_y;
        this.ppi = ppi;
        this.fspa_cpp_x = fspa_cpp_x;
        this.fspa_cpp_y = fspa_cpp_y;
    }

    public int getFs() {
        return fs;
    }

    public void setFs(int fs) {
        this.fs = fs;
    }

    public double getFspa_cpi_x() {
        return fspa_cpi_x;
    }

    public void setFspa_cpi_x(double fspa_cpi_x) {
        this.fspa_cpi_x = fspa_cpi_x;
    }

    public double getFspa_cpi_y() {
        return fspa_cpi_y;
    }

    public void setFspa_cpi_y(double fspa_cpi_y) {
        this.fspa_cpi_y = fspa_cpi_y;
    }

    public double getFspa_cpp_x() {
        return fspa_cpp_x;
    }

    public void setFspa_cpp_x(double fspa_cpp_x) {
        this.fspa_cpp_x = fspa_cpp_x;
    }

    public double getFspa_cpp_y() {
        return fspa_cpp_y;
    }

    public void setFspa_cpp_y(double fspa_cpp_y) {
        this.fspa_cpp_y = fspa_cpp_y;
    }

    public int getPpi() {
        return ppi;
    }

    public void setPpi(int ppi) {
        this.ppi = ppi;
    }
}
