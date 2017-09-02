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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 12/09/2013.
 */
package quino.clases.model;

import java.awt.Point;
import quino.util.Aleatorio;
import quino.util.QuinoTools;

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
