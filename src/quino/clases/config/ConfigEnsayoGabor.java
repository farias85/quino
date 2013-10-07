/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import java.awt.Point;

/**
 *
 * @author farias
 */
public class ConfigEnsayoGabor extends ConfigEnsayoSinusoide {

    private Point centro;
    private double contrat = 0.99;
    //central stimulus
    private double ftemp = 2;                      //temporal frequency in cicles / seconds (Hz)
    private double gaussianStdpix = 40;           //gaussian standar deviation [pixel]
    //periferal stimulis
    private double fspa_cpi_x_per = 0;             //spatial frequency in x, cicles / inch
    private double fspa_cpi_y_per = 1.0;           //spatial frequency in y, cicles / inch
    private double ftemp_per = 2;                  //temporal frequency in cicles / seconds (Hz)
    private int radio1 = 75;                       //internal radious
    private int radio2 = 180;                      //external radious
    private double fspa_cpp_x_per;                 //spatial frequency in x,cicles / pixels
    private double fspa_cpp_y_per;                 //spatial frequency in x,cicles / pixels
    private byte intensidadMedia = (byte) 128;
    private byte intensidadMax;


    public ConfigEnsayoGabor(int key, int panelEstimulo) {
        super(key, panelEstimulo);
    }

    public ConfigEnsayoGabor() {
        super();

        fs = 90;
        fspa_cpi_x = 1;                //spatial frequency in x, cicles / inch
        fspa_cpi_y = 0.0;               //spatial frequency in y, cicles / inch
        //ppi = 26;
        centro = new Point(0, 0);

        fspa_cpp_x = fspa_cpi_x / ppi;
        fspa_cpp_y = fspa_cpi_y / ppi;

        fspa_cpp_x_per = fspa_cpi_x_per / ppi;
        fspa_cpp_y_per = fspa_cpi_y_per / ppi;

        intensidadMax = (byte) (contrat * intensidadMedia);
    }

    public ConfigEnsayoGabor(int key, int panelEstimulo, int fs, double fspa_cpi_x, double fspa_cpi_y, int ppi, double fspa_cpp_x, double fspa_cpp_y) {
        super(key, panelEstimulo, fs, fspa_cpi_x, fspa_cpi_y, ppi, fspa_cpp_x, fspa_cpp_y);
    }

    public Point getCentro() {
        return centro;
    }

    public void setCentro(Point centro) {
        this.centro = centro;
    }

    public double getContrat() {
        return contrat;
    }

    public void setContrat(double contrat) {
        this.contrat = contrat;
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

    public byte getIntensidadMax() {
        return intensidadMax;
    }

    public void setIntensidadMax(byte intensidadMax) {
        this.intensidadMax = intensidadMax;
    }

    public byte getIntensidadMedia() {
        return intensidadMedia;
    }

    public void setIntensidadMedia(byte intensidadMedia) {
        this.intensidadMedia = intensidadMedia;
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
}
