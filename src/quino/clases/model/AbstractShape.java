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
import java.util.ArrayList;
import quino.util.Aleatorio;

public abstract class AbstractShape {

    protected ArrayList<Segment2DGuia> segment2DGuias = new ArrayList<Segment2DGuia>();
    protected ArrayList<Segment2D> segmentos = new ArrayList<Segment2D>();
    protected int densidad;
    protected int tolerancia;
    protected int pcDimension;
    protected Point point;

    public AbstractShape(int densidad, int tolerancia, int pcDimension) {
        this.densidad = densidad;
        this.tolerancia = tolerancia;
        this.pcDimension = pcDimension;
    }

    protected abstract void initShape();

    protected void cargarSegmentos() {
        for (int i = 0; i < segment2DGuias.size(); i++) {
            buscarSegmentosCercanos(segment2DGuias.get(i),
                    (int) segment2DGuias.get(i).getDensidadParcial());
        }
    }

    /**
     * Super Método, :-)
     *
     * @param guia
     * @param densidadParcial
     */
    protected void buscarSegmentosCercanos(Segment2DGuia guia, int densidadParcial) {
        Aleatorio random = new Aleatorio();

        while (densidadParcial != 0) {

            double angle = Math.atan(guia.getPendiente());
            double incremento = tolerancia * Math.sin(Math.abs(angle));

            int inf = (int) (guia.getOrdenada() - tolerancia - incremento);
            int sup = (int) (guia.getOrdenada() + tolerancia + incremento);
            int ordenada = random.nextInt(inf, sup);

            if (!guia.isVertical()) {
                int deltaX = (int) (Math.abs(guia.getP1().getX() - guia.getP2().getX()));
                boolean isGuiaP1 = guia.getP1().x < guia.getP2().x;
                int xInicio = isGuiaP1 ? random.nextInt(guia.getP1().x, guia.getP1().x - deltaX)
                        : random.nextInt(guia.getP2().x, guia.getP2().x + deltaX);

                Segment2D nuevo = new Segment2D(xInicio, guia.getPendiente(), ordenada);
                segmentos.add(nuevo);
            } else {
                int deltaY = (int) (Math.abs(guia.getP1().getY() - guia.getP2().getY()));
                boolean isGuiaP1 = guia.getP1().y < guia.getP2().y;

                int yInicio = isGuiaP1 ? random.nextInt(guia.getP1().y, guia.getP1().y - deltaY)
                        : random.nextInt(guia.getP2().y, guia.getP2().y + deltaY);
                int xInicio = random.nextInt(guia.getP1().x - tolerancia, guia.getP1().x + tolerancia);

                Point p = new Point(xInicio, yInicio);
                Segment2D nuevo = new Segment2D(p, guia.getPendiente(), ordenada);
                segmentos.add(nuevo);
            }
            densidadParcial--;
        }
    }

    public ArrayList<Segment2D> getSegmentos() {
        if (segment2DGuias.isEmpty()) {
            initShape();
            cargarSegmentos();
        }
        return segmentos;
    }
}
