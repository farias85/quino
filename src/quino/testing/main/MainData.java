/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.testing.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import quino.clases.config.ConfigApp;
import quino.clases.config.ConfigEnsayoEnrejadoAuto;
import quino.clases.config.ConfigEnsayoFormaABAuto;
import quino.clases.config.ConfigEnsayoGaborAuto;
import quino.clases.config.ConfigEnsayoOrientacionAuto;
import quino.clases.config.ConfigEnsayoShapeDetectAuto;
import quino.clases.config.ConfigEnsayoVelocidadAuto;
import quino.clases.model.Paciente;
import quino.util.test.PruebaFormaA;
import quino.util.test.PruebaFormaB;
import quino.clases.model.Registro;
import quino.clases.model.Resultado;
import quino.util.Aleatorio;
import quino.util.test.PruebaEnrejado;
import quino.util.test.PruebaGabor;
import quino.util.test.PruebaOrientacion;
import quino.util.test.PruebaShape;
import quino.util.test.PruebaVelocidad;

/**
 * Clase ejecutable para la generación de datos de prueba
 * @author Felipe Rodriguez Arias
 */
public class MainData {

    private static String[] nombres = {"Felipe", "Vladimir", "Ernesto",
        "Camilo", "Fernando", "Daniel", "linnet", "Onelia", "Amanda", "Liset", "Maité"};
    private static String[] apellidos = {"Rodriguez", "Hernandez", "Fernandez",
        "Perez", "La O", "Reyes", "Amat", "Balart", "Macias", "Escobedo", "Valdes",
        "Marañon", "Quintana", "Aguilar", "Peña", "Arias", "Fadraga", "Santana"};
    private static String[] escuelas = {"Moncada", "26 de Julio", "Camilo Cienfuegos",
        "José Martí", "Amistad Cuba Holanda", "Pedro Soto Alba"};

    public static void main(String[] args) {
        try {
            //Registro registro = Registro.cargarRegistro(ConfigApp.REGISTRO_FILE_NAME);
            Aleatorio random = new Aleatorio();
            Registro registro = new Registro();

            for (int i = 0; i < 15; i++) {

                PruebaFormaA foveal = new PruebaFormaA(new ConfigEnsayoFormaABAuto(false, random.nextInt(0, 8)), random.nextInt(5, 9));
                for (int j = 0; j < foveal.getEnsayos().size(); j++) {
                    boolean error = j % 3 == 0 ? true : false;
                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    foveal.getEnsayos().get(j).setResultado(results);
                }

                PruebaFormaB periferica = new PruebaFormaB(new ConfigEnsayoFormaABAuto(false, random.nextInt(0, 2)), random.nextInt(2, 8));
                for (int j = 0; j < periferica.getEnsayos().size(); j++) {
                    boolean error = j % 2 == 0 ? true : false;

                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    periferica.getEnsayos().get(j).setResultado(results);
                }

                PruebaShape pruebaShape = new PruebaShape(new ConfigEnsayoShapeDetectAuto(), random.nextInt(2, 3));
                for (int j = 0; j < pruebaShape.getEnsayos().size(); j++) {
                    boolean error = j % 3 == 0 ? true : false;
                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    pruebaShape.getEnsayos().get(j).setResultado(results);
                }

                PruebaEnrejado pruebaEnrejado = new PruebaEnrejado(new ConfigEnsayoEnrejadoAuto(), 3);
                for (int j = 0; j < pruebaEnrejado.getEnsayos().size(); j++) {
                    boolean error = j % 3 == 0 ? true : false;
                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    pruebaEnrejado.getEnsayos().get(j).setResultado(results);
                }

                PruebaGabor pruebaGabor = new PruebaGabor(new ConfigEnsayoGaborAuto(), 3);
                for (int j = 0; j < pruebaGabor.getEnsayos().size(); j++) {
                    boolean error = j % 3 == 0 ? true : false;
                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    pruebaGabor.getEnsayos().get(j).setResultado(results);
                }

                PruebaOrientacion pruebaOrientacion = new PruebaOrientacion(new ConfigEnsayoOrientacionAuto(), 3);
                for (int j = 0; j < pruebaOrientacion.getEnsayos().size(); j++) {
                    boolean error = j % 3 == 0 ? true : false;
                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    pruebaOrientacion.getEnsayos().get(j).setResultado(results);
                }

                PruebaVelocidad pruebaVelocidad = new PruebaVelocidad(new ConfigEnsayoVelocidadAuto(), 3);
                for (int j = 0; j < pruebaVelocidad.getEnsayos().size(); j++) {
                    boolean error = j % 3 == 0 ? true : false;
                    Resultado results = new Resultado(random.nextInt(300, 620),
                            error, "Descrip", 32, random.nextDouble(),
                            random.nextInt(5, 15));
                    pruebaVelocidad.getEnsayos().get(j).setResultado(results);
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
                String nombre = nombres[random.nextInt(0, nombres.length - 1)];
                String apellido1 = apellidos[random.nextInt(0, apellidos.length - 1)];
                String apellido2 = apellidos[random.nextInt(0, apellidos.length - 1)];
                String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
                String escuela = escuelas[random.nextInt(0, escuelas.length - 1)];

                String sexo = random.nextInt(0, 100) % 2 == 0 ? "Masculino" : "Femenino";
                String escolaridad = String.valueOf(random.nextInt(4, 6)) + "to";

                Paciente paciente = new Paciente(nombreCompleto, random.nextInt(5, 50),
                        sexo, escolaridad, historia, ci, "Ficha", escuela,
                        periferica, foveal, pruebaShape, pruebaGabor, pruebaEnrejado,
                        pruebaOrientacion, pruebaVelocidad);

                /*Paciente paciente = new Paciente(nombreCompleto, random.nextInt(5, 50),
                sexo, escolaridad, historia, ci, "Ficha",
                null, null, escuela, pruebaShape, null, null);*/


                registro.nuevoPaciente(paciente);
            }

            registro.salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);

        } catch (Exception ex) {
            Logger.getLogger(MainData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
