/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import quino.clases.config.IConfiguracion;
import quino.clases.model.Prueba;
import quino.view.main.ErrorDialog;
import quino.view.main.PrincipalView;

/**
 *
 * @author farias
 */
public class QuinoTools {

    /**
     * Devuelve el ancho q debe tener una celda para que todo el texto
     * q contiene una celda se vea en la celda y no se solape con otras celdas
     * vecinas. Una celda por defecto tiene valor de width de 2048 y caben en
     * ella 9 caracteres mas o menos
     * @param strlength El tamaño del texto str.length
     * @return El ancho de la celda
     */
    public static int getColumnWidthSize(int strlength) {
        return (int)(strlength * 2600 / 9);
    }

    public static String getPanelMovimiento(Prueba prueba, int panel) {
        if (prueba.isFoveal()) {
            switch (panel) {
                case 1:
                    return "Superior Izquierdo";
                case 2:
                    return "Superior";
                case 3:
                    return "Superior Derecho";
                case 4:
                    return "Derecho";
                case 5:
                    return "Izquierdo";
                case 6:
                    return "Inferior Izquierdo";
                case 7:
                    return "Inferior";
                case 8:
                    return "Inferior Derecho";
                default:
                    return "Ninguno";
            }
        } else {
            switch (panel) {
                case 1:
                    return "Panel Izquierdo";
                case 2:
                    return "Panel Derecho";
                default:
                    return "Ninguno";
            }
        }
    }

    public static void salvarPruebaEnRegistro(PrincipalView principalView, JDialog testView, Prueba prueba) {
        int option = -1;

        Prueba pruebaActual = prueba.isFoveal() ? principalView.getPacienteActual().getFoveal()
                : principalView.getPacienteActual().getPeriferica();
        String nombrePrueba = prueba.isFoveal() ? "Foveal" : "Periférica";

        try {
            if (pruebaActual != null) {
                option = JOptionPane.showConfirmDialog(testView, "El(La) paciente " + principalView.getPacienteActual().getNombre() + "\n"
                        + "tiene registrado(a) una prueba de tipo " + nombrePrueba + "\n"
                        + "¿Desea sobreescribir la prueba realizada?", "Advertencia", JOptionPane.YES_NO_OPTION);
                switch (option) {
                    case 0: {
                        if (prueba.isFoveal()) {
                            principalView.getPacienteActual().setFoveal(prueba);
                        } else {
                            principalView.getPacienteActual().setPeriferica(prueba);
                        }
                        principalView.getRegistro().salvarRegistro(IConfiguracion.REGISTRO_FILE_NAME);
                        principalView.Modificar_Tabla();
                    }
                    break;
                    case 1:
                        break;
                }
            } else {
                if (prueba.isFoveal()) {
                    principalView.getPacienteActual().setFoveal(prueba);
                } else {
                    principalView.getPacienteActual().setPeriferica(prueba);
                }
                principalView.getRegistro().salvarRegistro(IConfiguracion.REGISTRO_FILE_NAME);
                principalView.Modificar_Tabla();
            }
        } catch (Exception e) {
            ErrorDialog err = new ErrorDialog(principalView, true, "No se ha podido guardar la prueba");
            err.setVisible(true);
        }
    }
}
