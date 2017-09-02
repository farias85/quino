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

import java.awt.Point;

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
