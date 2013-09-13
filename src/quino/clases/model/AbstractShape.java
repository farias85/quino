/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import java.awt.Point;
import java.util.ArrayList;
import quino.util.Aleatorio;

/**
 *
 * @author Administrador
 */
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
     * 
     * @param guia Super Método, :-)
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
                boolean isGuiaP1 = guia.getP1().x < guia.getP2().x ? true : false;
                int xInicio = isGuiaP1 ? random.nextInt(guia.getP1().x, guia.getP1().x - deltaX)
                        : random.nextInt(guia.getP2().x, guia.getP2().x + deltaX);

                Segment2D nuevo = new Segment2D(xInicio, guia.getPendiente(), ordenada);
                segmentos.add(nuevo);
            } else {
                int deltaY = (int) (Math.abs(guia.getP1().getY() - guia.getP2().getY()));
                boolean isGuiaP1 = guia.getP1().y < guia.getP2().y ? true : false;

                int yInicio = isGuiaP1 ? random.nextInt(guia.getP1().y, guia.getP1().y - deltaY)
                        : random.nextInt(guia.getP2().y, guia.getP2().y + deltaY);
                int xInicio = random.nextInt(guia.getP1().x - tolerancia, guia.getP1().x + tolerancia);

                Point p = new Point(xInicio, yInicio);
                Segment2D nuevo = new Segment2D(p, guia.getPendiente(), ordenada);
                segmentos.add(nuevo);
            }
            densidadParcial--;
        }

        //segmentos.add((Segment2D) guia);
    }

    public ArrayList<Segment2D> getSegmentos() {
        if (segment2DGuias.isEmpty()) {
            initShape();
            cargarSegmentos();
        }
        return segmentos;
    }
}
