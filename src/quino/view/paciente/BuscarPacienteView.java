/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 24/06/2013.
 */
package quino.view.paciente;

import quino.clases.config.ConfigApp;
import quino.view.main.TipoPruebaView;
import quino.view.main.ErrorDialog;
import quino.view.main.PrincipalView;

public class BuscarPacienteView extends javax.swing.JDialog {

    private PrincipalView parent;

    public BuscarPacienteView() {
    }

    /**
     * 
     * @param parent
     * @param modal 
     */
    public BuscarPacienteView(PrincipalView parent, boolean modal) {
        super(parent, modal);

        initComponents();
        setLocationRelativeTo(null);
        this.parent = parent;

        sexo.setVisible(false);
        cambios.setVisible(false);
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
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        historia = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        CI = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        edad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox();
        eliminar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        ficha = new javax.swing.JButton();
        cambios = new javax.swing.JButton();
        escolaridad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        prueba = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Paciente");
        setIconImage(null);
        setIconImages(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Introduzca el número de la Historia Clínica del Paciente");
        jLabel1.setToolTipText("");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Historia Clínica");

        historia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        CI.setEditable(false);
        CI.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CI.setBorder(null);

        nombre.setEditable(false);
        nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombre.setBorder(null);

        edad.setEditable(false);
        edad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        edad.setBorder(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombre y Apellidos");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Carné de Identidad");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Edad");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Sexo");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.setBorder(null);

        sexo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        sexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sexoItemStateChanged(evt);
            }
        });
        sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoActionPerformed(evt);
            }
        });

        eliminar.setFont(new java.awt.Font("Tahoma", 1, 12));
        eliminar.setText("Eliminar");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        modificar.setFont(new java.awt.Font("Tahoma", 1, 12));
        modificar.setText("Modificar");
        modificar.setEnabled(false);
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        ficha.setFont(new java.awt.Font("Tahoma", 1, 12));
        ficha.setText("Mostrar Ficha");
        ficha.setEnabled(false);
        ficha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fichaActionPerformed(evt);
            }
        });

        cambios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cambios.setText("Guardar Cambios");
        cambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiosActionPerformed(evt);
            }
        });

        escolaridad.setEditable(false);
        escolaridad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        escolaridad.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Escolaridad");

        prueba.setFont(new java.awt.Font("Tahoma", 1, 12));
        prueba.setText("Realizar Prueba");
        prueba.setEnabled(false);
        prueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pruebaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ficha)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prueba))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(historia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(escolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CI, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cambios))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(historia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(escolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(cambios)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ficha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_sexoActionPerformed

    private void cambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiosActionPerformed
        // TODO add your handling code here:
        try {
            String hist = historia.getText();
            String fich = parent.getPacienteActual().getFicha();
            String nombre1 = this.nombre.getText();
            long ci = IsCi(CI.getText());
            String esco = escolaridad.getText();
            int edad1 = Integer.parseInt(this.edad.getText());
            String sexo1 = String.valueOf(this.sexo.getSelectedItem());

            parent.getPacienteActual().setNombre(nombre1);
            parent.getPacienteActual().setEdad(edad1);
            parent.getPacienteActual().setSexo(sexo1);
            parent.getPacienteActual().setEscolaridad(esco);
            parent.getPacienteActual().setHistoria(hist);
            parent.getPacienteActual().setCi(ci);
            parent.getPacienteActual().setFicha(fich);

            ErrorDialog er = new ErrorDialog(parent, true, "Cambos realizados exitosamente");
            er.setVisible(true);
            
            parent.modificarTableModel();
            parent.getRegistro().salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);
        } catch (Exception e) {
            ErrorDialog err = new ErrorDialog(parent, true, e.getMessage());
            err.setVisible(true);
        }
}//GEN-LAST:event_cambiosActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
        nombre.setEditable(true);
        CI.setEditable(true);
        edad.setEditable(true);
        escolaridad.setEditable(true);
        sexo.setVisible(true);
        cambios.setVisible(true);
    }//GEN-LAST:event_modificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            parent.setPacienteActual(parent.getRegistro().buscarPaciente(historia.getText()));

            nombre.setText(parent.getPacienteActual().getNombre());
            CI.setText(String.valueOf(parent.getPacienteActual().getCi()));
            escolaridad.setText(parent.getPacienteActual().getEscolaridad());
            edad.setText(String.valueOf(parent.getPacienteActual().getEdad()));
            jTextField5.setText(parent.getPacienteActual().getSexo());

            eliminar.setEnabled(true);
            modificar.setEnabled(true);
            ficha.setEnabled(true);
            prueba.setEnabled(true);
        } catch (Exception e) {
            eliminar.setEnabled(false);
            modificar.setEnabled(false);
            ficha.setEnabled(false);
            prueba.setEnabled(false);
            ErrorDialog err = new ErrorDialog(parent, true, e.getMessage());
            err.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public boolean Numero(String valor) {

        if (valor.length() != 0) {
            for (int i = 0; i < valor.length(); i++) {
                int ASCII = (int) valor.charAt(i);
                if (ASCII < 48 || ASCII > 57) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }

    }

    public long IsCi(String ci) throws Exception {
        int[] DaysByMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (Numero(ci)) //Si todos sus elementos son números
        {
            if (ci.length() == 11) //Si contiene 11 dígitos
            {

                int day = new Integer(String.valueOf(ci.charAt(4)) + String.valueOf(ci.charAt(5)));
                int month = new Integer(String.valueOf(ci.charAt(2)) + String.valueOf(ci.charAt(3)));
                int year = Integer.parseInt(String.valueOf(ci.charAt(0)) + String.valueOf(ci.charAt(1)));

                if (month >= 1 && month <= 12) //Si el mes está comprendido entre 1 y 12
                {
                    if (day >= 1 && day <= DaysByMonths[month - 1]) //Si el mes específico contiene la cantidad de dias necesarios
                    {
                        return Long.parseLong(ci);
                    } else {
                        throw new Exception("Error en la cantidad de dias, debe estar en el rango [01-" + DaysByMonths[month - 1] + "]");
                    }
                } else {
                    throw new Exception("Error en el mes, debe estar en el rango [01-12]");
                }


            } else {
                throw new Exception("El carnet debe contener 11 dígitos");
            }

        } else {
            throw new Exception("El carnet debe contener solamante números");
        }
    }
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        try {
            String hist = this.historia.getText();
            parent.getRegistro().eliminarXHistoria(hist);
            ErrorDialog err = new ErrorDialog(parent, true, "El paciente ha sido eliminado satisfactoriamente");
            err.setVisible(true);
            historia.setText("");
            nombre.setText("");
            CI.setText("");
            escolaridad.setText("");
            edad.setText("");
            jTextField5.setText("");
            nombre.setEditable(false);
            CI.setEditable(false);
            escolaridad.setEditable(false);
            edad.setEditable(false);
            jTextField5.setEditable(false);

            parent.modificarTableModel();
            parent.getRegistro().salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);

        } catch (Exception e) {
            ErrorDialog err = new ErrorDialog(parent, true, e.getMessage());
            err.setVisible(true);
        }

    }//GEN-LAST:event_eliminarActionPerformed

    private void fichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fichaActionPerformed
        // TODO add your handling code here:
        try {
            parent.setPacienteActual(parent.getRegistro().buscarPaciente(historia.getText()));
            FichaPacienteView fich = new FichaPacienteView(parent, true);
            fich.setVisible(true);
        } catch (Exception e) {
            ErrorDialog err = new ErrorDialog(parent, true, e.getMessage());
            err.setVisible(true);
        }
    }//GEN-LAST:event_fichaActionPerformed

    private void sexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sexoItemStateChanged
        // TODO add your handling code here:
        jTextField5.setText(String.valueOf(sexo.getSelectedItem()));
    }//GEN-LAST:event_sexoItemStateChanged

    private void pruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pruebaActionPerformed
        // TODO add your handling code here:
        TipoPruebaView rp = new TipoPruebaView(parent, true);
        rp.setVisible(true);
        setVisible(false);
        dispose();
}//GEN-LAST:event_pruebaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                BuscarPacienteView dialog = new BuscarPacienteView();
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
    private javax.swing.JTextField CI;
    private javax.swing.JButton cambios;
    private javax.swing.JTextField edad;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField escolaridad;
    private javax.swing.JButton ficha;
    private javax.swing.JTextField historia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton modificar;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton prueba;
    private javax.swing.JComboBox sexo;
    // End of variables declaration//GEN-END:variables
}
