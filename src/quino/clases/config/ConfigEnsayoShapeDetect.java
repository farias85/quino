/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

/**
 *
 * @author farias
 */
public class ConfigEnsayoShapeDetect extends ConfigEnsayo {

    /**
     * Densidad de rectas a representar en la prueba
     */
    protected int densidad;

    /**
     * Tolerancia respecto a la guia q define un trazo o lado de una figura.
     * tolerancia = 0, las rectas alrededor de la guia se pintan justamente sobre la guia
     */
    protected int tolerancia;

    /**
     * Por ciento de la densidad de rectas q se utilizaran para pintar la forma
     */
    protected double pcShape;

    public ConfigEnsayoShapeDetect(int key, int densidad, int tolerancia, double pcShape) {
        super(key);
        this.densidad = densidad;
        this.tolerancia = tolerancia;
        this.pcShape = pcShape;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    public double getPcShape() {
        return pcShape;
    }

    public void setPcShape(double pcShape) {
        this.pcShape = pcShape;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }
}
