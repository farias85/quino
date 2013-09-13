/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import quino.util.Aleatorio;

/**
 *
 * @author Administrador
 */
public class ShapeRectangle extends ShapeSquare {

    public ShapeRectangle(int densidad, int tolerancia, int pcDimension) {
        super(densidad, tolerancia, pcDimension);

        point = new Point(new Aleatorio().nextInt(110, 160),
                new Aleatorio().nextInt(110, 170));

        Dimension dimension = new Dimension(length, length * 2);
        rectangle = new Rectangle(point, dimension);
    }
}
