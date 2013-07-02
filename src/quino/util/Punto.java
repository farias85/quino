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
 * @author davisito
 */
public class Punto {

    private int x;
    private int y;

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

    /*public void mov_Asincronico() {
        Aleatorio ran = new Aleatorio();
        int dir = ran.nextInt(1, 8);
        Mover(dir);
    }*/

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
        // Image image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);

        g.setColor(Color.WHITE);
        //int ax = (int)x;
        //int ay = (int)y;
        g.fillOval(x, y, 4, 4);
        //  canvas.getGraphics().drawImage(image, 0,0, canvas);
    }
}
