/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.edition2nd;

import java.awt.Point;

/**
 *
 * @author Administrador
 */
public class Segment2DGuia extends Segment2D {

    private double densidadParcial;
    private boolean vertical = false;

    public Segment2DGuia(Point p1, Point p2, double densidadParcial) {
        this.p1 = p1;
        this.p2 = p2;
        this.densidadParcial = densidadParcial;

        if (p1.getX() != p2.getX()) {
            pendiente = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
            ordenada = p1.getY() - pendiente * p1.getX();
        }
    }

    public double getDensidadParcial() {
        return densidadParcial;
    }

    public void setDensidadParcial(double densidadParcial) {
        this.densidadParcial = densidadParcial;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
}
