/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Felipao
 */
public class Punto {

    /**
     * La cordenada x del punto
     */
    private int x;
    /**
     * La cordenada y del punto
     */
    private int y;
    /**
     * Ángulo respecto al centro de la pantalla
     */
    private double angulo = 0;

    /**
     * Constructor para valores enteros de cordenada de punto. El punto se crea
     * con valores Random
     * @param largo Limite superior de valores de x
     * @param alto Limite superior de valores de y
     */
    public Punto(int largo, int alto) {
        Random random = new Random();
        this.x = random.nextInt(largo);
        this.y = random.nextInt(alto);
    }

    public Punto(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public void mover(int direccion) {
        switch (direccion) {
            case 1: {
                this.y -= 2;
            }
            break;
            case 2: {
                this.y += 2;
            }
            break;
            case 3: {
                this.x += 2;
            }
            break;
            case 4: {
                this.x -= 2;
            }
            break;
            case 5: {
                this.x += 2;
                this.y -= 2;
            }
            break;
            case 6: {
                this.x -= 2;
                this.y -= 2;
            }
            break;
            case 7: {
                this.x += 2;
                this.y += 2;
            }
            break;
            case 8: {
                this.x -= 2;
                this.y += 2;
            }
            break;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void Pintar(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 4, 4);
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
}
