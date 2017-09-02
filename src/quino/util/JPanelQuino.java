/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 02/07/2013.
 */
package quino.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Representa al panel donde se pintan los puntos
 *
 */
public class JPanelQuino extends JPanel {

    /**
     * Lista de puntos q serán dibujados en el panel
     */
    private ArrayList<Punto> puntos = new ArrayList<Punto>();
    /**
     * Cantidad de puntos a mover respecto a la densidad
     */
    private int cantidad;
    /**
     * Densidad de puntos que se dibujaran
     */
    private int densidad;

    public JPanelQuino(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
    }

    /**
     * Método para pintar el panel
     * @param g Objeto Graphics de la super clase
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < puntos.size(); i++) {
            g.setColor(Color.WHITE);
            puntos.get(i).Pintar(g);
        }
    }

    /**
     * Mueve en una direccion la cantidad de puntos asignada en la creación del jpanel
     * @param direccion La direccion del movimiento
     * @param desplazamientX El desplazamiento en el eje X respecto al borde lateral izquierdo de la pantalla
     * @param desplazamientoY El desplazamiento en el eje Y respecto al borde superior de la pantalla
     */
    public void moverEnDireccion(int direccion) {
        for (int i = 0; i < cantidad; i++) {
            Aleatorio dir = new Aleatorio();
            int pos = dir.nextInt(0, puntos.size() - 1);

            Punto p1 = new Punto(puntos.get(pos).getX(), puntos.get(pos).getY());
            puntos.get(pos).mover(direccion);
            Punto p2 = puntos.get(pos);
            actualizarAnguloPuntos(p1, p2);
        }
    }

    /**
     * Actualiza el angulo desplazado en cada punto q se ha movido
     * @param p1 Punto inicial o de partida
     * @param p2 Punto de llegada o final
     * @param desplazamientX El desplazamiento en el eje X respecto al borde lateral izquierdo de la pantalla
     * @param desplazamientoY El desplazamiento en el eje Y respecto al borde superior de la pantalla
     */
    public void actualizarAnguloPuntos(Punto p1, Punto p2) {
        double desplazamientX = getLocationOnScreen().getX();
        double desplazamientoY = getLocationOnScreen().getY();

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

    /**
     * Mueve asincronicamente los puntos del jpanel
     * @param desplazamientX El desplazamiento en el eje X respecto al borde lateral izquierdo de la pantalla
     * @param desplazamientoY El desplazamiento en el eje Y respecto al borde superior de la pantalla
     */
    public void moverAsincronico() {
        for (int i = 0; i < cantidad; i++) {
            Aleatorio dir = new Aleatorio();
            int x = dir.nextInt(1, 8);
            int pos = dir.nextInt(0, puntos.size() - 1);

            Punto p1 = new Punto(puntos.get(pos).getX(), puntos.get(pos).getY());
            puntos.get(pos).mover(x);
            Punto p2 = puntos.get(pos);
            actualizarAnguloPuntos(p1, p2);
        }
    }

    /**
     * Actualiza el contenido del panel a travez del método paint
     * @param g
     */
    @Override
    public void update(Graphics g) {
        super.update(g);
        Image image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = image.getGraphics();
        paint(gr);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Limpia el panel
     */
    public void clear() {
        puntos.clear();
    }

    /**
     * Reconfigura la cantidad y densidad de puntos a dibujar en el panel
     * @param densidad Densidad de puntos
     * @param cantidad Cantidad de puntos a mover
     */
    public void rellenar(int densidad, int cantidad) {
        this.cantidad = cantidad;
        this.densidad = densidad;
        puntos = new ArrayList<Punto>();
        for (int i = 0; i < this.densidad; i++) {
            Punto p = new Punto(this.getWidth(), this.getHeight());
            puntos.add(p);
        }
    }

    /**
     * Calcula el promedio de los angulos de los puntos q se movieron
     * @return El promedio de los angulos de los puntos
     */
    public double promedioAngulo() {
        int puntosMovidos = 0;
        double sumatoriaAngulos = 0;

        for (int i = 0; i < puntos.size(); i++) {
            if (puntos.get(i).getAngulo() != 0) {
                puntosMovidos++;
                sumatoriaAngulos += puntos.get(i).getAngulo();
            }
        }
        return Math.rint((sumatoriaAngulos / puntosMovidos) * 100) / 100;
    }
}
