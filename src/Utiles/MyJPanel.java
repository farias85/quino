/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 * @author Casa
 */
public class MyJPanel extends JPanel{
    ArrayList<Punto> puntos;
    int cantidad;
    int densidad;

    public MyJPanel(int densidad, int cantidad) {
        this.cantidad =cantidad;
     puntos = new ArrayList<Punto>();
     this.densidad = densidad;
    }
   public void Inicializar(){
        for (int i = 0; i < densidad; i++) {
           Aleatorio ran = new Aleatorio();
           Punto p = new Punto(this.getWidth(), this.getHeight());
           puntos.add(p);
        }
   }
    

    @Override
    public void paint(Graphics g) {
        super.paint(g);
       /*Image image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = image.getGraphics();
       for (int i = 0; i < puntos.size(); i++) {
            gr.setColor(Color.WHITE);
           puntos.get(i).Pintar(gr);
        }
        g.drawImage(image, 0, 0, this);*/

         for (int i = 0; i < puntos.size(); i++) {
            g.setColor(Color.WHITE);
           puntos.get(i).Pintar(g);
        }
    }
    public void Mover(int direccion, boolean asin){
        if (asin) {
            for (int i = 0; i < cantidad; i++) {
                Aleatorio dir = new Aleatorio();
                int x = dir.nextInt(1, 8);
                int pos = dir.nextInt(0, puntos.size()-1);
                puntos.get(pos).Mover(x);
            }
        } else {
            for (int i = 0; i < cantidad; i++) {
                 Aleatorio dir = new Aleatorio();
             int pos = dir.nextInt(0, puntos.size()-1);
                puntos.get(pos).Mover(direccion);
            }
        }         
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        Image image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = image.getGraphics();
        paint(gr);
        g.drawImage(image, 0, 0, this);
    }
    public void clear()
    {
        puntos.clear();
    }
    public void Rellenar(int densidad, int cantidad)
    {
        this.cantidad =cantidad;
        puntos = new ArrayList<Punto>();
        for (int i = 0; i < densidad; i++) {          
           Punto p = new Punto(this.getWidth(), this.getHeight());
           puntos.add(p);
        }
    }
    public Punto MidPunto(boolean  fob){
        if(fob)
            return puntos.get(cantidad/4);
        else
            return puntos.get(cantidad/2);
    }
}
