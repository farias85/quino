/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.edition2nd;

import java.awt.Point;
import quino.util.Aleatorio;
import quino.util.QuinoTools;

/**
 *
 * @author Administrador
 */
public class ShapeTriangle extends AbstractShape {

    public ShapeTriangle(int densidad, int tolerancia, int pcDimension) {
        super(densidad, tolerancia, pcDimension);

        point = new Point(new Aleatorio().nextInt(240, 270),
                new Aleatorio().nextInt(120, 180));
    }

    @Override
    protected void initShape() {

        int relacionBase = (int) (point.getX() / 2);
        int relacionLaterales = (int) (point.getY() * 3.0F);

        Point p1 = new Point(relacionBase, relacionLaterales);
        Point p2 = new Point(point);
        Point p3 = new Point((int) (2 * point.getX()) - relacionBase, relacionLaterales);

        double densidad1 = QuinoTools.porciento(33, densidad);

        Segment2DGuia guia1 = new Segment2DGuia(p1, p2, densidad1);
        segment2DGuias.add(guia1);
        Segment2DGuia guia2 = new Segment2DGuia(p2, p3, densidad1);
        segment2DGuias.add(guia2);
        Segment2DGuia guia3 = new Segment2DGuia(p3, p1, densidad1);
        segment2DGuias.add(guia3);
    }
}
