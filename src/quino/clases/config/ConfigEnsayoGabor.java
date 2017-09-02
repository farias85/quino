/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 10/07/2013.
 */
package quino.clases.config;

import java.awt.Point;
import java.awt.Toolkit;

public class ConfigEnsayoGabor extends ConfigEnsayoSinusoide {

    /**
     * Centro de ambos circulos
     */
    private Point centro = new Point(0, 0);
    //central stimulus
    /**
     * temporal frequency in cicles / seconds (Hz)
     */
    private double ftemp = 2;
    /**
     * gaussian standar deviation [pixel]
     */
    private double gaussianStdpix = 40;
    //periferal stimulis
    /**
     * spatial frequency in x, cicles / inch
     */
    private double fspa_cpi_x_per = 0;
    /**
     * spatial frequency in y, cicles / inch
     */
    private double fspa_cpi_y_per = 1.0;
    /**
     * temporal frequency in cicles / seconds (Hz)
     */
    private double ftemp_per = 2;
    /**
     * internal radious
     */
    protected int radio1 = 75;
    /**
     * external radious
     */
    protected int radio2 = 180;
    /**
     * ppi2
     */
    protected double ppi2 = Toolkit.getDefaultToolkit().getScreenResolution() / 8;
    /**
     * spatial frequency in x,cicles / pixels
     */
    private double fspa_cpp_x_per = fspa_cpi_x_per / ppi2;
    /**
     * spatial frequency in y,cicles / pixels
     */
    private double fspa_cpp_y_per = fspa_cpi_y_per / ppi2;

    public ConfigEnsayoGabor() {
        super();
    }

    public ConfigEnsayoGabor(int direccion, double ppi, double contrat, double intensidadMedia,
            double gaussianStdpix, int radio1, int radio2, double ppi2) {
        super(direccion, ppi, contrat, intensidadMedia);
        this.gaussianStdpix = gaussianStdpix;
        this.radio1 = radio1;
        this.radio2 = radio2;
        this.ppi2 = ppi2;
        this.key = getKey2Press(direccion);

        fspa_cpp_x_per = fspa_cpi_x_per / ppi2;
        fspa_cpp_y_per = fspa_cpi_y_per / ppi2;
    }

    public ConfigEnsayoGabor(int direccion, double ppi) {
        super(direccion, ppi);
        this.key = getKey2Press(direccion);
    }

    protected int getKey2Press(int direccion) {
        int key2Press = 0;
        switch (direccion) {
            case 1:
                key2Press = 38;
                break;
            case 2:
                key2Press = 40;
                break;
            case 3:
                key2Press = 39;
                break;
            case 4:
                key2Press = 37;
                break;
        }
        return key2Press;
    }

    public ConfigEnsayoGabor(int key, int panelEstimulo, int fs, double fspa_cpi_x,
            double fspa_cpi_y, double ppi, double fspa_cpp_x, double fspa_cpp_y,
            boolean sentidoUpLeft, int direccion, double contrat,
            double intensidadMedia) {
        super(key, panelEstimulo, fs, fspa_cpi_x, fspa_cpi_y, ppi, fspa_cpp_x,
                fspa_cpp_y, sentidoUpLeft, direccion, contrat, intensidadMedia);
    }

    public Point getCentro() {
        return centro;
    }

    public void setCentro(Point centro) {
        this.centro = centro;
    }

    public double getFspa_cpi_x_per() {
        return fspa_cpi_x_per;
    }

    public void setFspa_cpi_x_per(double fspa_cpi_x_per) {
        this.fspa_cpi_x_per = fspa_cpi_x_per;
    }

    public double getFspa_cpi_y_per() {
        return fspa_cpi_y_per;
    }

    public void setFspa_cpi_y_per(double fspa_cpi_y_per) {
        this.fspa_cpi_y_per = fspa_cpi_y_per;
    }

    public double getFspa_cpp_x_per() {
        return fspa_cpp_x_per;
    }

    public void setFspa_cpp_x_per(double fspa_cpp_x_per) {
        this.fspa_cpp_x_per = fspa_cpp_x_per;
    }

    public double getFspa_cpp_y_per() {
        return fspa_cpp_y_per;
    }

    public void setFspa_cpp_y_per(double fspa_cpp_y_per) {
        this.fspa_cpp_y_per = fspa_cpp_y_per;
    }

    public double getFtemp() {
        return ftemp;
    }

    public void setFtemp(double ftemp) {
        this.ftemp = ftemp;
    }

    public double getFtemp_per() {
        return ftemp_per;
    }

    public void setFtemp_per(double ftemp_per) {
        this.ftemp_per = ftemp_per;
    }

    public double getGaussianStdpix() {
        return gaussianStdpix;
    }

    public void setGaussianStdpix(double gaussianStdpix) {
        this.gaussianStdpix = gaussianStdpix;
    }

    public int getRadio1() {
        return radio1;
    }

    public void setRadio1(int radio1) {
        this.radio1 = radio1;
    }

    public int getRadio2() {
        return radio2;
    }

    public void setRadio2(int radio2) {
        this.radio2 = radio2;
    }

    public double getPpi2() {
        return ppi2;
    }

    public void setPpi2(double ppi2) {
        this.ppi2 = ppi2;
    }
}
