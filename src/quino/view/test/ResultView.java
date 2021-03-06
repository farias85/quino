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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 04/10/2013.
 */
package quino.view.test;

import java.text.DateFormat;
import quino.util.Grafica;

import quino.clases.model.Resultado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import quino.clases.config.ConfigApp;
import quino.clases.config.ConfigEnsayo;
import quino.clases.config.ConfigEnsayoEnrejado;
import quino.util.QuinoTools;
import quino.view.main.ErrorDialog;
import quino.view.main.PrincipalView;
import quino.clases.model.Ensayo;
import quino.clases.config.ConfigEnsayoFormaAB;
import quino.clases.config.ConfigEnsayoGabor;
import quino.clases.config.ConfigEnsayoOrientacion;
import quino.clases.config.ConfigEnsayoShapeDetect;
import quino.clases.config.ConfigEnsayoVelocidad;
import quino.util.test.PruebaEnrejado;
import quino.util.test.PruebaFormaAB;
import quino.util.test.PruebaGabor;
import quino.util.test.PruebaOrientacion;
import quino.util.test.PruebaShape;
import quino.util.test.PruebaVelocidad;

public class ResultView extends javax.swing.JDialog {

    private PrincipalView parent;

    public ResultView() {
    }

    public ResultView(PrincipalView parent, boolean modal) {
        super(parent, modal);

        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);

        try {
            List<Ensayo> ensayos = parent.getPrueba().getEnsayos();
            if (parent.getPrueba() != null) {
                t_ensayos1.setText(String.valueOf(ensayos.size()));
                t_errores1.setText(String.valueOf(parent.getPrueba().cantErrores()));

                if (parent.getPrueba() instanceof PruebaFormaAB) {
                    t_denpromedio1.setText(String.valueOf(((PruebaFormaAB) parent.getPrueba()).densidadPromedio()));
                } else if (parent.getPrueba() instanceof PruebaShape) {
                    t_denpromedio1.setText(String.valueOf(((PruebaShape) parent.getPrueba()).densidadPromedio()));
                    menuDeteccionForma();
                } else if (parent.getPrueba() instanceof PruebaVelocidad) {
                    menuVelocidad();
                } else if (parent.getPrueba() instanceof PruebaEnrejado) {
                    menuEnrejado();
                } else if (parent.getPrueba() instanceof PruebaGabor) {
                    menuGabor();
                } else if (parent.getPrueba() instanceof PruebaOrientacion) {
                    menuOrientacion();
                }

                t_trespg1.setText(String.valueOf(parent.getPrueba().tiempoRespuestaPromedio()));
                t_densayo.setText(String.valueOf(ConfigApp.TIEMPO_DURACION));

                DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
                fechaLabel.setText(df.format(parent.getPrueba().getFecha()));

                tipoLabel.setText(parent.getPrueba().toString());
            } else {
                throw new Exception("Al paciente seleccionado no se le ha realizado ninguna prueba");
            }
            this.Arbol((ArrayList<Ensayo>) ensayos);
            PintarPastel();
        } catch (Exception e) {
            ErrorDialog err = new ErrorDialog(parent, true, e.getMessage());
            err.setVisible(true);
            setVisible(false);
            dispose();
        }
        
        jLabel27.setVisible(false);
    }

    private void Arbol(final ArrayList<Ensayo> ensayos) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Ensayos");
        DefaultTreeModel tm = new DefaultTreeModel(root);
        for (int i = 0; i < ensayos.size(); i++) {
            String nombre = "Ensayo" + " " + "#" + String.valueOf(i);
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(nombre);
            tm.insertNodeInto(nodo, root, i);
        }
        jTree1.setModel(tm);
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getPath().getLastPathComponent());
                if (node.getParent() != null) {
                    int pos = node.getParent().getIndex(node);
                    Ensayo ensayo = ensayos.get(pos);
                    ConfigEnsayo configEnsayo = ensayo.getConfiguracion();

                    if (configEnsayo instanceof ConfigEnsayoFormaAB) {
                        mostrarDatosFormaAB(ensayo);
                    } else if (configEnsayo instanceof ConfigEnsayoShapeDetect) {
                        mostrarDatosDeteccionForma(ensayo);
                    } else if (configEnsayo instanceof ConfigEnsayoOrientacion) {
                        mostrarDatosOrientacion(ensayo);
                    } else if (configEnsayo instanceof ConfigEnsayoVelocidad) {
                        mostrarDatosVelocidad(ensayo);
                    } else if (configEnsayo instanceof ConfigEnsayoEnrejado) {
                        mostrarDatosEnrejado(ensayo);
                    } else if (configEnsayo instanceof ConfigEnsayoGabor) {
                        mostrarDatosGabor(ensayo);
                    }
                }
            }
        });
    }

    private void menuOrientacion() {
        jLabel9.setVisible(false);
        jLabel33.setText("Frecuencia espacial (Hz):");
        jLabel31.setText("Contraste:");
        jLabel24.setText("Intensidad Media:");
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
        jLabel11.setVisible(false);
        jLabel29.setVisible(false);
        jLabel34.setVisible(false);
    }

    private void mostrarDatosOrientacion(Ensayo ensayo) {
        ConfigEnsayoOrientacion configEnsayo = ((ConfigEnsayoOrientacion) ensayo.getConfiguracion());
        Resultado resultado = ensayo.getResultado();

        menuOrientacion();
        
        t_densidad1.setText(String.valueOf(Math.rint(QuinoTools.getPPiXFrecuenciaEspacial(configEnsayo.getPpi()) * 1000) / 1000));
        t_cantidad1.setText(String.valueOf(Math.rint(configEnsayo.getContrat() * 100) / 100));
        t_vmov1.setText(String.valueOf(Math.abs((int) configEnsayo.getIntensidadMedia())));

        t_trespuesta1.setText(resultado.getTiempoRespuesta() == 0 ? "N/R" : String.valueOf(resultado.getTiempoRespuesta()));
        ImageIcon resultIcon = CambiarError(resultado.isError());
        t_resultado1.setIcon(resultIcon);

        e_desc.setText(resultado.getDescripcion());

        String panel = QuinoTools.getPanelMovimiento(parent.getPrueba(), ensayo.getConfiguracion().getPanelEstimulo());
        t_pestimulo1.setText(panel);
    }

    private void menuGabor() {
        jLabel9.setVisible(false);
        jLabel33.setText("F. espacial Int/Ext (Hz):");
        jLabel31.setText("Contraste:");
        jLabel24.setText("Intensidad Media:");
        jLabel25.setText("Campana de Gabor (px):");
        jLabel26.setText("Radio Int/Ext (px):");
        
        jLabel27.setVisible(true);
        jLabel27.setText("Velocidad (grado/ms):");
        
        jLabel28.setVisible(true);
        jLabel28.setText("Frecuencia temporal (Hz):");
        
        jLabel11.setVisible(false);
    }

    private void mostrarDatosGabor(Ensayo ensayo) {
        ConfigEnsayoGabor configEnsayo = ((ConfigEnsayoGabor) ensayo.getConfiguracion());
        Resultado resultado = ensayo.getResultado();

        menuGabor();

        t_densidad1.setText(String.valueOf(Math.rint(configEnsayo.getPpi() * 1000) / 1000)
                        + " / " + String.valueOf(Math.rint(configEnsayo.getPpi2() * 1000) / 1000));
        t_cantidad1.setText(String.valueOf(Math.rint(configEnsayo.getContrat() * 100) / 100));
        t_vmov1.setText(String.valueOf(Math.abs((int) configEnsayo.getIntensidadMedia())));

        t_angulo1.setText(String.valueOf(Math.rint(configEnsayo.getVelocidadPrimaria().getAceleracion() * 100) / 100));
        t_pestimulo1.setText(String.valueOf(Math.rint(configEnsayo.getFrecuenciaMuestreo() * 100) / 100));
        
        t_trespuesta1.setText(resultado.getTiempoRespuesta() == 0 ? "N/R" : String.valueOf(resultado.getTiempoRespuesta()));
        ImageIcon resultIcon = CambiarError(resultado.isError());
        t_resultado1.setIcon(resultIcon);

        ImageIcon dirIcon = CambiarDireccion(configEnsayo.getDireccion(),
                configEnsayo.getDireccion());
        t_direccion1.setIcon(dirIcon);

        e_desc.setText(resultado.getDescripcion());

        t_velocidad.setText(String.valueOf(configEnsayo.getGaussianStdpix()));
        t_angulo.setText(String.valueOf(configEnsayo.getRadio1() + " / " + configEnsayo.getRadio2()));

        ImageIcon keyIcon = CambiarArrowKey(resultado.getKey());
        b_keypressed.setIcon(keyIcon);
    }
    
    private void menuVelocidad() {
        jLabel9.setVisible(false);
        jLabel33.setText("Frecuencia espacial (Hz):");
        jLabel31.setText("Contraste:");
        jLabel24.setText("Intensidad media:");
        
        jLabel25.setVisible(true);
        jLabel25.setText("Velocidad 1 (grado/ms):");
        
        jLabel26.setVisible(true);
        jLabel26.setText("Velocidad 2 (grado/ms):");
        
        jLabel28.setVisible(true);
        jLabel28.setText("Frecuencia temporal (Hz):");
        
        t_pestimulo1.setVisible(true);
        t_pestimulo1.setEnabled(true);
        
        jLabel11.setVisible(false);
        jLabel34.setVisible(false);
    }
    
    private void mostrarDatosVelocidad(Ensayo ensayo) {
        ConfigEnsayoVelocidad configEnsayo = ((ConfigEnsayoVelocidad) ensayo.getConfiguracion());
        Resultado resultado = ensayo.getResultado();

        menuVelocidad();

        t_densidad1.setText(String.valueOf(Math.rint(QuinoTools.getPPiXFrecuenciaEspacial(configEnsayo.getPpi()) * 1000) / 1000));
        t_cantidad1.setText(String.valueOf(Math.rint(configEnsayo.getContrat() * 1000) / 1000));
        t_vmov1.setText(String.valueOf(Math.abs((int) configEnsayo.getIntensidadMedia())));        
        
        t_velocidad.setText(String.valueOf(Math.rint(configEnsayo.getVelocidadPrimaria().getAceleracion() * 100) / 100));
        t_angulo.setText(String.valueOf(Math.rint(configEnsayo.getVelocidadSecundaria().getAceleracion() * 100) / 100));
        t_pestimulo1.setText(String.valueOf(Math.rint(configEnsayo.getFrecuenciaMuestreo() * 100) / 100));

        t_trespuesta1.setText(resultado.getTiempoRespuesta() == 0 ? "N/R" : String.valueOf(resultado.getTiempoRespuesta()));
        ImageIcon resultIcon = CambiarError(resultado.isError());
        t_resultado1.setIcon(resultIcon);

        if (configEnsayo.isOnMove()) {
            ImageIcon dirIcon = CambiarDireccion(configEnsayo.getDireccion(),
                    configEnsayo.getDireccion());
            t_direccion1.setIcon(dirIcon);
        } else {
            ImageIcon dirIcon = CambiarDireccion(configEnsayo.getDireccion(), 0);
            t_direccion1.setIcon(dirIcon);
        }

        e_desc.setText(resultado.getDescripcion());
    }
    
    private void menuEnrejado() {
        jLabel9.setVisible(false);
        jLabel33.setText("Frecuencia espacial (Hz):");
        jLabel31.setText("Contraste:");
        jLabel24.setText("Intensidad media:");
        
        jLabel25.setVisible(true);
        jLabel25.setText("Velocidad 1 (grado/ms):");
        
        jLabel28.setVisible(true);
        jLabel28.setText("Frecuencia temporal (Hz):");
        
        jLabel26.setVisible(false);
        jLabel11.setVisible(false);
        jLabel34.setVisible(false);
    }

    private void mostrarDatosEnrejado(Ensayo ensayo) {
        ConfigEnsayoEnrejado configEnsayo = ((ConfigEnsayoEnrejado) ensayo.getConfiguracion());
        Resultado resultado = ensayo.getResultado();

        menuEnrejado();

        t_densidad1.setText(String.valueOf(Math.rint(QuinoTools.getPPiXFrecuenciaEspacial(configEnsayo.getPpi()) * 1000) / 1000));
        t_cantidad1.setText(String.valueOf(Math.rint(configEnsayo.getContrat() * 100) / 100));
        t_vmov1.setText(String.valueOf(Math.abs((int) configEnsayo.getIntensidadMedia())));

        t_velocidad.setText(String.valueOf(Math.rint(configEnsayo.getVelocidadPrimaria().getAceleracion() * 100) / 100));
        t_pestimulo1.setText(String.valueOf(Math.rint(configEnsayo.getFrecuenciaMuestreo() * 100) / 100));
        
        t_trespuesta1.setText(resultado.getTiempoRespuesta() == 0 ? "N/R" : String.valueOf(resultado.getTiempoRespuesta()));
        ImageIcon resultIcon = CambiarError(resultado.isError());
        t_resultado1.setIcon(resultIcon);

        if (configEnsayo.isOnMove()) {
            ImageIcon dirIcon = CambiarDireccion(configEnsayo.getDireccion(),
                    configEnsayo.getDireccion());
            t_direccion1.setIcon(dirIcon);
        } else {
            ImageIcon dirIcon = CambiarDireccion(configEnsayo.getDireccion(), 0);
            t_direccion1.setIcon(dirIcon);
        }

        e_desc.setText(resultado.getDescripcion());
    }

    private void menuDeteccionForma() {
        jLabel31.setText("% de Rectas:");
        jLabel24.setText("Tolerancia:");
        jLabel25.setText("Figura");
        jLabel26.setVisible(false);
        jLabel29.setVisible(false);
        jLabel34.setVisible(false);
        jLabel11.setVisible(false);
    }

    private void mostrarDatosDeteccionForma(Ensayo ensayo) {
        ConfigEnsayoShapeDetect configEnsayo = ((ConfigEnsayoShapeDetect) ensayo.getConfiguracion());
        Resultado resultado = ensayo.getResultado();

        menuDeteccionForma();

        t_densidad1.setText(String.valueOf(configEnsayo.getDensidad()));
        t_cantidad1.setText(String.valueOf(configEnsayo.getPcShape()));
        //t_angulo.setText(String.valueOf(resultado.getAngulo()));
        t_vmov1.setText(String.valueOf(configEnsayo.getTolerancia()));
        t_pestimulo1.setText(ensayo.getConfiguracion().getPanelEstimulo() == 0 ? "-" : configEnsayo.getKey() == 37 ? "Panel Izquierdo" : "Panel Derecho");
        t_trespuesta1.setText(resultado.getTiempoRespuesta() == 0 ? "N/R" : String.valueOf(resultado.getTiempoRespuesta()));
        t_velocidad.setText(QuinoTools.getNombreFigura(configEnsayo.getNumFigura()));

        ImageIcon resultIcon = CambiarError(resultado.isError());
        t_resultado1.setIcon(resultIcon);

        e_desc.setText(resultado.getDescripcion());

        if (ensayo.getConfiguracion().getPanelEstimulo() == 0) {
            t_cantidad1.setText("-");
            t_vmov1.setText(String.valueOf("-"));
            t_velocidad.setText("-");
            t_angulo.setText("-");
        }
    }

    private void mostrarDatosFormaAB(Ensayo ensayo) {

        ConfigEnsayoFormaAB configEnsayo = ((ConfigEnsayoFormaAB) ensayo.getConfiguracion());
        Resultado resultado = ensayo.getResultado();

        int densidad = configEnsayo.getDensidad();
        int cantidad = (configEnsayo.getCantidad() * 100) / densidad;
        double velocidad = configEnsayo.getTiempoMovimiento();

        ImageIcon dirIcon = CambiarDireccion(configEnsayo.getDireccion(),
                ensayo.getConfiguracion().getPanelEstimulo());
        String panel = QuinoTools.getPanelMovimiento(parent.getPrueba(), ensayo.getConfiguracion().getPanelEstimulo());
        int tiempo_res = resultado.getTiempoRespuesta();

        ImageIcon resultIcon = CambiarError(resultado.isError());
        ImageIcon keyIcon = CambiarKey(resultado.getKey(), configEnsayo.isControl());
        String descripcion = resultado.getDescripcion();
        double vel = resultado.getVelocidad();
        double angulo = resultado.getAngulo();

        t_densidad1.setText(String.valueOf(densidad));
        t_cantidad1.setText(String.valueOf(cantidad) + "%");
        t_vmov1.setText(String.valueOf(velocidad));
        t_direccion1.setIcon(dirIcon);
        t_pestimulo1.setText(panel);
        b_keypressed.setIcon(keyIcon);
        e_desc.setText(descripcion);
        t_velocidad.setText(Double.toString(vel) + " " + "cm/mls");
        t_angulo.setText(Double.toString(angulo) + "º");

        if (tiempo_res == 0) {
            t_trespuesta1.setText("N/R");
        } else {
            t_trespuesta1.setText(String.valueOf(tiempo_res));
        }

        t_resultado1.setIcon(resultIcon);
        if (ensayo.getConfiguracion().getPanelEstimulo() == 0) {
            t_cantidad1.setText("-");
            t_vmov1.setText(String.valueOf("-"));
            t_velocidad.setText("-");
            t_angulo.setText("-");
        }
    }

    private ImageIcon CambiarDireccion(int pos, int panel) {
        if (panel == 0) {
            pos = -1;
        }
        switch (pos) {
            case 0: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "asincronico.gif"));
            }
            case 1: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion1.gif"));
            }
            case 2: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion2.gif"));
            }
            case 3: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion3.gif"));
            }
            case 4: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion4.gif"));
            }
            case 5: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion5.gif"));
            }
            case 6: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion6.gif"));
            }
            case 7: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion7.gif"));
            }
            case 8: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion8.gif"));
            }
        }
        return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "error.gif"));
    }

    private ImageIcon CambiarKey(int key, boolean control) {
        if (!control || key == 0) {
            key = -1;
        }
        switch (key) {
            case 104: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion1.gif"));
            }
            case 98: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion2.gif"));
            }
            case 102: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion3.gif"));
            }
            case 100: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion4.gif"));
            }
            case 105: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion5.gif"));
            }
            case 103: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion6.gif"));
            }
            case 99: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion7.gif"));
            }
            case 97: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion8.gif"));
            }
        }
        return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "error.gif"));
    }

    private ImageIcon CambiarArrowKey(int key) {
        switch (key) {
            case 38: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion1.gif"));
            }
            case 40: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion2.gif"));
            }
            case 39: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion3.gif"));
            }
            case 37: {
                return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "direccion4.gif"));
            }
        }
        return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "error.gif"));
    }

    private ImageIcon CambiarError(boolean error) {
        if (error) {

            return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "error.gif"));
        } else {
            return new ImageIcon(getClass().getResource(ConfigApp.RESOURCES_LOCATION + "exito.gif"));
        }
    }

    private void PintarPastel() {
        //int aciertos = prueba.getResultados().size()-prueba.cant_Errores();
        int errores = parent.getPrueba().cantErrores();
        int perrores = (errores * 100) / parent.getPrueba().getEnsayos().size();
        int paciertos = 100 - perrores;
        String laciertos = String.valueOf(paciertos) + "%" + " " + "Aciertos";
        String lerrores = String.valueOf(perrores) + "%" + " " + "Errores";
        Grafica g = new Grafica();
        int diametro = 200;
        int x = (Pastel.getWidth() / 2) - (diametro / 2);
        int y = (Pastel.getHeight() / 2) - (diametro / 2);

        try {
            g.PintarPastel(Pastel.getGraphics(), diametro, x, y, perrores, paciertos, lerrores, laciertos);
        } catch (Exception e) {
            //Logger.getLogger(ResultView.class.getName()).log(Level.SEVERE, null, e);
            ErrorDialog err = new ErrorDialog(parent, true, e.getMessage());
            err.setVisible(true);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        t_ensayos1 = new javax.swing.JTextField();
        t_errores1 = new javax.swing.JTextField();
        t_denpromedio1 = new javax.swing.JTextField();
        t_trespg1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tipoLabel = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        fechaLabel = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        t_trespuesta1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        t_cantidad1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        t_vmov1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        t_pestimulo1 = new javax.swing.JTextField();
        t_densidad1 = new javax.swing.JTextField();
        t_direccion1 = new javax.swing.JButton();
        t_resultado1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        b_keypressed = new javax.swing.JButton();
        e_desc = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        t_velocidad = new javax.swing.JTextField();
        t_angulo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        t_angulo1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        t_densayo = new javax.swing.JTextField();
        t_interestimulo = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Pastel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setResizable(false);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parámetros Generales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Ensayos Realizados:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Cantidad de Errores:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Densidad Promedio:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Tiempo de Respuesta:");

        t_ensayos1.setEditable(false);
        t_ensayos1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_ensayos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_ensayos1.setBorder(null);

        t_errores1.setEditable(false);
        t_errores1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_errores1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_errores1.setBorder(null);

        t_denpromedio1.setEditable(false);
        t_denpromedio1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_denpromedio1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_denpromedio1.setBorder(null);

        t_trespg1.setEditable(false);
        t_trespg1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_trespg1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_trespg1.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Prueba");

        tipoLabel.setEditable(false);
        tipoLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipoLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tipoLabel.setBorder(null);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Fecha:");

        fechaLabel.setEditable(false);
        fechaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fechaLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaLabel.setBorder(null);
        fechaLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel6))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_ensayos1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(t_errores1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(t_denpromedio1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(t_trespg1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fechaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_ensayos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_errores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_denpromedio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(t_trespg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fechaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parámetros por Ensayos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTree1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTree1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane2.setViewportView(jTree1);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Panel de Estímulo:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Tiempo de Desplazamiento");

        t_trespuesta1.setEditable(false);
        t_trespuesta1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_trespuesta1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_trespuesta1.setBorder(null);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Dirección de Movimiento:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Tiempo de Respuesta:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("% de Puntos:");

        t_cantidad1.setEditable(false);
        t_cantidad1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_cantidad1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_cantidad1.setBorder(null);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Resultado:");

        t_vmov1.setEditable(false);
        t_vmov1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_vmov1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_vmov1.setBorder(null);
        t_vmov1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_vmov1ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Densidad de Puntos:");

        t_pestimulo1.setEditable(false);
        t_pestimulo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_pestimulo1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_pestimulo1.setBorder(null);

        t_densidad1.setEditable(false);
        t_densidad1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_densidad1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_densidad1.setBorder(null);

        t_direccion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/vacio.gif"))); // NOI18N

        t_resultado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/vacio.gif"))); // NOI18N
        t_resultado1.setBorder(null);
        t_resultado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_resultado1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Dirección Presionada:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Descripción del Error:");

        b_keypressed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quino/view/main/icons/vacio.gif"))); // NOI18N
        b_keypressed.setBorder(null);
        b_keypressed.setMaximumSize(new java.awt.Dimension(65, 43));
        b_keypressed.setMinimumSize(new java.awt.Dimension(65, 43));
        b_keypressed.setPreferredSize(new java.awt.Dimension(65, 43));

        e_desc.setEditable(false);
        e_desc.setBackground(new java.awt.Color(240, 240, 240));
        e_desc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        e_desc.setBorder(null);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Velocidad de Movimiento:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Ángulo:");

        t_velocidad.setEditable(false);
        t_velocidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_velocidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_velocidad.setBorder(null);

        t_angulo.setEditable(false);
        t_angulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_angulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_angulo.setBorder(null);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Ángulo:");

        t_angulo1.setEditable(false);
        t_angulo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_angulo1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        t_angulo1.setBorder(null);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel30)
                                            .addComponent(jLabel32)))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel28)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t_resultado1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t_trespuesta1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t_pestimulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel35)
                                .addGap(14, 14, 14)
                                .addComponent(e_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel27)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(t_direccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(t_vmov1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(t_cantidad1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t_velocidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(t_angulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(t_densidad1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel34)
                                    .addComponent(t_angulo1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(b_keypressed, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(86, 86, 86))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_densidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_cantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_vmov1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_velocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(t_angulo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(t_angulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_keypressed, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t_direccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_pestimulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(t_trespuesta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(t_resultado1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(e_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel32))))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Tiempo Interestímulo:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Duración de Ensayo:");

        t_densayo.setEditable(false);
        t_densayo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_densayo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_densayo.setBorder(null);

        t_interestimulo.setEditable(false);
        t_interestimulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_interestimulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_interestimulo.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t_densayo, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(t_interestimulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_densayo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(t_interestimulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Resultados", jPanel1);

        javax.swing.GroupLayout PastelLayout = new javax.swing.GroupLayout(Pastel);
        Pastel.setLayout(PastelLayout);
        PastelLayout.setHorizontalGroup(
            PastelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );
        PastelLayout.setVerticalGroup(
            PastelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Mostrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(Pastel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(605, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Pastel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Gráficos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        //PintarPastel();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PintarPastel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void t_resultado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_resultado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_resultado1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void t_vmov1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_vmov1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_vmov1ActionPerformed

    private void fechaLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaLabelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ResultView dialog = new ResultView();
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
    private javax.swing.JPanel Pastel;
    private javax.swing.JButton b_keypressed;
    private javax.swing.JTextField e_desc;
    private javax.swing.JTextField fechaLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTextField t_angulo;
    private javax.swing.JTextField t_angulo1;
    private javax.swing.JTextField t_cantidad1;
    private javax.swing.JTextField t_denpromedio1;
    private javax.swing.JTextField t_densayo;
    private javax.swing.JTextField t_densidad1;
    private javax.swing.JButton t_direccion1;
    private javax.swing.JTextField t_ensayos1;
    private javax.swing.JTextField t_errores1;
    private javax.swing.JTextField t_interestimulo;
    private javax.swing.JTextField t_pestimulo1;
    private javax.swing.JButton t_resultado1;
    private javax.swing.JTextField t_trespg1;
    private javax.swing.JTextField t_trespuesta1;
    private javax.swing.JTextField t_velocidad;
    private javax.swing.JTextField t_vmov1;
    private javax.swing.JTextField tipoLabel;
    // End of variables declaration//GEN-END:variables
}
