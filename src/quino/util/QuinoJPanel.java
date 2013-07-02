/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 * @author Casa
 */
public class QuinoJPanel extends JPanel {

    private ArrayList<Punto> puntos = new ArrayList<Punto>();
    private int cantidad;
    private int densidad;

    public QuinoJPanel(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < puntos.size(); i++) {
            g.setColor(Color.WHITE);
            puntos.get(i).Pintar(g);
        }
    }

    public void moverEnDireccion(int direccion) {
        for (int i = 0; i < cantidad; i++) {
            Aleatorio dir = new Aleatorio();
            int pos = dir.nextInt(0, puntos.size() - 1);
            puntos.get(pos).Mover(direccion);
        }
    }

    public void moverAsincronico() {
        for (int i = 0; i < cantidad; i++) {
            Aleatorio dir = new Aleatorio();
            int x = dir.nextInt(1, 8);
            int pos = dir.nextInt(0, puntos.size() - 1);
            puntos.get(pos).Mover(x);
        }
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        Image image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = image.getGraphics();
        paint(gr);
        g.drawImage(image, 0, 0, this);
    }

    public void clear() {
        puntos.clear();
    }

    public void Rellenar(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
        puntos = new ArrayList<Punto>();
        for (int i = 0; i < this.densidad; i++) {
            Punto p = new Punto(this.getWidth(), this.getHeight());
            puntos.add(p);
        }
    }

    public Punto MidPunto(boolean fob) {
        //NO SE Q SE HACE ACÁ
        //VER ESTO

        /*if (fob) {
        return puntos.get(cantidad / 4);
        } else {
        return puntos.get(cantidad / 2);
        }*/

        //ESTO LO PUSE YO DE EJEMPLO PQ LO ANTERIOR EXPLOTA Y NO SE LO Q ES
        if (puntos.size() > 0) {
            return puntos.get(0);
        }
        return new Punto(1, 1);
    }
}
