/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PerifericaTestView.java
 *
 * Created on 14-ago-2010, 19:30:39
 */
package quino.view.test2nd;

import quino.view.main.*;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import quino.clases.config.ConfigEnsayoShapeDetect;
import quino.util.JPanelShape;
import quino.util.timer.ShapeTimer;

/**
 *
 * @author Casa
 */
public class ShapeDetectTestView extends javax.swing.JDialog {

    private PrincipalView parent;
    private ConfigEnsayoShapeDetect conf;

    public ShapeDetectTestView() {
    }

    public ShapeDetectTestView(PrincipalView parent, boolean modal, boolean practica) {
        super(parent, modal);

        this.parent = parent;
        if (parent.getConf() instanceof ConfigEnsayoShapeDetect) {
            conf = ((ConfigEnsayoShapeDetect) parent.getConf());
        } else {
            System.err.println("La configuracion no es de tipo ConfigEnsayoFormaAB en la clase ShapeDetectTestView");
        }

        initComponents();
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.BLACK);

        TimerTask task = new ShapeTimer(parent.getPrueba(),
                (JPanelShape) jPanel1, (JPanelShape) jPanel2,
                this, practica);

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

        jPanel1 = new quino.util.JPanelShape(conf.getDensidad());
        jPanel2 = new quino.util.JPanelShape(conf.getDensidad());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 497, Short.MAX_VALUE)
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
            .addGap(0, 491, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 702, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ShapeDetectTestView dialog = new ShapeDetectTestView();
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
    // End of variables declaration//GEN-END:variables
}