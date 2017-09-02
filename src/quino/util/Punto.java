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
import java.util.Random;

/**
 * Representa un punto de cada panel
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

    /**
     * Constructor para valores enteros de cordenada de punto. El punto se crea
     * exactamente con los valores de los parametros
     * @param x
     * @param y
     */
    public Punto(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    /**
     * Mueve un punto de una posición a otra en dependencia de la direción
     * @param direccion La dirección a mover el punto
     */
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
