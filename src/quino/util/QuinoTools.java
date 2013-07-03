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
