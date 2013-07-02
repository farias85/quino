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
import clases.prueba.ConfiguracionAutomatica;
import clases.prueba.Ensayo;
import clases.prueba.Prueba;
import clases.prueba.Results;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.TimerTask;
import vistas.Prueba.PerifericaTestView;


/**
 *
 * @author Davisito
 */
public class AutoPerifericaTimer extends TimerTask{
    private Prueba prueba;
    private Configuracion configuracion;
    private QuinoJPanel panel1;
    private QuinoJPanel panel2;
    private CentralJPanel panel3;
    private int t_total=0;
    private boolean cantPress=false;
    private Ensayo ensayo;
    private int num_ensayo=0;
    boolean pract;
    PerifericaTestView test;
    private boolean control;
    private int duracion;
    private int t_interestimulo;
    private int p_estimulo;
    private Punto p1;
    private Punto p2;
    private double angulo;

    public AutoPerifericaTimer() {
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

    public QuinoJPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(QuinoJPanel panel1) {
        this.panel1 = panel1;
    }

    public QuinoJPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(QuinoJPanel panel2) {
        this.panel2 = panel2;
    }

    public CentralJPanel getPanel3() {
        return panel3;
    }

    public void setPanel3(CentralJPanel panel3) {
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


    public AutoPerifericaTimer(Prueba prueba, Configuracion configuracion,  QuinoJPanel panel1, QuinoJPanel panel2, CentralJPanel panel3, PerifericaTestView test, boolean pract) {
        this.prueba = prueba;
        this.configuracion = configuracion;
        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.test=test;
        duracion = prueba.getConf_avanzada().getDuracion();
        t_interestimulo = prueba.getConf_avanzada().getT_interestimulo();
        this.pract=pract;
        control= configuracion.isControl();
    }

 @Override
    public void run() {
       if (prueba.getCant_ensayos()>0){
            if(t_total==0)
            {                
                if(prueba.getResultados().size()>1){
                     configuracion= new ConfiguracionAutomatica(control);
                     panel3.setColor(Color.RED);
                     panel3.repaint();
                     panel1.clear();
                     panel2.clear();
                     panel1.Rellenar(configuracion.getDensidad(), configuracion.getCantidad());
                     panel2.Rellenar(configuracion.getDensidad(), configuracion.getCantidad());
                }
                 ensayo = new Ensayo(configuracion);
                 Aleatorio al = new Aleatorio();
                 p_estimulo = al.nextInt(0, 2);
                
            }
            if(t_total<(t_interestimulo/2)-10){
              panel3.setColor(Color.RED);
              panel3.repaint();
              panel1.repaint();
              panel2.repaint();
              t_total++;
            }            
             else if(t_total<=(t_interestimulo/2)+configuracion.getTiempo_movimiento()){
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
                
                if(control&& ensayo.getP_estimulo()>0 && ensayo.getKey()!=0){
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
                num_ensayo++;
                cantPress = false;
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
                double velo = configuracion.CalcularVelocidad();
                Results result = new Results(velocidad, t_respuesta, direccion, densidad, cantidad, error, num_ensayo, asincronico, p_estimulo, descrip, key, control, velo, angulo);
                p_estimulo =0;
                prueba.add_Result(result);
            }
        } else {
           if(!pract){
                test.GuardarPrueba(prueba);
           }
           vistas.Prueba.ResultView res = new vistas.Prueba.ResultView(test.getParet(), true, prueba);
           test.setVisible(false);
           res.setVisible(true);
           cancel();    
        }
    }
}
