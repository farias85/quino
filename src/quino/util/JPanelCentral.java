/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Representa al panel del centro de las vistas de prueba foveal y periférica
 * @author Felipe Rodriguez Arias
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