/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

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
public class ShapeJPanel extends JPanel {

    private ArrayList<Segment2D> segmentos = new ArrayList<Segment2D>();
    private int cantidad;
    private int densidad;
    private AbstractShape shape;

    public ShapeJPanel(int densidad, int cantidad) {
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
        segmentos = new ArrayList<Segment2D>();

        rellenarRandom(densidad);
    }

    public void rellenarShape(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
        segmentos = new ArrayList<Segment2D>();

        int densidadOthers = (int) QuinoTools.porciento(90, densidad);
        int densidadShape = (int) QuinoTools.porciento(10, densidad);
        int tolerancia = 2;

        Aleatorio random = new Aleatorio();
        int numero = random.nextInt(1, 4);

        seleccionarFigura(numero, densidadShape, tolerancia, 1);
        segmentos = shape.getSegmentos();

        rellenarRandom(densidadOthers);
    }

    private void rellenarRandom(int densidad) {
        Aleatorio random = new Aleatorio();
        int tamanno = 10;
        for (int i = 0; i < densidad; i++) {

            int xp1 = random.nextInt(tamanno, getWidth() - tamanno);
            int yp1 = random.nextInt(tamanno, getHeight() - tamanno);
            Point p1 = new Point(xp1, yp1);

            Segment2D seg2D = new Segment2D(p1);
            segmentos.add(seg2D);
        }
    }

    private void seleccionarFigura(int numero, int densidadParcial, int tolerancia,
            int pcDimension) {

        switch (numero) {
            case 1:
                shape = new ShapeSquare(densidadParcial, tolerancia, pcDimension);
                break;
            case 2:
                shape = new ShapeRectangle(densidadParcial, tolerancia, pcDimension);
                break;
            case 3:
                shape = new ShapeTriangle(densidadParcial, tolerancia, pcDimension);
                break;
            case 4:
                shape = new ShapeCircle(densidadParcial, tolerancia, pcDimension);
                break;
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    public ArrayList<Segment2D> getSegmentos() {
        return segmentos;
    }

    public void setSegmentos(ArrayList<Segment2D> segmentos) {
        this.segmentos = segmentos;
    }
}
