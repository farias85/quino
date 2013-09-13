/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.edition2nd.old;

import quino.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 * @author Felipao
 */
public class ShapeJPanelOld extends JPanel {

    private ArrayList<Segment2DOld> segmentos = new ArrayList<Segment2DOld>();
    private int cantidad;
    private int densidad;

    public ShapeJPanelOld(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < segmentos.size(); i++) {
            g.setColor(Color.WHITE);
            segmentos.get(i).Pintar(g);
        }
    }

    /*public void moverEnDireccion(int direccion, double desplazamientX, double desplazamientoY) {
    for (int i = 0; i < cantidad; i++) {
    Aleatorio dir = new Aleatorio();
    int pos = dir.nextInt(0, puntos.size() - 1);

    Punto p1 = new Punto(puntos.get(pos).getX(), puntos.get(pos).getY());
    puntos.get(pos).mover(direccion);
    Punto p2 = puntos.get(pos);
    actualizarAnguloPuntos(p1, p2, desplazamientX, desplazamientoY);
    }
    }*/
    public void actualizarAnguloPuntos(Punto p1, Punto p2, double desplazamientX, double desplazamientoY) {
        desplazamientX += this.getLocation().getX();
        desplazamientoY += this.getLocation().getY();

        Punto antes = new Punto(p1.getX() + desplazamientX, p1.getY() + desplazamientoY);
        double anguloAntes = QuinoTools.getAngulo(antes);

        Punto despues = new Punto(p2.getX() + desplazamientX, p2.getY() + desplazamientoY);
        double anguloDespues = QuinoTools.getAngulo(despues);

        p2.setAngulo((anguloAntes + anguloDespues) / 2);

        //Muy pequeño el valor de la diferencia debido a q los
        //puntos solo se desplazan dos pixeles en cada movimiento
        //angulo = Math.abs(anguloActual - anguloDespues);
        //angulo = (anguloActual + anguloDespues) / 2;
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        Image image = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = image.getGraphics();
        paint(gr);
        g.drawImage(image, 0, 0, this);
    }

    public void clear() {
        segmentos.clear();
    }

    public void rellenar(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
        segmentos = new ArrayList<Segment2DOld>();

        rellenarRandom(densidad);
    }

    public void rellenarShape(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
        segmentos = new ArrayList<Segment2DOld>();

        //Point inicio = new Point(random.nextInt(20, 50), random.nextInt(50, 150));
        Point inicio = new Point(150, 150);
        int densidadOthers = (int) QuinoTools.porciento(60, densidad);
        int densidadShape = (int) QuinoTools.porciento(40, densidad);

        ShapeRectangleOld shapeRectangle = new ShapeRectangleOld(densidadShape, inicio, 100);
        segmentos = shapeRectangle.getSegmentos();

        rellenarRandom(densidadOthers);
    }

    private void rellenarRandom(int densidad) {
        Aleatorio random = new Aleatorio();
        int tamanno = 10;
        for (int i = 0; i < densidad; i++) {
            int direccion = random.nextInt(1, 8);
            int xp1 = random.nextInt(tamanno, getWidth() - tamanno);
            int yp1 = random.nextInt(tamanno, getHeight() - tamanno);
            Point p1 = new Point(xp1, yp1);
            Segment2DOld segment2D = new Segment2DOld(p1, direccion, tamanno);
            segmentos.add(segment2D);
        }
    }

    /*public double promedioAngulo() {
    int puntosMovidos = 0;
    double sumatoriaAngulos = 0;

    for (int i = 0; i < puntos.size(); i++) {
    if (puntos.get(i).getAngulo() != 0) {
    puntosMovidos++;
    sumatoriaAngulos += puntos.get(i).getAngulo();
    }
    }
    return Double.parseDouble(ConfigApp.DECIMAL_FORMAT.format(sumatoriaAngulos / puntosMovidos));
    }*/
}
