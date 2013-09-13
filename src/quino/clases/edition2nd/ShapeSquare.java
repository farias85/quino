/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.edition2nd;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import quino.util.Aleatorio;
import quino.util.QuinoTools;

/**
 *
 * @author Administrador
 */
public class ShapeSquare extends AbstractShape {

    protected Rectangle rectangle;
    protected int length = 200;

    public ShapeSquare(int densidad, int tolerancia, int pcDimension) {
        super(densidad, tolerancia, pcDimension);

        point = new Point(new Aleatorio().nextInt(120, 150),
                new Aleatorio().nextInt(250, 400));

        Dimension dimension = new Dimension(length, length);
        rectangle = new Rectangle(point, dimension);
    }

    @Override
    protected void initShape() {

        Point p1 = rectangle.getLocation();
        double densidad1 = QuinoTools.porciento(20, densidad);
        double densidad2 = QuinoTools.porciento(30, densidad);

        Point p2 = new Point(rectangle.getLocation().x + rectangle.width,
                rectangle.getLocation().y);

        Point p3 = new Point(rectangle.getLocation().x + rectangle.width,
                rectangle.getLocation().y + rectangle.height);

        Point p4 = new Point(rectangle.getLocation().x,
                rectangle.getLocation().y + rectangle.height);

        Segment2DGuia guia1 = new Segment2DGuia(p1, p2, densidad1);
        segment2DGuias.add(guia1);
        Segment2DGuia guia2 = new Segment2DGuia(p2, p3, densidad2);
        guia2.setVertical(true);
        segment2DGuias.add(guia2);
        Segment2DGuia guia3 = new Segment2DGuia(p3, p4, densidad1);
        segment2DGuias.add(guia3);
        Segment2DGuia guia4 = new Segment2DGuia(p4, p1, densidad2);
        guia4.setVertical(true);
        segment2DGuias.add(guia4);
    }
}
