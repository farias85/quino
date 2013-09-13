/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.edition2nd.old;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Administrador
 */
public class Segment2DOld {

    private Point p1;
    private Point p2;
    private int direccion;
    private float tamanno;

    public Segment2DOld(Point p1, Point p2, int direccion, float tamanno) {
        this.p1 = p1;
        this.p2 = p2;
        this.direccion = direccion;
        this.tamanno = tamanno;
    }

    public Segment2DOld(Point p1, int direccion, float tamanno) {
        this.p1 = p1;
        this.direccion = direccion;
        this.tamanno = tamanno;
        buscarP2();
    }

    public static double buscarPendienteRecta(Point p1, Point p2){
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

    public static double buscarOrdenadaRecta(Point p1, Point p2){
        double m = buscarPendienteRecta(p1, p2);

        //(y = mx + n)  == (n = y - mx)
        return p1.getY() - m * p1.getX();
    }

    private void buscarP2() {
        this.p2 = new Point(this.p1);
        switch (direccion) {
            case 1: {
                p2.setLocation(p2.getX(), p2.getY() - tamanno);
            }
            break;
            case 2: {
                p2.setLocation(p2.getX(), p2.getY() + tamanno);
            }
            break;
            case 3: {
                p2.setLocation(p2.getX() + tamanno, p2.getY());
            }
            break;
            case 4: {
                p2.setLocation(p2.getX() - tamanno, p2.getY());
            }
            break;
            case 5: {
                p2.setLocation(p2.getX() + tamanno, p2.getY() - tamanno);
            }
            break;
            case 6: {
                p2.setLocation(p2.getX() - tamanno, p2.getY() - tamanno);
            }
            break;
            case 7: {
                p2.setLocation(p2.getX() + tamanno, p2.getY() + tamanno);
            }
            break;
            case 8: {
                p2.setLocation(p2.getX() - tamanno, p2.getY() + tamanno);
            }
            break;
        }
    }

    public void Pintar(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public float getTamanno() {
        return tamanno;
    }

    public void setTamanno(float tamanno) {
        this.tamanno = tamanno;
    }
}
