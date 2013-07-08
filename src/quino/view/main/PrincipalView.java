/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrincipalView.java
 *
 * Created on 24-jul-2010, 10:23:06
 */
package quino.view.main;

import java.awt.Color;
import quino.clases.model.Paciente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import quino.view.prueba.ManualConfigView;
import quino.view.prueba.AutoConfigView;
import quino.clases.model.Registro;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoAuto;
import quino.clases.model.Prueba;
import java.io.IOException;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import quino.view.paciente.BuscarPacienteView;
import quino.view.paciente.FichaPacienteView;
import quino.view.paciente.ModificarPacienteView;
import quino.view.paciente.NuevoPacienteView;
import quino.view.prueba.ResultView;
import quino.view.prueba.PerifericaTestView;
import java.io.File;
import javax.swing.filechooser.*;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.config.ConfigApp;
import quino.clases.model.PruebaFoveal;
import quino.clases.model.PruebaPeriferica;
import quino.util.QuinoTableModel;
import quino.util.QuinoTools;
import quino.util.ScreenSplash;
import quino.util.report.AbstractInformeExcel;
import quino.util.report.InformeCampoVisual;
import quino.util.report.InformeParametrosXEnsayo;
import quino.view.prueba.FovealTestView;

/**
 *
 * @author davisito
 */
public class PrincipalView extends javax.swing.JFrame {
    //public clases.prueba.Tester tester;

    private ConfigEnsayo conf;
    private Prueba prueba;
    private Registro registro;
    private Paciente pacienteActual;

    public ConfigEnsayo getConf() {
        return conf;
    }

    public void setConf(ConfigEnsayo conf) {
        this.conf = conf;
    }

    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public void setPacienteActual(Paciente pacienteActual) {
        this.pacienteActual = pacienteActual;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public Registro getRegistro() {
        return registro;
    }

    /** Creates new form PrincipalView */
    public PrincipalView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        this.setLocationRelativeTo(null);
        jMenuItem16.setEnabled(false);
        jMenuItem17.setEnabled(false);

        try {
            registro = new Registro();
            registro = Registro.cargarRegistro(ConfigApp.REGISTRO_FILE_NAME);
            QuinoTools.cargarConfiguracion();

            modificarTableModel();

            if (registro.getPacientes().size() == 0) {
                b_busc.setEnabled(false);
                b_del.setEnabled(false);
                b_fich.setEnabled(false);
                b_mod.setEnabled(false);
                b_prueba.setEnabled(false);
                jMenuItem16.setEnabled(false);
                jMenuItem17.setEnabled(false);
                throw new Exception("No existen Pacientes registrados");
            }
        } catch (Exception e) {
            if (e instanceof IOException) {
                ErrorDialog err = new ErrorDialog(this, true, "No existen Pacientes registrados");
                err.setVisible(true);
            } else {
                ErrorDialog err = new ErrorDialog(this, true, e.getMessage());
                err.setVisible(true);
                registro = new Registro();
            }
        }

        jMenu5.add(jMenuItem1);
        jMenu5.add(jMenuItem2);

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                    habilitarComponentes(false);
                } else {
                    habilitarComponentes(true);
                    int sel_paciente = lsm.getMinSelectionIndex();
                    pacienteActual = registro.getPacientes().get(sel_paciente);
                    jTable1.setComponentPopupMenu(menu);
                }
            }
        });

        jTable1.setAutoCreateRowSorter(true);

        b_add.setToolTipText("Registrar nuevo Paciente");
        b_del.setToolTipText("Eliminar el Paciente seleccionado");
        b_busc.setToolTipText("Buscar un Paciente");
        b_fich.setToolTipText("Mostrar la Ficha del Paciente seleccionado");
        b_mod.setToolTipText("Modificar el Paciente seleccionado");
        b_prueba.setToolTipText("Realizar Prueba al Paciente seleccionado");
        jMenuItem4.setText("Prueba Foveal");
    }

    private void habilitarComponentes(boolean habilitar) {
        jMenu5.setEnabled(habilitar);
        jMenuItem6.setEnabled(habilitar);
        jMenuItem7.setEnabled(habilitar);
        jMenuItem8.setEnabled(habilitar);
        jMenuItem9.setEnabled(habilitar);
        b_del.setEnabled(habilitar);
        b_fich.setEnabled(habilitar);
        b_mod.setEnabled(habilitar);
        b_prueba.setEnabled(habilitar);
        jMenuItem16.setEnabled(habilitar);
        jMenuItem17.setEnabled(habilitar);
    }

    public final void modificarTableModel() {
        DefaultTableModel tm = new QuinoTableModel(registro);

        if (registro.getPacientes().size() == 0) {
            b_busc.setEnabled(false);
        } else {
            b_busc.setEnabled(true);
        }

        jTable1.setModel(tm);
        jTable1.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 11));
        jTable1.setGridColor(Color.BLUE);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(0);

        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
    }

    public boolean itemSelected() {
        for (int i = 0; i < registro.getPacientes().size(); i++) {
            if (jTable1.isRowSelected(i)) {
                return true;
            }
        }
        return false;
    }

    public void ActivarPractica() {
        jMenuItem16.setEnabled(true);
    }

    private void eliminarPaciente() {
        ConfirmDialog confirmDialog = new ConfirmDialog(this, true);
        confirmDialog.setVisible(true);

        int respuesta = confirmDialog.getReturnStatus();

        if (respuesta == 1) {
            try {
                String hist = pacienteActual.getHistoria();
                registro.eliminarXHistoria(hist);

                modificarTableModel();
                registro.salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);
            } catch (Exception e) {
                ErrorDialog err = new ErrorDialog(this, true, e.getMessage());
                err.setVisible(true);
            }
        } else {
            modificarTableModel();
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

        menu = new javax.swing.JPopupMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        Resultados = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        b_add = new javax.swing.JButton();
        b_del = new javax.swing.JButton();
        b_mod = new javax.swing.JButton();
        b_fich = new javax.swing.JButton();
        b_busc = new javax.swing.JButton();
        b_prueba = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();

        jMenu5.setText("Realizar Prueba");
        jMenu5.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem1.setText("Configuración Manual");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem2.setText("Configuración Automática");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        menu.add(jMenu5);

        jMenuItem6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem6.setText("Modificar Datos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem7.setText("Mostrar Ficha");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem8.setText("Eliminar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem8);

        Resultados.setText("Mostrar Resultados");
        Resultados.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem9.setText("Prueba Periférica");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem9);

        jMenuItem14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem14.setText("Prueba Foveal");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem14);

        menu.add(Resultados);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setResizable(false);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 12));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Historia Clínica", "Nombre y Apellidos", "Carné de Identidad", "Edad", "Escolaridad", "Sexo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jTable1.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);

        b_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/add_paciente.gif"))); // NOI18N
        b_add.setBorder(null);
        b_add.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/add_paciente_dis.gif"))); // NOI18N
        b_add.setPreferredSize(new java.awt.Dimension(44, 44));
        b_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_addActionPerformed(evt);
            }
        });

        b_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/del_paciente.gif"))); // NOI18N
        b_del.setBorder(null);
        b_del.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/del_paciente_dis.gif"))); // NOI18N
        b_del.setPreferredSize(new java.awt.Dimension(44, 44));
        b_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_delActionPerformed(evt);
            }
        });

        b_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mod_paciente.gif"))); // NOI18N
        b_mod.setBorder(null);
        b_mod.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mod_paciente_dis.gif"))); // NOI18N
        b_mod.setPreferredSize(new java.awt.Dimension(44, 44));
        b_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_modActionPerformed(evt);
            }
        });

        b_fich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mos_ficha.gif"))); // NOI18N
        b_fich.setBorder(null);
        b_fich.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mos_ficha_dis.gif"))); // NOI18N
        b_fich.setPreferredSize(new java.awt.Dimension(44, 44));
        b_fich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_fichActionPerformed(evt);
            }
        });

        b_busc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/bus_paciente.gif"))); // NOI18N
        b_busc.setBorder(null);
        b_busc.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/bus_paciente_dis.gif"))); // NOI18N
        b_busc.setPreferredSize(new java.awt.Dimension(44, 44));
        b_busc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_buscActionPerformed(evt);
            }
        });

        b_prueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/real_prueba.gif"))); // NOI18N
        b_prueba.setBorder(null);
        b_prueba.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/real_prueba_dis.gif"))); // NOI18N
        b_prueba.setPreferredSize(new java.awt.Dimension(44, 44));
        b_prueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_pruebaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(b_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_fich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_busc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b_busc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(b_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_fich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar1.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/add-folder-to-archive.png"))); // NOI18N
        jMenu6.setText("Archivo");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });

        jMenuItem11.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-upgrade.png"))); // NOI18N
        jMenuItem11.setText("Cargar base de datos ");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuItem12.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-downgrade.png"))); // NOI18N
        jMenuItem12.setText("Exportar base de datos ");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/view-sort-ascending.png"))); // NOI18N
        jMenuItem3.setText("Exportar informe");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem3);
        jMenu6.add(jSeparator1);

        jMenuItem13.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/system-shutdown-restart-panel.png"))); // NOI18N
        jMenuItem13.setText("Salir");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuBar1.add(jMenu6);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/view-list-details-symbolic.png"))); // NOI18N
        jMenu8.setText("Entrenamiento");
        jMenu8.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem18.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-installed-updated.png"))); // NOI18N
        jMenuItem18.setText("Foveal");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem18);

        jMenuItem19.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-broken.png"))); // NOI18N
        jMenuItem19.setText("Periférica");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem19);

        jMenuBar1.add(jMenu8);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/view-list-icons.png"))); // NOI18N
        jMenu2.setText("Prueba");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem16.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/stock_properties.png"))); // NOI18N
        jMenuItem16.setText("Configuración Manual");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem17.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/stock_print-setup.png"))); // NOI18N
        jMenuItem17.setText("Configuración Automática");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/tools-check-spelling.png"))); // NOI18N
        jMenu4.setText("Paciente   ");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/stock_new-bcard.png"))); // NOI18N
        jMenuItem4.setText("Nuevo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/stock_zoom-in.png"))); // NOI18N
        jMenuItem5.setText("Buscar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/window_nofullscreen.png"))); // NOI18N
        jMenu1.setText("Herramientas");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem10.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/stock_fullscreen.png"))); // NOI18N
        jMenuItem10.setText("Configuración Avanzada");
        jMenuItem10.setName(""); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuBar1.add(jMenu1);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/gtk-about.png"))); // NOI18N
        jMenu3.setText("Ayuda");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/gtk-info.png"))); // NOI18N
        jMenuItem15.setText("Acerca de...");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        NuevoPacienteView np = new NuevoPacienteView(this, true);
        np.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        BuscarPacienteView bp = new BuscarPacienteView(this, true);
        bp.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        eliminarPaciente();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        try {
            FichaPacienteView fich = new FichaPacienteView(this, true);
            fich.setVisible(true);
        } catch (Exception e) {
            ErrorDialog err = new ErrorDialog(this, true, e.getMessage());
            err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        ModificarPacienteView mp = new ModificarPacienteView(this, true);
        mp.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        prueba = pacienteActual.getPeriferica();
        if (prueba != null) {
            ResultView resultado = new ResultView(this, true, false);
            resultado.setVisible(true);
        } else {
            ErrorDialog err = new ErrorDialog(this, true,
                    "A este paciente no se le ha realizado este tipo de prueba");
            err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de base de datos(*.tls)", "tls");
        jFileChooser1.addChoosableFileFilter(filter);
        jFileChooser1.setDialogTitle("Exportar base de datos");
        jFileChooser1.setDialogType(JFileChooser.SAVE_DIALOG);
        jFileChooser1.showSaveDialog(this);
        File selectPlaced = jFileChooser1.getSelectedFile();
        if (selectPlaced != null) {
            String extension = selectPlaced.getPath().substring(selectPlaced.getPath().length()
                    - ".tls".length(), selectPlaced.getPath().length());

            if (extension.equals(".tls")) {
                registro.salvarRegistro(selectPlaced.getPath());
            } else {
                registro.salvarRegistro(selectPlaced.getPath() + "." + filter.getExtensions()[0]);
            }
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de base de datos (*.tls)", "tls");
        jFileChooser1.addChoosableFileFilter(filter);
        jFileChooser1.setDialogTitle("Importar base de datos");
        jFileChooser1.setDialogType(JFileChooser.OPEN_DIALOG);
        int status = jFileChooser1.showOpenDialog(this);

        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser1.getSelectedFile();
            try {
                Registro base = Registro.cargarRegistro(selectedFile.getPath());
                registro.importarRegistro(base);
                modificarTableModel();
                registro.salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);
            } catch (Exception ex) {
                ErrorDialog e = new ErrorDialog(this, true, ex.getMessage());
                e.setVisible(true);
            }
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void b_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_addActionPerformed
        // TODO add your handling code here:
        NuevoPacienteView np = new NuevoPacienteView(this, true);
        np.setVisible(true);
    }//GEN-LAST:event_b_addActionPerformed

    private void b_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modActionPerformed
        // TODO add your handling code here:
        ModificarPacienteView mp = new ModificarPacienteView(this, true);
        mp.setVisible(true);
    }//GEN-LAST:event_b_modActionPerformed

    private void b_fichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_fichActionPerformed
        // TODO add your handling code here:
        FichaPacienteView fp = new FichaPacienteView(this, true);
        fp.setVisible(true);
    }//GEN-LAST:event_b_fichActionPerformed

    private void b_pruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_pruebaActionPerformed
        // TODO add your handling code here:
        TipoPruebaView rp = new TipoPruebaView(this, true);
        rp.setVisible(true);
    }//GEN-LAST:event_b_pruebaActionPerformed

    private void b_buscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_buscActionPerformed
        // TODO add your handling code here:
        BuscarPacienteView bp = new BuscarPacienteView(this, true);
        bp.setVisible(true);
    }//GEN-LAST:event_b_buscActionPerformed

    private void b_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_delActionPerformed
        // TODO add your handling code here:
        eliminarPaciente();
    }//GEN-LAST:event_b_delActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        prueba = pacienteActual.getFoveal();
        if (prueba != null) {
            ResultView resultado = new ResultView(this, true, true);
            resultado.setVisible(true);
        } else {
            ErrorDialog err = new ErrorDialog(this, true,
                    "A este paciente no se le ha realizdo este tipo de prueba");
            err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ManualConfigView c = new ManualConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        AutoConfigView c = new AutoConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoAuto(false);
        prueba = new PruebaFoveal(ConfigApp.CANT_ENSAYOS, conf);
        FovealTestView t = new FovealTestView(this, true, true);
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoAuto(false);
        prueba = new PruebaPeriferica(ConfigApp.CANT_ENSAYOS, conf);
        PerifericaTestView t = new PerifericaTestView(this, true, true);
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        ManualConfigView c = new ManualConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        AutoConfigView c = new AutoConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        AvanzadaConfigView avanzadaConfigView = new AvanzadaConfigView(this, true);
        avanzadaConfigView.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos Excel(*.xls)", "xls");
        jFileChooser1.addChoosableFileFilter(filter);
        jFileChooser1.setDialogTitle("Exportar informe de datos xls");
        jFileChooser1.setDialogType(JFileChooser.SAVE_DIALOG);
        jFileChooser1.showSaveDialog(this);
        File selectPlaced = jFileChooser1.getSelectedFile();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeExcel excel1 = new InformeParametrosXEnsayo(book);
        AbstractInformeExcel excel2 = new InformeCampoVisual(book);
        excel1.getInformeExcel();
        excel2.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + filter.getExtensions()[0], book);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        AboutJDialog aboutJDialog = new AboutJDialog(this, true);
        aboutJDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        new ScreenSplash().animar();

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PrincipalView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Resultados;
    private javax.swing.JButton b_add;
    private javax.swing.JButton b_busc;
    private javax.swing.JButton b_del;
    private javax.swing.JButton b_fich;
    private javax.swing.JButton b_mod;
    private javax.swing.JButton b_prueba;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPopupMenu menu;
    // End of variables declaration//GEN-END:variables
}
