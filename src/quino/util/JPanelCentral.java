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
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Representa al panel del centro de las vistas de prueba foveal y periférica
 */
public class JPanelCentral extends JPanel {

    private Color color;

    public JPanelCentral(Color color) {
        this.color = color;
    }

    /**
     * Metodo para pintar el cirulo verde en el centro del panel
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = image.getGraphics();

        gr.setColor(color);
        gr.fillOval((this.getWidth() / 2) - 15, (this.getHeight() / 2) - 15, 30, 30);
        
        g.drawImage(image, 0, 0, this);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
