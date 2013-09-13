/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import quino.clases.config.ConfigApp;
import quino.clases.config.ConfigEnsayoAuto;
import quino.clases.model.Paciente;
import quino.clases.model.PruebaFoveal;
import quino.clases.model.PruebaPeriferica;
import quino.clases.model.Registro;
import quino.clases.model.Resultado;
import quino.util.Aleatorio;

/**
 *
 * @author farias
 */
public class MainData {

    public static void main(String[] args) {
        try {
            //Registro registro = Registro.cargarRegistro(ConfigApp.REGISTRO_FILE_NAME);
            Aleatorio random = new Aleatorio();
            Registro registro = new Registro();

            for (int i = 0; i < 100; i++) {

                PruebaFoveal foveal = new PruebaFoveal(random.nextInt(5, 9),
                        new ConfigEnsayoAuto(false));
                for (int j = 0; j < foveal.getCantEnsayos(); j++) {
                    boolean error = j % 3 == 0 ? true : false;
                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    foveal.getEnsayos().get(j).setResultado(results);
                }

                PruebaPeriferica periferica = new PruebaPeriferica(random.nextInt(2, 8),
                        new ConfigEnsayoAuto(false));
                for (int j = 0; j < periferica.getCantEnsayos(); j++) {
                    boolean error = j % 2 == 0 ? true : false;
                   Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    periferica.getEnsayos().get(j).setResultado(results);
                }

                String anno = String.valueOf(random.nextInt(10, 99));
                String mes = String.valueOf(random.nextInt(1, 12));
                String dia = String.valueOf(random.nextInt(1, 27));
                String rest = String.valueOf(random.nextInt(10000, 99999));

                String hn1 = String.valueOf(random.nextInt(10, 19));
                String hn2 = String.valueOf(random.nextInt(20, 29));
                String hn3 = String.valueOf(random.nextInt(30, 39));
                String hn4 = String.valueOf(random.nextInt(40, 49));
                String hn5 = String.valueOf(random.nextInt(50, 59));
                String historia = hn1 + hn2 + hn3 + hn4 + hn5;

                long ci = Long.parseLong(anno + mes + dia + rest);
                String nombre = "Ernestico" + i + " Acabalo Todo";

                String sexo = random.nextInt(0, 100) % 2 == 0 ? "Masculino" : "Femenino";
                String escolaridad = String.valueOf(random.nextInt(4, 6)) + "to";

                Paciente paciente = new Paciente(nombre, random.nextInt(5, 50),
                        sexo, escolaridad, historia, ci, "Ficha",
                        periferica, foveal, "Amigos de todos");

                registro.nuevoPaciente(paciente);
            }

            registro.salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);

        } catch (Exception ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
