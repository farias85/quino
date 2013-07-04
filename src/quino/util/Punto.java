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

    private int x;
    private int y;

    /**
     * Ángulo respecto al centro de la pantalla
     */
    private double angulo = 0;

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public Punto(int largo, int alto) {
        Random ran = new Random();
        this.x = ran.nextInt(largo);
        this.y = ran.nextInt(alto);
    }

    public Punto(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public void Mover(int direccion) {
        Punto actual = new Punto(this.getX(), this.getY());
        double anguloActual = QuinoTools.getAngulo(actual);

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

        double anguloDespues = QuinoTools.getAngulo(this);
        angulo = Math.abs(anguloActual - anguloDespues);
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
}
