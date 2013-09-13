/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.edition2nd.old;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import quino.util.Aleatorio;
import quino.util.QuinoTools;

/**
 *
 * @author Administrador
 */
public class ShapeRectangleOld {

    private ArrayList<Segment2DOld> segmentos = new ArrayList<Segment2DOld>();

    public ArrayList<Segment2DOld> getSegmentos() {
        return segmentos;
    }

    public ShapeRectangleOld(int densidad, Point point, double porcientoTamanno) {
        int width = 200;
        int height = 400;

        porcientoTamanno = Math.abs(porcientoTamanno);
        porcientoTamanno = porcientoTamanno > 100 ? 100 : porcientoTamanno;
        width = (int) QuinoTools.porciento(porcientoTamanno, width);
        height = (int) QuinoTools.porciento(porcientoTamanno, height);

        Dimension dimension = new Dimension(width, height);
        Rectangle rectangle = new Rectangle(point, dimension);
        int tolerancia = 40;

        //Estos son los segmentos q rodean todo el lado izquierdo del rectangulo
        //La diraccion es hacia abajo 2
        int densidadLadoIzquierdo = (int) QuinoTools.porciento(25, densidad);
        Segment2DOld ladoIzquierdo = new Segment2DOld(rectangle.getLocation(), 2,
                (int) rectangle.getHeight());
        getSegmentosCercanos(ladoIzquierdo, densidadLadoIzquierdo, tolerancia);

        //Estos son los segmentos q rodean todo el lado derecho del rectangulo
        //La diraccion es hacia abajo 2
        int densidadLadoDerecho = (int) QuinoTools.porciento(25, densidad);
        Point superiorDerecho = new Point((int) rectangle.getLocation().getX()
                + (int) rectangle.getWidth(),
                (int) rectangle.getLocation().getY());
        Segment2DOld ladoDerecho = new Segment2DOld(superiorDerecho, 2,
                (int) rectangle.getHeight());
        getSegmentosCercanos(ladoDerecho, densidadLadoDerecho, tolerancia);

        //Estos son los segmentos q rodean todo el lado de arriba del rectangulo
        //La diraccion es hacia la derecha 3
        int densidadLadoArriba = (int) QuinoTools.porciento(25, densidad);
        Segment2DOld ladoArriba = new Segment2DOld(rectangle.getLocation(), 3,
                (int) rectangle.getWidth());
        getSegmentosCercanos(ladoArriba, densidadLadoArriba, tolerancia);

        //Estos son los segmentos q rodean todo el lado de arriba del rectangulo
        //La diraccion es hacia la derecha 3
        int densidadLadoAbajo = (int) QuinoTools.porciento(25, densidad);
        Point inferiorDerecho = new Point((int) rectangle.getLocation().getX(),
                (int) rectangle.getLocation().getY() + (int) rectangle.getHeight());
        Segment2DOld ladoAbajo = new Segment2DOld(inferiorDerecho, 3,
                (int) rectangle.getWidth());
        getSegmentosCercanos(ladoAbajo, densidadLadoAbajo, tolerancia);
    }

    private void getSegmentosCercanos(Segment2DOld segment2D, int densidad,
            int tolerancia) {

        int tamanno = 10;
        Aleatorio random = new Aleatorio();

        for (int i = 0; i < densidad; i++) {
            int segX = random.nextInt(segment2D.getP1().x - tolerancia,
                    segment2D.getP2().x + tolerancia);
            int segY = random.nextInt(segment2D.getP1().y - tolerancia,
                    segment2D.getP2().y + tolerancia);

            Point p = new Point(segX, segY);
            Segment2DOld nuevo = new Segment2DOld(p, segment2D.getDireccion(), tamanno);
            segmentos.add(nuevo);
        }
    }
}
