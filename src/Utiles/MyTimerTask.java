/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utiles;

import clases.prueba.Configuracion;
import clases.prueba.Ensayo;
import clases.prueba.Prueba;
import clases.prueba.Results;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.TimerTask;
import vistas.Prueba.Test;

/**
 *
 * @author Casa
 */
public class MyTimerTask extends TimerTask{
    private Prueba prueba;
    private Configuracion configuracion;
    private MyJPanel panel1;
    private MyJPanel panel2;
    private MyMJPanel panel3;
    private int t_total=0;
    private boolean cantPress=false;
    private Ensayo ensayo;
    private int num_ensayo=0;
    Test test;
    private Punto p1;
    private Punto p2;
    private int duracion;
    private int t_interestimulo;
    private int p_estimulo;
    private double angulo;
   
    public MyTimerTask() {
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

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public Ensayo getEnsayo() {
        return ensayo;
    }

    public void setEnsayo(Ensayo ensayo) {
        this.ensayo = ensayo;
    }

    public MyJPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(MyJPanel panel1) {
        this.panel1 = panel1;
    }

    public MyJPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(MyJPanel panel2) {
        this.panel2 = panel2;
    }

    public MyMJPanel getPanel3() {
        return panel3;
    }

    public void setPanel3(MyMJPanel panel3) {
        this.panel3 = panel3;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public int getT_total() {
        return t_total;
    }

    public void setT_total(int t_total) {
        this.t_total = t_total;
    }


    public MyTimerTask(Prueba prueba, Configuracion configuracion,  MyJPanel panel1, MyJPanel panel2, MyMJPanel panel3, Test test) {
        this.prueba = prueba;
        this.configuracion = configuracion;        
        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.test=test;
        duracion = prueba.getConf_avanzada().getDuracion();
        t_interestimulo = prueba.getConf_avanzada().getT_interestimulo();
       
    }   


    @Override
    public void run() {
       if (prueba.getCant_ensayos()>0){
            if(t_total==0)
            {                
                ensayo = new Ensayo(configuracion);
                 Aleatorio al = new Aleatorio();
                 p_estimulo = al.nextInt(0, 2);               
            }           
            if(t_total<t_interestimulo/4){
              panel1.repaint();
              panel2.repaint();              
              t_total++;
            }
            if (t_total<t_interestimulo/2) {
              //panel1.repaint();
              //panel2.repaint();
              panel3.setColor(Color.RED);
              panel3.repaint();
              t_total++;
            } else if(t_total<=(t_interestimulo/2)+configuracion.getTiempo_movimiento()){
                 panel3.setColor(Color.GREEN);
              panel3.repaint();
                if (t_total == ((t_interestimulo/2)+configuracion.getTiempo_movimiento())/2 || (t_total== (t_interestimulo/2)+configuracion.getTiempo_movimiento()-1))
                {
                    switch(p_estimulo){
                        case 0:{
                            panel1.repaint();
                            panel2.repaint();
                        }break;
                        case 1:{
                             panel1.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                             panel1.repaint();
                        }break;
                        case 2:{
                             panel2.Mover(configuracion.getDireccion(), configuracion.isAsincronico());
                             panel2.repaint();
                        }break;
                    }
                    cantPress = true;
                }
                t_total++;
            }else if(t_total<duracion){
                if (t_total == duracion-(t_interestimulo/4)) {
                    panel3.setColor(Color.RED);
                    panel3.repaint();
                }
                cantPress = true;
                t_total++;
            }
            else{                
                
                if(configuracion.isControl()&& ensayo.getP_estimulo()>0 && ensayo.getKey()!=0){
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
                     if(p_estimulo == 0 && !ensayo.isError())
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
                num_ensayo++;
                cantPress = false;
                if(/*ensayo.getT_respuesta()==0*/ ensayo.getKey()==0 && p_estimulo > 0 && !ensayo.isError())
                    {
                      ensayo.setError(true);
                      ensayo.setDescripcion("Omisión");
                    }
                t_total=0;
                prueba.decre_Ensayos();

                if(ensayo.getP_estimulo()>0){
                    if(ensayo.getP_estimulo()==1){
                        p2 = panel1.MidPunto(false);
                    }else
                        p2 = panel2.MidPunto(false);
                    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                    double x = d.getWidth()/2;
                    double y = d.getHeight()/2;
                    p1 = new Punto(x, y);
                    angulo = ensayo.CalcularAngulo(p1, p2);
                }

                int cantidad = configuracion.getCantidad();
                int velocidad = configuracion.getTiempo_movimiento();
                int t_respuesta= ensayo.getT_respuesta();
                int direccion = configuracion.getDireccion();
                int densidad = configuracion.getDensidad();
                boolean error = ensayo.isError();                
                boolean asincronico = configuracion.isAsincronico();
                String descrip = ensayo.getDescripcion();
                int key = ensayo.getKey();
                boolean control = configuracion.isControl();
                double velo = configuracion.CalcularVelocidad();
                Results result = new Results(velocidad, t_respuesta, direccion, densidad, cantidad, error, num_ensayo, asincronico, p_estimulo, descrip, key, control, velo, angulo);
                p_estimulo =0;
                prueba.add_Result(result);
            }
        } else {
           test.GuardarPrueba(prueba);
           vistas.Prueba.Result res = new vistas.Prueba.Result(test.getParet(), true, prueba);
           test.setVisible(false);
           res.setVisible(true);
           cancel();          
        }
    }

}
