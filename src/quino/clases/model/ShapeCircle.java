/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import java.awt.Point;
import quino.util.Aleatorio;

/**
 *
 * @author Administrador
 */
public class ShapeCircle extends AbstractShape {

    private int radio = 110;

    public ShapeCircle(int densidad, int tolerancia, int pcDimension) {
        super(densidad, tolerancia, pcDimension);

        point = new Point(new Aleatorio().nextInt(200, 240),
                new Aleatorio().nextInt(200, 400));
    }

    private Point puntoCircunferencia(int i) {
        double cos = Math.cos(i);
        double sin = Math.sin(i);
        int x = point.x + (int) (radio * cos);
        int y = point.y + (int) (radio * sin);
        return new Point(x, y);
    }

    @Override
    protected void initShape() {

        int cantidadPuntos = 55;
        int densidadParcial = densidad / cantidadPuntos;
        densidadParcial = densidadParcial < 0 ? 1 : densidadParcial;


        Point p0 = puntoCircunferencia(0);
        for (int i = 1; i < cantidadPuntos; i++) {
            Point p1 = puntoCircunferencia(i);

            Segment2DGuia guia = new Segment2DGuia(p0, p1, densidadParcial);
            segment2DGuias.add(guia);

            p0 = puntoCircunferencia(i);
        }
    }
}
