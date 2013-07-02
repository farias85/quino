/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utiles.Timers;

import Utiles.Aleatorio;
import Utiles.QuinoJPanel;
import Utiles.CentralJPanel;
import Utiles.Punto;
import clases.prueba.Configuracion;
import clases.prueba.Ensayo;
import clases.prueba.Prueba;
import clases.prueba.Results;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.TimerTask;
import vistas.Prueba.FovealTestView;
;

/**
 *
 * @author Davisito
 */
public class ManualFovealTimer extends TimerTask{
    private Prueba prueba;
    private Configuracion configuracion;
    private QuinoJPanel panel1;
    private QuinoJPanel panel2;
    private QuinoJPanel panel3;
    private QuinoJPanel panel4;
    private QuinoJPanel panel5;
    private QuinoJPanel panel6;
    private QuinoJPanel panel7;
    private QuinoJPanel panel8;
    private CentralJPanel panel9;
    private int t_total=0;
    private boolean cantPress=false;
    private Ensayo ensayo;
    private int num_ensayo=0;
    FovealTestView test;
    private int duracion;
    private int t_interestimulo;
    private int p_estimulo;
    private double angulo;
    private Punto p1;
    private Punto p2;

    public ManualFovealTimer(Prueba prueba) {
        this.prueba = prueba;
    }

    public ManualFovealTimer(Prueba prueba, Configuracion configuracion, QuinoJPanel panel1, QuinoJPanel panel2, QuinoJPanel panel3, QuinoJPanel panel4, QuinoJPanel panel5, QuinoJPanel panel6, QuinoJPanel panel7, QuinoJPanel panel8, CentralJPanel panel9,FovealTestView test) {
        this.prueba = prueba;
        this.configuracion = configuracion;
        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.panel4 = panel4;
        this.panel5 = panel5;
        this.panel6 = panel6;
        this.panel7 = panel7;
        this.panel8 = panel8;
        this.panel9 = panel9;
        this.test = test;
        duracion = prueba.getConf_avanzada().getDuracion();
        t_interestimulo = prueba.getConf_avanzada().getT_interestimulo();
            
    }

    public boolean isCantPress() {
        return cantPress;
    }

    public void setCantPress(boolean cantPress) {
        this.cantPress = cantPress;
    }


    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Ensayo getEnsayo() {
        return ensayo;
    }

    public int getNum_ensayo() {
        return num_ensayo;
    }

    public int getP_estimulo() {
        return p_estimulo;
    }

    public QuinoJPanel getPanel1() {
        return panel1;
    }

    public QuinoJPanel getPanel2() {
        return panel2;
    }

    public QuinoJPanel getPanel3() {
        return panel3;
    }

    public QuinoJPanel getPanel4() {
        return panel4;
    }

    public QuinoJPanel getPanel5() {
        return panel5;
    }

    public QuinoJPanel getPanel6() {
        return panel6;
    }

    public QuinoJPanel getPanel7() {
        return panel7;
    }

    public QuinoJPanel getPanel8() {
        return panel8;
    }

    public CentralJPanel getPanel9() {
        return panel9;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public int getT_interestimulo() {
        return t_interestimulo;
    }

    public int getT_total() {
        return t_total;
    }



     @Override
    public void run() {
       if (prueba.getCant_ensayos()>0){
            if(t_total==0)
            {               
                ensayo = new Ensayo(configuracion);
                 Aleatorio al = new Aleatorio();
                 p_estimulo = al.nextInt(0, 8);
            }
            if(t_total<t_interestimulo/4){
                 panel9.setColor(Color.RED);
                 panel9.repaint();
                 panel1.repaint();
                 panel2.repaint();
                 panel3.repaint();
                 panel4.repaint();
                 panel5.repaint();
                 panel6.repaint();
                 panel7.repaint();
                 panel8.repaint();
                 t_total++;
            }
            if (t_total<t_interestimulo/2) {              
              panel9.setColor(Color.RED);
              panel9.repaint();
              t_total++;
            } else if(t_total<=(t_interestimulo/2)+configuracion.getTiempo_movimiento()){
                panel9.setColor(Color.GREEN);
              panel9.repaint();
                if (t_total == ((t_interestimulo/2)+configuracion.getTiempo_movimiento())/2 || (t_total== (t_interestimulo/2)+configuracion.getTiempo_movimiento()-1))
                {
                    switch(p_estimulo){
                        case 0:{
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 1:{
                            panel1.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 2:{
                            panel2.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 3:{
                            panel3.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 4:{
                            panel4.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 5:{
                            panel5.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 6:{
                            panel6.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 7:{
                            panel7.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                        case 8:{
                            panel8.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                            panel1.repaint();
                            panel2.repaint();
                            panel3.repaint();
                            panel4.repaint();
                            panel5.repaint();
                            panel6.repaint();
                            panel7.repaint();
                            panel8.repaint();
                        }break;
                    }
                    cantPress = true;
                }
                t_total++;
            }else if(t_total<duracion){
                if (t_total == duracion-(t_interestimulo/4)) {
                    panel9.setColor(Color.RED);
                    panel9.repaint();
                }
                cantPress = true;
                t_total++;
            }
            else{
                
                if(configuracion.isControl()&& (p_estimulo)>0 && ensayo.getKey()!=0){ // ensayo.getP_estimulo(), frank  7/10/2012
                    switch(configuracion.getDireccion()){
                        case 1:{
                              if (ensayo.getKey()!=104 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                        case 2:{
                              if (ensayo.getKey()!=98 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                        case 3:{
                              if (ensayo.getKey()!=102 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                        case 4:{
                              if (ensayo.getKey()!=100 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                        case 5:{
                              if (ensayo.getKey()!=105 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                        case 6:{
                              if (ensayo.getKey()!=103 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                        case 7:{
                              if (ensayo.getKey()!=99 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                        case 8:{
                              if (ensayo.getKey()!=97 && !ensayo.isError()) {
                                ensayo.setError(true);
                                ensayo.setDescripcion("Las direcciones no coinciden");
                            }
                        }break;
                    }
                     if(p_estimulo == 0&& !ensayo.isError())
                    {
                      ensayo.setError(true);
                      ensayo.setDescripcion("No hubo Estímulo");
                    }
                    
                }
                if(p_estimulo == 0 && !ensayo.isError() && ensayo.getKey()>0)
                    {
                      ensayo.setError(true);
                      ensayo.setDescripcion("No hubo Estímulo");
                    }
                if(/*ensayo.getT_respuesta()==0*/ensayo.getKey()==0 && p_estimulo > 0)
                    {
                      ensayo.setError(true);
                      ensayo.setDescripcion("Omisión");
                    }

                if(p_estimulo >0){ // ensayo.getP_estimulo(), frank  7/10/2012
                    switch(p_estimulo ){ // ensayo.getP_estimulo(), frank 7/10/2012
                        case 1:
                            p2=panel1.MidPunto(true);break;
                        case 2:
                            p2=panel2.MidPunto(true);break;
                        case 3:
                            p2=panel3.MidPunto(true);break;
                        case 4:
                            p2=panel4.MidPunto(true);break;
                        case 5:
                            p2=panel5.MidPunto(true);break;
                        case 6:
                            p2=panel6.MidPunto(true);break;
                        case 7:
                            p2=panel7.MidPunto(true);break;
                        case 8:
                            p2=panel8.MidPunto(true);break;
                    }
                    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                    double x = d.getWidth()/2;
                    double y = d.getHeight()/2;
                    p1 = new Punto(x, y);
                    angulo = ensayo.CalcularAngulo(p1, p2);
                }

                num_ensayo++;
                cantPress = false;
                t_total=0;
                prueba.decre_Ensayos();
                int cantidad = configuracion.getCantidad();
                int velocidad = configuracion.getTiempo_movimiento();
                int t_respuesta= ensayo.getT_respuesta();
                int direccion = configuracion.getDireccion();
                int densidad = (configuracion.getDensidad()*8)+configuracion.getResto();
                boolean error = ensayo.isError();
                boolean asincronico = configuracion.isAsincronico();
                String descrip = ensayo.getDescripcion();
                int key = ensayo.getKey();
                boolean control = configuracion.isControl();
                double velo= configuracion.CalcularVelocidad();
                Results result = new Results(velocidad, t_respuesta, direccion, densidad, cantidad, error, num_ensayo, asincronico, p_estimulo, descrip, key, control, velo, angulo);
                p_estimulo =0;
                prueba.add_Result(result);
            }
        } else {
           test.GuardarPrueba(prueba);
           vistas.Prueba.ResultView res = new vistas.Prueba.ResultView(test.GetParet(), true, prueba);
           test.setVisible(false);
           res.setVisible(true);
           cancel();
        }
    }

}
