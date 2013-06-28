/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Test.java
 *
 * Created on 14-ago-2010, 19:30:39
 */

package vistas.Prueba;
import Utiles.ATimerTask;
import vistas.*;
import Utiles.MyJPanel;
import Utiles.MyMJPanel;
import Utiles.MyTimerTask;
import clases.prueba.Configuracion;
import clases.prueba.Prueba;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


/**
 *
 * @author Casa
 */
public class Test extends javax.swing.JDialog {

    private TimerTask task;
    private Configuracion configuracion;
    private Principal parent;
    private int duracion;
    private int total;
    Timer ti;
    /** Creates new form Test */
    public Test(Principal parent, boolean modal, Prueba prueba, Configuracion conf, boolean auto, boolean pract) {
        super(parent, modal);
        this.configuracion=conf;
        this.parent = parent;
        duracion=parent.conf_avanzada.getT_interestimulo();
        total=parent.conf_avanzada.getDuracion();
        initComponents();
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        ((MyJPanel)jPanel1).Inicializar();
        ((MyJPanel)jPanel2).Inicializar();

        if (auto) {
            task = new ATimerTask(prueba, configuracion, (MyJPanel)jPanel1, (MyJPanel)jPanel2, (MyMJPanel)jPanel3, this, pract);
        }
        else{
            task = new MyTimerTask(prueba, configuracion, (MyJPanel)jPanel1, (MyJPanel)jPanel2, (MyMJPanel)jPanel3, this);
        }
       ti = new Timer();
       try{
       ti.scheduleAtFixedRate(task, 0, 1);
       }
       catch(Exception e){
            Errores er = new Errores(parent, true, e.getMessage());
            er.setVisible(true);
       }
       
        KeyListener keyPress = new KeyListener() {

            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void keyPressed(KeyEvent e) {

                if(task instanceof MyTimerTask)
                {
                   if( ((MyTimerTask)task).isCantPress())//Si lo aprieta
                   {
                       int k=e.getKeyCode();
                       ((MyTimerTask)task).getEnsayo().setKey(k);
                        if(((MyTimerTask)task).getEnsayo().getP_estimulo() == 0 && !((MyTimerTask)task).getEnsayo().getConfiguracion().isControl()){
                            ((MyTimerTask)task).getEnsayo().setError(true);
                            ((MyTimerTask)task).getEnsayo().setDescripcion("No hubo estímulo");
                        }
                        else if(((MyTimerTask)task).getEnsayo().getP_estimulo()>0)
                        {
                          ((MyTimerTask)task).getEnsayo().setT_respuesta(((MyTimerTask)task).getT_total()-(duracion/2));
                        }

                   }
                }
                else
                {
                     if(((ATimerTask)task).isCantPress())//Si lo aprieta
                     {
                        int k=e.getKeyCode();
                        ((ATimerTask)task).getEnsayo().setKey(k);
                        if(((ATimerTask)task).getEnsayo().getP_estimulo() == 0 && !((ATimerTask)task).getEnsayo().getConfiguracion().isControl()){
                            ((ATimerTask)task).getEnsayo().setError(true);
                            ((ATimerTask)task).getEnsayo().setDescripcion("No hubo estímulo");
                        }                           
                        else if(((ATimerTask)task).getEnsayo().getP_estimulo()>0)
                        {
                          ((ATimerTask)task).getEnsayo().setT_respuesta(((ATimerTask)task).getT_total()-(duracion/2));
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
   

    public Principal getParet()
    {
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
                   case 1:break;
               }
            }else{
               parent.pacientes.paciente_Pos(pos).setPrueba(prueba);
               parent.pacientes.SaveObject("datos.bin");
               parent.Mod_Tabla();
            }
        }
        catch(Exception e){
            Errores err = new Errores(parent, true, e.getMessage());
            err.setVisible(true);
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new MyJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel2 = new MyJPanel(configuracion.getDensidad(), configuracion.getCantidad());
        jPanel3 = new MyMJPanel(Color.RED);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   



    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Test dialog = new Test(new Principal(), true, new Prueba(), new Configuracion(), true, false);
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
    // End of variables declaration//GEN-END:variables

}
