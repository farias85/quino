/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import quino.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import quino.clases.model.AbstractShape;
import quino.clases.model.Segment2D;
import quino.clases.model.ShapeCircle;
import quino.clases.model.ShapeRectangle;
import quino.clases.model.ShapeSquare;
import quino.clases.model.ShapeTriangle;

/**
 *
 * @author Felipao
 */
public class JPanelShape extends JPanel {

    private ArrayList<Segment2D> segmentos = new ArrayList<Segment2D>();
    private int densidad;
    private AbstractShape shape;

    public JPanelShape(int densidad) {
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

    public void rellenar(int densidad) {
        this.densidad = densidad;
        segmentos = new ArrayList<Segment2D>();

        rellenarRandom(densidad);
    }

    public void rellenarShape(int densidad, double pcShape, int tolerancia, int numeroFigura) {
        this.densidad = densidad;
        segmentos = new ArrayList<Segment2D>();

        if (tolerancia > 20) {
            tolerancia = 20;
        }
        
        pcShape = pcShape > 100 || pcShape < 0 ? 10 : pcShape;
        double pcOthers = 100 - pcShape;

        int densidadOthers = (int) QuinoTools.porciento(pcOthers, densidad);
        int densidadShape = (int) QuinoTools.porciento(pcShape, densidad);

        seleccionarFigura(numeroFigura, densidadShape, tolerancia, 1);
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
