/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Central_Test.java
 *
 * Created on 07-nov-2010, 15:27:47
 */

package vistas.Prueba;

import Utiles.Timers.AutoFovealTimer;
import Utiles.Timers.ManualFovealTimer;
import Utiles.CentralJPanel;
import Utiles.QuinoJPanel;
import clases.prueba.Configuracion;
import clases.prueba.Prueba;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import vistas.ErrorDialog;
import vistas.PrincipalView;
/**
 *
 * @author Davisito
 */
public class FovealTestView extends javax.swing.JDialog {

    /** Creates new form Central_Test */
    private TimerTask task;
    private Configuracion configuracion;
    private PrincipalView parent;
    private int duracion;
    private int total;
    private boolean pract;
    Timer ti;
    
    public FovealTestView(PrincipalView parent, boolean modal, Prueba prueba, Configuracion conf, boolean auto, boolean pract) {
        super(parent, modal);
        this.parent = parent;
        this.configuracion = conf;
        duracion = parent.conf_avanzada.getT_interestimulo();
        total = parent.conf_avanzada.getDuracion();
        this.pract = pract;
        initComponents();
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        ((QuinoJPanel)jPanel1).Inicializar();
        ((QuinoJPanel)jPanel2).Inicializar();
        ((QuinoJPanel)jPanel3).Inicializar();
        ((QuinoJPanel)jPanel4).Inicializar();
        ((QuinoJPanel)jPanel5).Inicializar();
        ((QuinoJPanel)jPanel6).Inicializar();
        ((QuinoJPanel)jPanel7).Inicializar();
        ((QuinoJPanel)jPanel8).Inicializar();

         if (auto) {
            task = new AutoFovealTimer(prueba, configuracion, (QuinoJPanel)jPanel1, (QuinoJPanel)jPanel2, (QuinoJPanel)jPanel3, (QuinoJPanel)jPanel4, (QuinoJPanel)jPanel5, (QuinoJPanel)jPanel6, (QuinoJPanel)jPanel7, (QuinoJPanel)jPanel8,(CentralJPanel)jPanel9, this, pract);
        }
        else{
            task = new ManualFovealTimer(prueba, configuracion, (QuinoJPanel)jPanel1, (QuinoJPanel)jPanel2, (QuinoJPanel)jPanel3, (QuinoJPanel)jPanel4, (QuinoJPanel)jPanel5, (QuinoJPanel)jPanel6, (QuinoJPanel)jPanel7, (QuinoJPanel)jPanel8,(CentralJPanel)jPanel9, this);
        }
       ti = new Timer();
       ti.scheduleAtFixedRate(task, 0, 1);

               KeyListener keyPress = new KeyListener() {

            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void keyPressed(KeyEvent e) {

                if(task instanceof ManualFovealTimer)
                {
                   if( ((ManualFovealTimer)task).isCantPress())//Si lo aprieta
                   {
                       int k=e.getKeyCode();
                       ((ManualFovealTimer)task).getEnsayo().setKey(k);
                        if(((ManualFovealTimer)task).getEnsayo().getP_estimulo() == 0 && !((ManualFovealTimer)task).getEnsayo().getConfiguracion().isControl()){
                          ((ManualFovealTimer)task).getEnsayo().setError(true);
                          ((ManualFovealTimer)task).getEnsayo().setDescripcion("No hubo estímulo");
                        }
                            
                        else if(((ManualFovealTimer)task).getEnsayo().getP_estimulo()>0)
                        {
                          ((ManualFovealTimer)task).getEnsayo().setT_respuesta(((ManualFovealTimer)task).getT_total()-(duracion/2));
                        }

                   }
                }
                else
                {
                     if(((AutoFovealTimer)task).isCantPress())//Si lo aprieta
                     {
                         int k=e.getKeyCode();
                       ((AutoFovealTimer)task).getEnsayo().setKey(k);
                        if(((AutoFovealTimer)task).getEnsayo().getP_estimulo() == 0 && !((AutoFovealTimer)task).getEnsayo().getConfiguracion().isControl()){
                          ((AutoFovealTimer)task).getEnsayo().setError(true);
                          ((AutoFovealTimer)task).getEnsayo().setDescripcion("No hubo estímulo");
                        }
                        else if(((AutoFovealTimer)task).getEnsayo().getP_estimulo()>0)
                        {
                          ((AutoFovealTimer)task).getEnsayo().setT_respuesta(((AutoFovealTimer)task).getT_total()-(duracion/2));
                        }

                      }
                }

            }

            public void keyReleased(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        this.addKeyListener(keyPress);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                       ti.cancel();
                       setVisible(false);
                       dispose();
                    }
                });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    public PrincipalView GetParet(){
        return parent;
    }
    public void GuardarPrueba(Prueba prueba){
         int option;
        try{
            int pos = parent.getSel_paciente();
            if(parent.pacientes.paciente_Pos(pos).Prueba()!=null){
               option = JOptionPane.showConfirmDialog(this, "¿Desea sobreescribir la prueba realizada?", "Advertencia", JOptionPane.YES_NO_OPTION);
               switch(option){
                   case 0:{
                        parent.pacientes.paciente_Pos(pos).setPrueba(prueba);
                        parent.pacientes.SaveObject("datos.bin");
                        parent.Mod_Tabla();
                   }break;
                   case 1:break;}
            }else{

                        parent.pacientes.paciente_Pos(pos).setFobeal(prueba);
                        parent.pacientes.SaveObject("datos.bin");
                        parent.Mod_Tabla();
                  }
        }
        catch(Exception e){
            ErrorDialog err = new ErrorDialog(parent, true, e.getMessage());
            err.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new Utiles.CentralJPanel(Color.RED);
        jPanel2 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel7 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel4 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel5 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel1 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel6 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel8 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel3 = new Utiles.QuinoJPanel(configuracion.getDensidad(), configuracion.getCantidad());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 61, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(251, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(195, 195, 195))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FovealTestView dialog = new FovealTestView(new PrincipalView(), true, new Prueba(), new Configuracion(), true, false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

}
