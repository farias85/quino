/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GaborTestView.java
 *
 * Created on 01-oct-2013, 15:20:35
 */

package quino.view.prueba2nd;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import quino.util.timer.GaborTimer;
import quino.view.main.PrincipalView;

/**
 *
 * @author farias
 */
public class GaborTestView extends javax.swing.JDialog {

    private PrincipalView parent;

    public GaborTestView() {
    }

    /** Creates new form GaborTestView */
    public GaborTestView(PrincipalView parent, boolean modal, boolean practica) {
        super(parent, modal);

        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);

        System.loadLibrary("opencv_java245");
        getContentPane().setBackground(Color.BLACK);

        TimerTask task = new GaborTimer(jLabel1);
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
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GaborTestView dialog = new GaborTestView();
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