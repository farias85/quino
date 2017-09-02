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

/**
 * Representa a la grafica para mostrar los resultados de las pruebas
 */
public class Grafica {

    public void PintarPastel(Graphics g, int radio, int posx, int posy, int porcInicial, int porcFinal, String leyenda1, String leyenda2) throws Exception {
        if (porcFinal + porcInicial == 100) {

            g.setColor(Color.RED);
            g.fillArc(posx, posy, radio, radio, 0, (int) (porcInicial * 3.6));
            g.setColor(Color.BLUE);
            g.fillArc(posx, posy, radio, radio, (int) (porcInicial * 3.6), ((int) (porcFinal * 3.6)));

            //Leyenda
            g.setColor(Color.RED);
            g.fillRect(posx, posy + radio + 10, 10, 10);

            g.setColor(Color.BLACK);
            g.drawString(leyenda1, posx + 20, posy + radio + 20);

            g.setColor(Color.BLUE);
            g.fillRect(posx, posy + radio + 30, 10, 10);

            g.setColor(Color.BLACK);
            g.drawString(leyenda2, posx + 20, posy + radio + 40);

        } else {
            throw new Exception("La suma de los porcientos es diferente de 100");
        }

    }
}
