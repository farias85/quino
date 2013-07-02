package quino.util;


import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Charlee
 */
public class Grafica {

    public void PintarPastel(Graphics g,int radio,int posx,int posy,int porcInicial,int porcFinal,String leyenda1, String leyenda2) throws Exception
    {
          if(porcFinal + porcInicial == 100){

          g.setColor(Color.RED);
          g.fillArc(posx, posy, radio, radio, 0, (int)(porcInicial* 3.6));
          g.setColor(Color.BLUE);
          g.fillArc(posx, posy, radio, radio, (int)(porcInicial* 3.6),((int)(porcFinal * 3.6)));

          /*g.setColor(Color.BLACK);
          g.drawArc(posx, posy, radio, radio, 0, (int)(porcInicial* 3.6));
          g.drawArc(posx, posy, radio, radio, (int)(porcInicial* 3.6),((int)(porcFinal * 3.6)));*/


          //Leyenda
          g.setColor(Color.RED);
          g.fillRect(posx , posy + radio + 10, 10,10);

          g.setColor(Color.BLACK);
          g.drawString(leyenda1, posx + 20, posy + radio + 20);

          g.setColor(Color.BLUE);
          g.fillRect(posx , posy + radio + 30, 10,10);

          g.setColor(Color.BLACK);
          g.drawString(leyenda2, posx + 20, posy + radio + 40);


          }
          else{

              throw new Exception("La suma de los porcientos es diferente de 100");
          }
       
    }

}
