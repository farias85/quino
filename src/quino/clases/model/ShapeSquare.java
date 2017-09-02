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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 13/09/2013.
 */
package quino.clases.model;

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
