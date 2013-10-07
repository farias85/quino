/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

import quino.util.Aleatorio;

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
    /**
     * El numero de la figura, 1. Cuadrado, 2. Rectangulo, 3. Triangulo, 4. Circulo
     */
    protected int numFigura;

    public ConfigEnsayoShapeDetect() {
        super();
    }

    public ConfigEnsayoShapeDetect(int key, int panelEstimulo, int densidad, int tolerancia, double pcShape, int numFigura) {
        super(key, panelEstimulo);
        this.densidad = densidad;
        this.tolerancia = tolerancia;
        this.pcShape = pcShape;
        this.numFigura = numFigura;
    }

    public ConfigEnsayoShapeDetect(int densidad, int tolerancia, double pcShape, int numFigura) {
        super(0, 0);
        this.densidad = densidad;
        this.tolerancia = tolerancia;
        this.pcShape = pcShape;
        this.numFigura = numFigura;
    }

    public ConfigEnsayoShapeDetect(int densidad, int tolerancia, double pcShape) {
        super(0, 0);
        this.densidad = densidad;
        this.tolerancia = tolerancia;
        this.pcShape = pcShape;

        Aleatorio random = new Aleatorio();
        numFigura = random.nextInt(1, 4);
        panelEstimulo = random.nextInt(0, 2);

        switch (panelEstimulo) {
            case 0:
                key = 0; //ninguna tecla
                break;
            case 1:
                key = 37;//tecla de la flecha izquierda
                break;
            case 2:
                key = 39;//tecla de la flecha derecha
                break;
        }
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

    public int getNumFigura() {
        return numFigura;
    }

    public void setNumFigura(int numFigura) {
        this.numFigura = numFigura;
    }
}