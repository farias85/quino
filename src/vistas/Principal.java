/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 24-jul-2010, 10:23:06
 */

package vistas;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import vistas.Prueba.Configuración_Manual;
import vistas.Prueba.Configuracion_Automatica;
import Datos.Registro;
import clases.prueba.Configuracion;
import clases.prueba.ConfiguracionAutomatica;
import clases.prueba.ConfiguracionAvanzada;
import clases.prueba.Prueba;
import java.io.IOException;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import vistas.Paciente.BuscarPaciente;
import vistas.Paciente.FichaPaciente;
import vistas.Paciente.ModificarPaciente;
import vistas.Paciente.NuevoPaciente;
import vistas.Prueba.Result;
import vistas.Prueba.Test;
import java.io.File;
import javax.swing.filechooser.*;
import javax.swing.JFileChooser;
import vistas.Paciente.RealizarPrueba;
import vistas.Prueba.Central_Test;



/**
 *
 * @author davisito
 */
public class Principal extends javax.swing.JFrame {
    //public clases.prueba.Tester tester;
   public Configuracion configuracion;
   public Prueba prueba;
   public ConfiguracionAvanzada conf_avanzada;
   public Registro pacientes;
   private int sel_paciente;
   private int respuesta;

    /** Creates new form Principal */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        jMenuItem10.setEnabled(false);
        jMenuItem15.setEnabled(false);
        try{
            pacientes = new Registro();
            pacientes = Registro.OpenObject("datos.bin");
            Mod_Tabla();
            if (pacientes.Size()==0) {
                b_busc.setEnabled(false);
                b_del.setEnabled(false);
                b_fich.setEnabled(false);
                b_mod.setEnabled(false);
                b_prueba.setEnabled(false);
                jMenuItem10.setEnabled(false);
                jMenuItem15.setEnabled(false);
                throw new Exception("No existen Pacientes registrados");
            }
        }
        catch(Exception e){
            if(e instanceof IOException){
                Errores err = new Errores(this, true, "No existen Pacientes registrados");
                err.setVisible(true);
            }else{
                Errores err = new Errores(this, true, e.getMessage());
                err.setVisible(true);
                pacientes = new Registro();
            }
        }
        /*if(pacientes.Size()==0){
            jMenuItem10.setEnabled(false);
        }*/
        try{
            conf_avanzada = new ConfiguracionAvanzada(WIDTH, WIDTH);
            conf_avanzada = ConfiguracionAvanzada.OpenObject("config.bin");
        }
        catch(Exception e){
            if(e instanceof IOException){
                conf_avanzada = new ConfiguracionAvanzada(1000, 400);
            }            
        }
        jMenu5.add(jMenuItem1);
        jMenu5.add(jMenuItem2);
        //jTable1.setComponentPopupMenu(menu);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
               ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                if (lsm.isSelectionEmpty()) {
                    jMenu5.setEnabled(false);
                    jMenuItem6.setEnabled(false);
                    jMenuItem7.setEnabled(false);
                    jMenuItem8.setEnabled(false);
                    jMenuItem9.setEnabled(false);
                    b_del.setEnabled(false);
                    b_fich.setEnabled(false);
                    b_mod.setEnabled(false);
                    b_prueba.setEnabled(false);
                    jMenuItem10.setEnabled(false);
                    jMenuItem15.setEnabled(false);
                    
                }else{
                    jMenu5.setEnabled(true);
                    jMenuItem6.setEnabled(true);
                    jMenuItem7.setEnabled(true);
                    jMenuItem8.setEnabled(true);
                    jMenuItem9.setEnabled(true);
                    b_del.setEnabled(true);
                    b_fich.setEnabled(true);
                    b_mod.setEnabled(true);
                    b_prueba.setEnabled(true);
                    jMenuItem10.setEnabled(true);
                    jMenuItem15.setEnabled(true);
                    sel_paciente = lsm.getMinSelectionIndex();
                    jTable1.setComponentPopupMenu(menu);
                }
            }
        });
        b_add.setToolTipText("Registrar nuevo Paciente");
        b_del.setToolTipText("Eliminar el Paciente seleccionado");
        b_busc.setToolTipText("Buscar un Paciente");
        b_fich.setToolTipText("Mostrar la Ficha del Paciente seleccionado");
        b_mod.setToolTipText("Modificar el Paciente seleccionado");
        b_prueba.setToolTipText("Realizar Prueba al Paciente seleccionado");
    }

    public int getSel_paciente() {
        return sel_paciente;
    }


    public void Mod_Tabla(){
        DefaultTableModel tm = new DefaultTableModel(){
            //para que las celdas no se puedan editar
            boolean[] celdasNoEditables = {false, false, false, false, false, false};
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return celdasNoEditables[columna];
            }


        };
        tm.addColumn("Historia Clínica");
        tm.addColumn("Nombre y Apellidos");
        tm.addColumn("Carné de Identidad");
        tm.addColumn("Edad");
        tm.addColumn("Escolaridad");
        tm.addColumn("Sexo");
        for (int i = 0; i < pacientes.Size(); i++) {
            Object[] fila = {pacientes.paciente_Pos(i).getNo_historia(), pacientes.paciente_Pos(i).getNombre(),
                             pacientes.paciente_Pos(i).getCI(), pacientes.paciente_Pos(i).getEdad(),
                            pacientes.paciente_Pos(i).getEscolaridad(), pacientes.paciente_Pos(i).getSexo()};
            tm.addRow(fila);
        }
        if (pacientes.Size()==0) {
            b_busc.setEnabled(false);
        }else{
            b_busc.setEnabled(true);
        }
        jTable1.setModel(tm);
        jTable1.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 12));        
        //jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //jTable1.setCellSelectionEnabled(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
    }
    public boolean itemSelected(){
        for (int i = 0; i < pacientes.Size(); i++){
             if (jTable1.isRowSelected(i)) {
                 return true;
            }
        }
        return false;  
    }
    public void ActivarPractica(){
          jMenuItem10.setEnabled(true);
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
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

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
        jMenuItem14.setText("Prueba Fobeal");
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

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12));
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

        b_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/add_paciente.gif"))); // NOI18N
        b_add.setBorder(null);
        b_add.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/add_paciente_dis.gif"))); // NOI18N
        b_add.setPreferredSize(new java.awt.Dimension(44, 44));
        b_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_addActionPerformed(evt);
            }
        });

        b_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/del_paciente.gif"))); // NOI18N
        b_del.setBorder(null);
        b_del.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/del_paciente_dis.gif"))); // NOI18N
        b_del.setPreferredSize(new java.awt.Dimension(44, 44));
        b_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_delActionPerformed(evt);
            }
        });

        b_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/mod_paciente.gif"))); // NOI18N
        b_mod.setBorder(null);
        b_mod.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/mod_paciente_dis.gif"))); // NOI18N
        b_mod.setPreferredSize(new java.awt.Dimension(44, 44));
        b_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_modActionPerformed(evt);
            }
        });

        b_fich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/mos_ficha.gif"))); // NOI18N
        b_fich.setBorder(null);
        b_fich.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/mos_ficha_dis.gif"))); // NOI18N
        b_fich.setPreferredSize(new java.awt.Dimension(44, 44));
        b_fich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_fichActionPerformed(evt);
            }
        });

        b_busc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/bus_paciente.gif"))); // NOI18N
        b_busc.setBorder(null);
        b_busc.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/bus_paciente_dis.gif"))); // NOI18N
        b_busc.setPreferredSize(new java.awt.Dimension(44, 44));
        b_busc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_buscActionPerformed(evt);
            }
        });

        b_prueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/real_prueba.gif"))); // NOI18N
        b_prueba.setBorder(null);
        b_prueba.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/Iconos/real_prueba_dis.gif"))); // NOI18N
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

        jMenu6.setText("Archivo");
        jMenu6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });

        jMenuItem11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem11.setText("Importar Base");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuItem12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem12.setText("Exportar Base");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);
        jMenu6.add(jSeparator1);

        jMenuItem13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem13.setText("Salir");
        jMenu6.add(jMenuItem13);

        jMenuBar1.add(jMenu6);

        jMenu2.setText("Prueba");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenu1.setText("Nueva");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem10.setText("Configuración Manual");
        jMenuItem10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem10MouseMoved(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem15.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem15.setText("Configuración Automática");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);

        jMenu2.add(jMenu1);

        jMenu7.setText("Práctica");
        jMenu7.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jMenuItem16.setText("Paracentral");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem16);

        jMenuItem17.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem17.setText("Periférica");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem17);

        jMenu2.add(jMenu7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Herramientas");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem3.setText("Configuracion Avanzada");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Paciente");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 12));

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem4.setText("Nuevo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMenuItem5.setText("Buscar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Configuracion_Avanzada conf = new Configuracion_Avanzada(this, true);
        conf.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        NuevoPaciente np= new NuevoPaciente(this, true);
        np.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        BuscarPaciente bp = new BuscarPaciente(this, true);
        bp.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
       try{
            String hist = pacientes.paciente_Pos(sel_paciente).getNo_historia();
            pacientes.Eliminar(hist);
            /*Errores err = new Errores(this, true, "El Paciente ha sido eliminado satisfactoriamente");
            err.setVisible(true);*/
            Mod_Tabla();
            pacientes.SaveObject("datos.bin");
        }
        catch(Exception e){
          Errores err = new Errores(this, true, e.getMessage());
          err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
         try{
            int pos = sel_paciente;
            FichaPaciente fich = new FichaPaciente(this, true, pos);
            fich.setVisible(true);
        }
        catch(Exception e){
            Errores err = new Errores(this, true, e.getMessage());
            err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        ModificarPaciente mp = new ModificarPaciente(this, true, sel_paciente);
        mp.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        try{
            Prueba p = pacientes.paciente_Pos(sel_paciente).getPrueba();
            Result resultado = new Result(this, true, p);
            resultado.setVisible(true);
        }
        catch(Exception e){
            Errores err = new Errores(this, true, e.getMessage());
            err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        try{
            
            FileNameExtensionFilter  filter =  new FileNameExtensionFilter("Archivos de base (*.tls)","tls");
            jFileChooser1.addChoosableFileFilter( filter);
            jFileChooser1.setDialogTitle("Exportar Base");
            jFileChooser1.setDialogType(JFileChooser.SAVE_DIALOG);
            jFileChooser1.showSaveDialog(this);
            File selectPlaced = jFileChooser1.getSelectedFile();
            String extension = selectPlaced.getPath().substring(selectPlaced.getPath().length()-".tls".length(),selectPlaced.getPath().length());
            if(extension.equals(".tls"))
                pacientes.SaveObject(selectPlaced.getPath());
            else
                pacientes.SaveObject(selectPlaced.getPath()+ "." +filter.getExtensions()[0]);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (IOException ex) {
            Errores err = new Errores(this, true, ex.getMessage());
            err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter  filter =  new FileNameExtensionFilter("Archivos de base (*.tls)","tls");
        jFileChooser1.addChoosableFileFilter( filter);
        jFileChooser1.setDialogTitle("Importar Base");
        jFileChooser1.setDialogType(JFileChooser.OPEN_DIALOG);
        int status =  jFileChooser1.showOpenDialog(this);

        if(status == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = jFileChooser1.getSelectedFile();
            try {
                 Registro base = Registro.OpenObject(selectedFile.getPath());
                 pacientes.Importar(base);
                 Mod_Tabla();
                 pacientes.SaveObject("datos.bin");
            } catch (Exception ex) {
                Errores e = new Errores(this, true, ex.getMessage());
                e.setVisible(true);
            } 
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void b_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_addActionPerformed
        // TODO add your handling code here:
        NuevoPaciente np= new NuevoPaciente(this, true);
        np.setVisible(true);
    }//GEN-LAST:event_b_addActionPerformed

    private void b_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modActionPerformed
        // TODO add your handling code here:
        ModificarPaciente mp= new ModificarPaciente(this, true, sel_paciente);
        mp.setVisible(true);
    }//GEN-LAST:event_b_modActionPerformed

    private void b_fichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_fichActionPerformed
        // TODO add your handling code here:
        FichaPaciente fp= new FichaPaciente(this, true, sel_paciente);
        fp.setVisible(true);
    }//GEN-LAST:event_b_fichActionPerformed

    private void b_pruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_pruebaActionPerformed
        // TODO add your handling code here:
        RealizarPrueba rp= new RealizarPrueba(this, true);
        rp.setVisible(true);
    }//GEN-LAST:event_b_pruebaActionPerformed

    private void b_buscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_buscActionPerformed
        // TODO add your handling code here:
        BuscarPaciente bp= new BuscarPaciente(this, true);
        bp.setVisible(true);
    }//GEN-LAST:event_b_buscActionPerformed

    private void b_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_delActionPerformed
        // TODO add your handling code here:
        Confirmacion conf = new Confirmacion(this, true);
        conf.setVisible(true);
        respuesta = conf.getReturnStatus();
          
        if(respuesta == 1){
             try{
                String hist = pacientes.paciente_Pos(sel_paciente).getNo_historia();
                pacientes.Eliminar(hist);
                
                Mod_Tabla();
                pacientes.SaveObject("datos.bin");
            }
            catch(Exception e){
              Errores err = new Errores(this, true, e.getMessage());
              err.setVisible(true);
            }
        }
        else
            Mod_Tabla();
        
    }//GEN-LAST:event_b_delActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        try{
            Prueba p = pacientes.paciente_Pos(sel_paciente).getFobeal();
            Result resultado = new Result(this, true, p);
            resultado.setVisible(true);
        }
        catch(Exception e){
            Errores err = new Errores(this, true, e.getMessage());
            err.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Configuración_Manual c = new Configuración_Manual(this, true);
            c.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Configuracion_Automatica c = new Configuracion_Automatica(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        Configuracion confi = new ConfiguracionAutomatica(false);
        ConfiguracionAvanzada ca= new ConfiguracionAvanzada(3000, 1600);
        Prueba p = new Prueba(5, ca);
        Test t = new Test(this, true, p, confi, true, true);
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        Configuracion confi = new ConfiguracionAutomatica(false);
        ConfiguracionAvanzada ca= new ConfiguracionAvanzada(3000, 1600);
        Prueba p = new Prueba(5, ca);
        Central_Test t = new Central_Test(this, true, p, confi, true, true);
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        Configuración_Manual c = new Configuración_Manual(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        Configuracion_Automatica c = new Configuracion_Automatica(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem10MouseMoved

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
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
    private javax.swing.JMenu jMenu7;
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
