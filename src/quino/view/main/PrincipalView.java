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
import quino.view.test.ManualFormaABConfigView;
import quino.view.test.AutoFormaABConfigView;
import quino.clases.model.Registro;
import quino.clases.config.ConfigEnsayoFormaABAuto;
import quino.util.test.Prueba;
import java.io.IOException;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import quino.view.paciente.BuscarPacienteView;
import quino.view.paciente.FichaPacienteView;
import quino.view.paciente.ModificarPacienteView;
import quino.view.paciente.NuevoPacienteView;
import quino.view.test.ResultView;
import quino.view.test.FormaBTestView;
import java.io.File;
import javax.swing.filechooser.*;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import quino.clases.config.ConfigApp;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoEnrejadoAuto;
import quino.clases.config.ConfigEnsayoGaborAuto;
import quino.clases.config.ConfigEnsayoOrientacionAuto;
import quino.clases.config.ConfigEnsayoShapeDetectAuto;
import quino.clases.config.ConfigEnsayoVelocidadAuto;
import quino.util.Aleatorio;
import quino.util.test.PruebaFormaA;
import quino.util.test.PruebaFormaB;
import quino.util.test.PruebaShape;
import quino.util.QuinoTableModel;
import quino.util.QuinoTools;
import quino.util.QuinoSplash;
import quino.util.report.AbstractInformeAB;
import quino.util.report.AbstractInformeExcel;
import quino.util.report.InformeCampoVisual;
import quino.util.report.InformeParametrosXEnsayo;
import quino.util.report.estadisticas.EstadisticaDeteccionForma;
import quino.util.report.estadisticas.EstadisticaEnrejado;
import quino.util.report.estadisticas.EstadisticaFormaAB;
import quino.util.report.estadisticas.EstadisticaGabor;
import quino.util.report.estadisticas.EstadisticaOrientacion;
import quino.util.report.estadisticas.EstadisticaVelocidad;
import quino.util.report.estadisticas.FrecuenciaEspacial;
import quino.util.test.PruebaEnrejado;
import quino.util.test.PruebaGabor;
import quino.util.test.PruebaOrientacion;
import quino.util.test.PruebaVelocidad;
import quino.view.test2nd.EnrejadoTestView;
import quino.view.test2nd.ShapeDetectTestView;
import quino.view.test.FormaATestView;
import quino.view.test2nd.ManualEnrejadoConfigView;
import quino.view.test2nd.GaborTestView;
import quino.view.test2nd.OrientacionTestView;
import quino.view.test2nd.ManualShapeDetectConfigView;
import quino.view.test2nd.AutoTest2ndConfigView;
import quino.view.test2nd.ManualGaborConfigView;
import quino.view.test2nd.ManualOrientacionConfigView;
import quino.view.test2nd.ManualVelocidadConfigView;
import quino.view.test2nd.VelocidadTestView;

/**
 *
 * @author Felipe Rodriguez Arias
 */
public class PrincipalView extends javax.swing.JFrame {

    private ConfigEnsayo conf;
    private Prueba prueba;
    private Registro registro;
    private Paciente pacienteActual;
    private Aleatorio random = new Aleatorio();

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

    /**
     * Creates new form PrincipalView
     */
    public PrincipalView() {

        boolean is64bit;
        if (System.getProperty("os.name").contains("Windows")) {
            is64bit = (System.getenv("ProgramFiles(x86)") != null);
        } else {
            is64bit = (System.getProperty("os.arch").contains("64"));
        }

        if (is64bit) {
            System.loadLibrary("/lib/opencv2411/x64/opencv_java2411");
        } else {
            System.loadLibrary("/lib/opencv2411/x86/opencv_java2411");
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        this.setLocationRelativeTo(null);
        jMenu7.setEnabled(false);
        jMenu9.setEnabled(false);

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
                jMenu7.setEnabled(false);
                jMenu9.setEnabled(false);
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

                    //pacienteActual.get
                    menu = new JPopupMenu();
                    jMenu5.setFont(new java.awt.Font("Tahoma", 1, 12));
                    menu.add(jMenu5);
                    menu.add(jMenuItem6);
                    menu.add(jMenuItem7);
                    menu.add(jMenuItem8);

                    Resultados = new JMenu();

                    Resultados.setText("Mostrar Resultados");
                    Resultados.setFont(new java.awt.Font("Tahoma", 1, 12));

                    if (pacienteActual.getPeriferica() != null) {
                        Resultados.add(jMenuItem9);
                    }
                    if (pacienteActual.getFoveal() != null) {
                        Resultados.add(jMenuItem14);
                    }
                    if (pacienteActual.getForma() != null) {
                        Resultados.add(jMenuItem34);
                    }
                    if (pacienteActual.getEnrejado() != null) {
                        Resultados.add(jMenuItem35);
                    }
                    if (pacienteActual.getGabor() != null) {
                        Resultados.add(jMenuItem36);
                    }
                    if (pacienteActual.getOrientacion() != null) {
                        Resultados.add(jMenuItem37);
                    }
                    if (pacienteActual.getVelocidad() != null) {
                        Resultados.add(jMenuItem52);
                    }

                    if (Resultados.getMenuComponentCount() > 0) {
                        menu.add(Resultados);
                    }

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

        jMenu10.add(jMenuItem1);
        jMenu10.add(jMenuItem2);
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
        jMenu7.setEnabled(habilitar);
        jMenu9.setEnabled(habilitar);
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
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
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
        jMenu7.setEnabled(true);
    }

    private void eliminarPaciente() {
        ConfirmDialog confirmDialog = new ConfirmDialog(this, true);
        confirmDialog.setVisible(true);

        int respuesta = confirmDialog.getReturnStatus();

        if (respuesta == 1) {
            try {
                int[] nums = jTable1.getSelectedRows();

                for (int i = nums.length - 1; i >= 0; i--) {
                    Paciente p = registro.getPacientes().get(nums[i]);
                    String hist = p.getHistoria();
                    registro.eliminarXHistoria(hist);
                }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPopupMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem55 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenuItem56 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        Resultados = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem52 = new javax.swing.JMenuItem();
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
        jMenu12 = new javax.swing.JMenu();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem50 = new javax.swing.JMenuItem();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenuItem57 = new javax.swing.JMenuItem();
        jMenuItem58 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem53 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem54 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();

        jMenu5.setText("Realizar Prueba");
        jMenu5.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenu10.setText("Configuración Manual");
        jMenu10.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem1.setText("Forma A, B");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem2.setText("Detección de Forma");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem2);

        jMenuItem16.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem16.setText("Enrejado");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem16);

        jMenuItem17.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem17.setText("Campana de Gabor");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem17);

        jMenuItem38.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem38.setText("Detección de Orientación");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem38);

        jMenuItem55.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem55.setText("Detección de Velocidad");
        jMenuItem55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem55ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem55);

        jMenu5.add(jMenu10);

        jMenu11.setText("Configuración Automática");
        jMenu11.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem39.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem39.setText("Forma A, B");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem39);

        jMenuItem40.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem40.setText("Detección de Forma");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem40);

        jMenuItem41.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem41.setText("Enrejado");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem41);

        jMenuItem42.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem42.setText("Campana de Gabor");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem42);

        jMenuItem43.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem43.setText("Detección de Orientación");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem43);

        jMenuItem56.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem56.setText("Detección de Velocidad");
        jMenuItem56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem56ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem56);

        jMenu5.add(jMenu11);

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
        jMenuItem9.setText("Prueba Forma B");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem9);

        jMenuItem14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem14.setText("Prueba Forma A");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem14);

        jMenuItem34.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem34.setText("Prueba Detección Forma");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem34);

        jMenuItem35.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem35.setText("Prueba Enrejado");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem35);

        jMenuItem36.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem36.setText("Prueba Gabor");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem36);

        jMenuItem37.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem37.setText("Prueba Detección de Orientación");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem37);

        jMenuItem52.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem52.setText("Prueba Detección de Velocidad");
        jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem52ActionPerformed(evt);
            }
        });
        Resultados.add(jMenuItem52);

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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setRowHeight(20);
        jTable1.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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
        jMenuItem3.setText("Exportar informe  ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem3);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/add-folder-to-archive.png"))); // NOI18N
        jMenu12.setText("Exportar Estadísticas    ");
        jMenu12.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem45.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-installed-updated.png"))); // NOI18N
        jMenuItem45.setText("Forma A");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem45);

        jMenuItem46.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-installed-updated.png"))); // NOI18N
        jMenuItem46.setText("Forma B");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem46);

        jMenuItem47.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-remove.png"))); // NOI18N
        jMenuItem47.setText("Detección de Forma");
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem47);

        jMenuItem50.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/star.png"))); // NOI18N
        jMenuItem50.setText("Enrejado   ");
        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem50);

        jMenuItem49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-supported.png"))); // NOI18N
        jMenuItem49.setText("Campana de Gabor   ");
        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem49);

        jMenuItem57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mail-send.png"))); // NOI18N
        jMenuItem57.setText("Detección de Orientación");
        jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem57ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem57);

        jMenuItem58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/lpi-translate.png"))); // NOI18N
        jMenuItem58.setText("Discriminación de Velocidad");
        jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem58ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem58);

        jMenu6.add(jMenu12);
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

        jMenu8.setText("Entrenamiento");
        jMenu8.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem18.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-installed-updated.png"))); // NOI18N
        jMenuItem18.setText("Forma A");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem18);

        jMenuItem19.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-broken.png"))); // NOI18N
        jMenuItem19.setText("Forma B");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem19);

        jMenuItem20.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-remove.png"))); // NOI18N
        jMenuItem20.setText("Detección de Forma");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem20);

        jMenuItem21.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/star.png"))); // NOI18N
        jMenuItem21.setText("Enrejado");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem21);

        jMenuItem22.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-supported.png"))); // NOI18N
        jMenuItem22.setText("Campana de Gabor");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem22);

        jMenuItem27.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mail-send.png"))); // NOI18N
        jMenuItem27.setText("Detección de Orientación");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem27);

        jMenuItem53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/lpi-translate.png"))); // NOI18N
        jMenuItem53.setText("Discriminación de Velocidad");
        jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem53ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem53);

        jMenuBar1.add(jMenu8);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/view-list-icons.png"))); // NOI18N
        jMenu2.setText("Prueba");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenu7.setText("Configuración Manual   ");
        jMenu7.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem23.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-installed-updated.png"))); // NOI18N
        jMenuItem23.setText("Forma A,B");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem23);

        jMenuItem24.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-remove.png"))); // NOI18N
        jMenuItem24.setText("Detección de Forma");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed1(evt);
            }
        });
        jMenu7.add(jMenuItem24);

        jMenuItem25.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/star.png"))); // NOI18N
        jMenuItem25.setText("Enrejado");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem25);

        jMenuItem26.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-supported.png"))); // NOI18N
        jMenuItem26.setText("Campana de Gabor");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem26);

        jMenuItem33.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mail-send.png"))); // NOI18N
        jMenuItem33.setText("Detección de Orientación");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem33);

        jMenuItem44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/lpi-translate.png"))); // NOI18N
        jMenuItem44.setText("Discriminación de Velocidad  ");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem44);

        jMenu2.add(jMenu7);

        jMenu9.setText("Configuración Automática");
        jMenu9.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem28.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-installed-updated.png"))); // NOI18N
        jMenuItem28.setText("Forma A, B");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem28);

        jMenuItem29.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-remove.png"))); // NOI18N
        jMenuItem29.setText("Detección de Forma");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem29);

        jMenuItem31.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/star.png"))); // NOI18N
        jMenuItem31.setText("Enrejado");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed1(evt);
            }
        });
        jMenu9.add(jMenuItem31);

        jMenuItem30.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/package-supported.png"))); // NOI18N
        jMenuItem30.setText("Campana de Gabor");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed1(evt);
            }
        });
        jMenu9.add(jMenuItem30);

        jMenuItem32.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/mail-send.png"))); // NOI18N
        jMenuItem32.setText("Detección de Orientación");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem32);

        jMenuItem54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/lpi-translate.png"))); // NOI18N
        jMenuItem54.setText("Discriminación de Velocidad");
        jMenuItem54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem54ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem54);

        jMenu2.add(jMenu9);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/tools-check-spelling.png"))); // NOI18N
        jMenu4.setText("Paciente");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/stock_new-bcard.png"))); // NOI18N
        jMenuItem4.setText("Nuevo Paciente");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/stock_zoom-in.png"))); // NOI18N
        jMenuItem5.setText("Buscar Paciente");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/window_nofullscreen.png"))); // NOI18N
        jMenu1.setText("Herramienta         ");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 12));

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
        jMenu3.setText("Ayuda   ");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 12));

        jMenuItem15.setFont(new java.awt.Font("Tahoma", 0, 12));
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
        verResultadoDPrueba();
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
        verResultadoDPrueba();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoFormaABAuto(false, random.nextInt(0, 8));
        prueba = new PruebaFormaA((ConfigEnsayoFormaABAuto) conf, ConfigApp.CANT_ENSAYOS);
        FormaATestView t = new FormaATestView(this, true, true);
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoFormaABAuto(false, random.nextInt(0, 2));
        prueba = new PruebaFormaB((ConfigEnsayoFormaABAuto) conf, ConfigApp.CANT_ENSAYOS);
        FormaBTestView t = new FormaBTestView(this, true, true);
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        ManualEnrejadoConfigView emcv = new ManualEnrejadoConfigView(this, true);
        emcv.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

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

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoShapeDetectAuto();
        prueba = new PruebaShape((ConfigEnsayoShapeDetectAuto) conf, ConfigApp.CANT_ENSAYOS);
        ShapeDetectTestView sdtv = new ShapeDetectTestView(this, true, true);
        sdtv.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoEnrejadoAuto();
        prueba = new PruebaEnrejado((ConfigEnsayoEnrejadoAuto) conf, ConfigApp.CANT_ENSAYOS);
        EnrejadoTestView etv = new EnrejadoTestView(this, true, true);
        etv.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem21ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed1
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem21ActionPerformed1

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoGaborAuto();
        prueba = new PruebaGabor((ConfigEnsayoGaborAuto) conf, ConfigApp.CANT_ENSAYOS);
        GaborTestView gtv = new GaborTestView(this, true, true);
        gtv.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        ManualFormaABConfigView c = new ManualFormaABConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoOrientacionAuto();
        prueba = new PruebaOrientacion((ConfigEnsayoOrientacionAuto) conf, ConfigApp.CANT_ENSAYOS);
        OrientacionTestView otv = new OrientacionTestView(this, true, true);
        otv.setVisible(true);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        // TODO add your handling code here:
        ManualEnrejadoConfigView emcv = new ManualEnrejadoConfigView(this, true);
        emcv.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoFormaABAuto();
        AutoFormaABConfigView c = new AutoFormaABConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoShapeDetectAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoEnrejadoAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoGaborAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoOrientacionAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        ManualGaborConfigView mgcv = new ManualGaborConfigView(this, true);
        mgcv.setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem32ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed1
        // TODO add your handling code here:
        conf = new ConfigEnsayoOrientacionAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed1

    private void jMenuItem30ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed1
        // TODO add your handling code here:
        conf = new ConfigEnsayoGaborAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed1

    private void jMenuItem31ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed1
        // TODO add your handling code here:
        conf = new ConfigEnsayoEnrejadoAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed1

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        // TODO add your handling code here:
        ManualOrientacionConfigView mocv = new ManualOrientacionConfigView(this, true);
        mocv.setVisible(true);
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        // TODO add your handling code here:
        prueba = pacienteActual.getForma();
        verResultadoDPrueba();
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        // TODO add your handling code here:]
        prueba = pacienteActual.getEnrejado();
        verResultadoDPrueba();
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        // TODO add your handling code here:
        prueba = pacienteActual.getGabor();
        verResultadoDPrueba();
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        // TODO add your handling code here:
        prueba = pacienteActual.getOrientacion();
        verResultadoDPrueba();
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem24ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed1
        // TODO add your handling code here:
        ManualShapeDetectConfigView c = new ManualShapeDetectConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed1

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ManualFormaABConfigView c = new ManualFormaABConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        ManualShapeDetectConfigView c = new ManualShapeDetectConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        ManualGaborConfigView mgcv = new ManualGaborConfigView(this, true);
        mgcv.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        // TODO add your handling code here:
        ManualOrientacionConfigView mocv = new ManualOrientacionConfigView(this, true);
        mocv.setVisible(true);
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoFormaABAuto();
        AutoFormaABConfigView c = new AutoFormaABConfigView(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoShapeDetectAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoEnrejadoAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoGaborAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoOrientacionAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        // TODO add your handling code here:
        ManualVelocidadConfigView mvcv = new ManualVelocidadConfigView(this, true);
        mvcv.setVisible(true);
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        // TODO add your handling code here:
        File selectPlaced = openFileChooser2Estadistica();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeAB excel1 = new EstadisticaFormaAB(book);
        excel1.setFoveal(true);
        excel1.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + "xls", book);
        }
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private File openFileChooser2Estadistica() {

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos Excel(*.xls)", "xls");
        jFileChooser1.addChoosableFileFilter(filter);
        jFileChooser1.setDialogTitle("Exportar informe de datos xls");
        jFileChooser1.setDialogType(JFileChooser.SAVE_DIALOG);
        jFileChooser1.showSaveDialog(this);
        return jFileChooser1.getSelectedFile();
    }

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        // TODO add your handling code here:
        File selectPlaced = openFileChooser2Estadistica();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeAB excel1 = new EstadisticaFormaAB(book);
        excel1.setFoveal(false);
        excel1.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + "xls", book);
        }
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        // TODO add your handling code here:
        File selectPlaced = openFileChooser2Estadistica();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeExcel excel1 = new EstadisticaDeteccionForma(book);
        excel1.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + "xls", book);
        }
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        // TODO add your handling code here:
        File selectPlaced = openFileChooser2Estadistica();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeExcel excel1 = new EstadisticaEnrejado(book, FrecuenciaEspacial.ALL);
        excel1.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + "xls", book);
        }
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        // TODO add your handling code here:
        File selectPlaced = openFileChooser2Estadistica();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeExcel excel1 = new EstadisticaGabor(book);
        excel1.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + "xls", book);
        }
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        // TODO add your handling code here:
        prueba = pacienteActual.getVelocidad();
        verResultadoDPrueba();
    }//GEN-LAST:event_jMenuItem52ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoVelocidadAuto();
        prueba = new PruebaVelocidad((ConfigEnsayoVelocidadAuto) conf, ConfigApp.CANT_ENSAYOS);
        VelocidadTestView vtv = new VelocidadTestView(this, true, true);
        vtv.setVisible(true);
    }//GEN-LAST:event_jMenuItem53ActionPerformed

    private void jMenuItem54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem54ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoVelocidadAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem54ActionPerformed

    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        // TODO add your handling code here:
        ManualVelocidadConfigView mocv = new ManualVelocidadConfigView(this, true);
        mocv.setVisible(true);
    }//GEN-LAST:event_jMenuItem55ActionPerformed

    private void jMenuItem56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem56ActionPerformed
        // TODO add your handling code here:
        conf = new ConfigEnsayoVelocidadAuto();
        AutoTest2ndConfigView tacv = new AutoTest2ndConfigView(this, true);
        tacv.setVisible(true);
    }//GEN-LAST:event_jMenuItem56ActionPerformed

    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        // TODO add your handling code here:
        File selectPlaced = openFileChooser2Estadistica();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeExcel excel1 = new EstadisticaOrientacion(book);
        excel1.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + "xls", book);
        }
    }//GEN-LAST:event_jMenuItem57ActionPerformed

    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        // TODO add your handling code here:
        File selectPlaced = openFileChooser2Estadistica();

        HSSFWorkbook book = new HSSFWorkbook();
        AbstractInformeExcel excel1 = new EstadisticaVelocidad(book);
        excel1.getInformeExcel();

        if (selectPlaced != null) {
            QuinoTools.salvarLibroExcel(selectPlaced.getPath() + "." + "xls", book);
        }
    }//GEN-LAST:event_jMenuItem58ActionPerformed

    private void verResultadoDPrueba() {
        if (prueba != null) {
            ResultView resultado = new ResultView(this, true);
            resultado.setVisible(true);
        } else {
            ErrorDialog err = new ErrorDialog(this, true,
                    "A este paciente no se le ha realizdo este tipo de prueba");
            err.setVisible(true);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        new QuinoSplash().animar();

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
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
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
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem54;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem56;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
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
