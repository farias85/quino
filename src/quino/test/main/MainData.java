/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quino.clases.config.ConfigApp;
import quino.clases.model.Paciente;
import quino.clases.model.Prueba;
import quino.clases.model.Registro;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;

/**
 *
 * @author farias
 */
public class MainData {

    public static void main(String[] args) {
        try {
            Registro registro = Registro.cargarRegistro(ConfigApp.REGISTRO_FILE_NAME);

            Prueba foveal = new Prueba(3);
            for (int i = 0; i < foveal.getCantEnsayos(); i++) {
                Resultado results = new Resultado(0.141, 650, 2, 500, 21, true, i, 2, false, "Descrip", 32, true, 0.022, 9.2);
                foveal.getResultados().add(results);
            }

            Prueba periferica = new Prueba(4);
            for (int i = 0; i < periferica.getCantEnsayos(); i++) {
                Resultado results2 = new Resultado(0.141, 650, 2, 500, 21, true, i, 2, false, "Descrip", 32, true, 0.022, 9.2);
                periferica.getResultados().add(results2);
            }

            for (int i = 0; i < 100; i++) {
                String historia = "1452" + i;
                long ci = 85051100701L + i;
                String nombre = "Ernestico" + i + " Acabalo Todo";


                Paciente paciente = new Paciente(nombre, 28, "Masculino", "4to", historia, ci, "Ficha", periferica, foveal, "Amigos de todos");

                paciente.setPeriferica(periferica);
                paciente.setFoveal(foveal);

                registro.nuevoPaciente(paciente);
            }

            registro.salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);

        } catch (IOException ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
