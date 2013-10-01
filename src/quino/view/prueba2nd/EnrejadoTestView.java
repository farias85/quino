/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PerifericaTestView.java
 *
 * Created on 14-ago-2010, 19:30:39
 */
package quino.view.prueba2nd;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import quino.util.timer.EnrejadoTimer;
import quino.view.main.*;

/**
 *
 * @author Casa
 */
public class EnrejadoTestView extends javax.swing.JDialog {

    private PrincipalView parent;

    public EnrejadoTestView() {
    }

    public EnrejadoTestView(PrincipalView parent, boolean modal, boolean practica) {
        super(parent, modal);

        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);

        System.loadLibrary("opencv_java245");
        getContentPane().setBackground(Color.BLACK);

        TimerTask task = new EnrejadoTimer(jLabel1);
        Timer ti = new Timer();
        ti.scheduleAtFixedRate(task, 0, 1);
    }

    public PrincipalView getParentView() {
        return parent;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setIconImage(null);
        setIconImages(null);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                EnrejadoTestView dialog = new EnrejadoTestView();
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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
