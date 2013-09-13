/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.edition2nd;

import com.sun.imageio.plugins.common.BogusColorSpace;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.color.ColorSpace;
import quino.util.Aleatorio;

/**
 *
 * @author Felipe Rodriguez Arias
 */
public class Segment2D {

    /**
     * Punto p1 del segmento
     */
    protected Point p1;
    /**
     * Punto p2 del segmento
     */
    protected Point p2;
    /**
     * La pendiente de la recta
     */
    protected double pendiente;
    /**
     * La ordenada de la recta, lugar por donde corta el eje de las y con x=0
     */
    protected double ordenada;
    /**
     * 10 es la cantidad de pendientes q se crean desde 0 hasta PI
     * Es la inclinación de los segmentos descritos en el documento
     * de selectividad direccional
     */
    public final double fraccionPi = (Math.PI / 10);
    protected final int length = 10;

    public Segment2D() {
    }

    /**
     * Constructor para la generacion de segmentos aleatorios con pendientes
     * aleatorias.
     * @param p1
     */
    public Segment2D(Point p1) {
        this.p1 = p1;
        Aleatorio random = new Aleatorio();
        int direccion = random.nextInt(0, 10);

        //La direccion 5 = PI/2, Math.tan(PI/2) está indefinida
        if (direccion != 5) {
            double angleRadianes = direccion * fraccionPi;
            this.pendiente = Math.tan(angleRadianes);
            this.ordenada = p1.getY() - pendiente * p1.getX();

            double desplazamientoX = Math.cos(angleRadianes) * length;
            int p2X = (int) Math.round(this.p1.getX() + desplazamientoX);

            //y = mx + n
            int p2Y = (int) Math.round(pendiente * p2X + ordenada);

            p2 = new Point(p2X, p2Y);
        } else {
            p2 = new Point(p1.x, p1.y + length);
        }
    }

    /**
     * Constructor de Prueba para chequeos de pendiente. No se utiliza en la
     * aplicación
     * @param p1 Punto de inicio
     * @param direccion direccion de la pendiente 0 a 10
     */
    public Segment2D(Point p1, int direccion) {
        this.p1 = p1;

        //La direccion 5 = PI/2, Math.tan(PI/2) está indefinida
        if (direccion != 5) {
            double angleRadianes = direccion * fraccionPi;
            this.pendiente = Math.tan(angleRadianes);
            this.ordenada = p1.getY() - pendiente * p1.getX();

            double desplazamientoX = Math.cos(angleRadianes) * length;
            int p2X = (int) Math.round(this.p1.getX() + desplazamientoX);

            //y = mx + n
            int p2Y = (int) Math.round(pendiente * p2X + ordenada);

            p2 = new Point(p2X, p2Y);
        } else {
            p2 = new Point(p1.x, p1.y + length);
        }
    }

    /**
     * Constrcutor para crear los segmentos con dirección definida, junto a
     * un Segmento Guia. Se mueven alrededor del segmento guía cambiando
     * el valor de la ordenada
     * @param p1X El punto x del primer punto de la guia
     * @param pendiente La pendiente de la guia
     * @param ordenada La nueva ordenada para este segmento
     */
    public Segment2D(int p1X, double pendiente, double ordenada) {
        this.ordenada = ordenada;
        this.pendiente = pendiente;

        double angle = Math.atan(pendiente);
        double angleReal = angle < 0 ? 2 * Math.PI - Math.abs(angle) : angle;
        int direccion = (int) (angleReal / fraccionPi);

        int p1Y = getY(p1X);
        this.p1 = new Point(p1X, p1Y);
        //La direccion 5 = PI/2, Math.tan(PI/2) está indefinida
        if (direccion != 5) {
            double desplazamientoX = Math.cos(angle) * length;
            int p2X = (int) Math.round(this.p1.getX() + desplazamientoX);

            //y = mx + n
            int p2Y = (int) Math.round(pendiente * p2X + ordenada);

            p2 = new Point(p2X, p2Y);
        } else {
            p2 = new Point(p1.x, p1.y + length);
        }
    }

    /**
     * Constructor para crear segmentos de guias verticales. Ya q para
     * angulos rectos la tangente no está indefinida.
     * @param p1 Punto p1 para la inicialización
     * @param pendiente Este valor puede desecharse
     * @param ordenada Este valor puede desecharse
     */
    public Segment2D(Point p1, double pendiente, double ordenada) {
        this.p1 = p1;
        this.ordenada = ordenada;
        this.pendiente = pendiente;

        p2 = new Point(p1.x, p1.y + length);
    }

    public void Pintar(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public final int getY(int x) {
        return (int) Math.round(pendiente * x + ordenada);
    }

    public double getDistancia() {
        return p1.distance(p2);
    }

    public double getOrdenada() {
        return ordenada;
    }

    public void setOrdenada(double ordenada) {
        this.ordenada = ordenada;
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

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }
}
