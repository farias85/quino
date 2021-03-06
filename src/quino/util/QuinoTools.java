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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 02/07/2013.
 */
package quino.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.opencv.core.Mat;
import quino.clases.config.ConfigApp;
import quino.util.test.Prueba;
import quino.util.test.PruebaEnrejado;
import quino.util.test.PruebaFormaA;
import quino.util.test.PruebaGabor;
import quino.util.test.PruebaFormaB;
import quino.util.test.PruebaOrientacion;
import quino.util.test.PruebaShape;
import quino.util.test.PruebaVelocidad;
import quino.view.main.ErrorDialog;
import quino.view.main.PrincipalView;

/**
 * Todos los métodos de esta clase son estáticos. Se definen acá métodos de
 * utilidad en el trabajo en toda la aplicación
 */
public class QuinoTools {

    public static float[] VELOCIDAD = {1.5f, 3.0f, 6.0f, 12.0f, 24.0f};

    public static float[] FRECUENCIA_ESPACIAL = {/*0.01f, 0.0125f, 0.025f,*/ 0.04f, 0.05f, 0.057f, 0.07f, 0.1f,
         0.15f, 0.17f, 0.173f, 0.25f, 0.3f, 0.5f, 0.59f, 0.9f,/* 0.93f, 1.17f, 1.4f, 1.559f, 2.34f, 4.0f, 8.0f*/};

    public static float[] FRECUENCIA_TEMPORAL = {/*0.5f, 1.0f, 2.0f, 3.0f, 4.0f, 6.0f, 7.0f, 8.0f, */10.0f, 12.0f, 18.0f, 24.0f};

    public static double getPeriodoXFrecuencia(double frecuencia) {

        //calculo el período y lo multiplico por 1000 pa convertirlo en en milisegundo
        //que es lo que utiliza el parámetro del timertask
        double periodo = 1 / frecuencia;
        periodo *= 1000;

        return periodo;
    }

    public static double getK2() {
        //distancia por defecto entre el sujeto y la pantalla 60 cm
        double distanciaDeVisualizacion = 0.60d;
        //distancia de un grado en la pantalla
        double dist = distanciaDeVisualizacion * Math.tan(1);

        //calcular pixeles por segundo
        //Factor de Kell para pantallas CRT
        double k = 0.7;
        double k2 = dist * k;

        return k2 * 20;
    }

    /**
     * Este método se utiliza para encontrar el ppi de una frecuencia especifica
     * Tambien para encontrar la frecuencia de un ppi especifico
     *
     * @param frecuenciaEspacial
     * @return
     */
    public static double getPPiXFrecuenciaEspacial(double frecuenciaEspacial) {
        double k2 = getK2();
        return 1 / frecuenciaEspacial * k2;
    }

    public static String getDireccion(int value) {
        String result = "";
        switch (value) {
            case 104:
            case 1:
                result += "Arriba";
                break;
            case 98:
            case 2:
                result += "Abajo";
                break;
            case 102:
            case 3:
                result += "Derecha";
                break;
            case 100:
            case 4:
                result += "Izquierda";
                break;
            case 105:
            case 5:
                result += "Derecha + Arriba";
                break;
            case 103:
            case 6:
                result += "Izquierda + Arriba";
                break;
            case 99:
            case 7:
                result += "Derecha + Abajo";
                break;
            case 97:
            case 8:
                result += "Izquierda + Abajo";
                break;
            case 32:
                result += "Barra Espaciadora";
                break;
            default:
                result += "Otro";
        }

        return result;
    }

    public static double calcAlphaCronbach(ArrayList<double[]> data) {

        double cronbach = 0;

        if (!data.isEmpty()) {
            int itemsCount = data.get(0).length;

            double[] vSuma = new double[data.size()];
            double[] vVarianza = new double[itemsCount];

            for (int i = 0; i < data.size(); i++) {
                vSuma[i] = sumVector(data.get(i));
            }

            for (int i = 0; i < itemsCount; i++) {
                vVarianza[i] = calcVarianza(getElements(data, i));
            }

            double varianzaSuma = calcVarianza(vSuma);

            //calcular valor de k
            double k = (double) itemsCount / ((double) itemsCount - 1);

            //sumar varianzas de los datos de la variable data 
            //sin contar el valor varianzaSuma
            double sumaVarianzas = sumVector(vVarianza);

            //calcular coeficiente de cronbach
            cronbach = k * (1 - (sumaVarianzas / varianzaSuma));
        }

        return cronbach;
    }

    private static double[] getElements(ArrayList<double[]> data, int pos) {
        double[] result = new double[data.size()];

        int i = 0;
        for (double[] ds : data) {
            result[i] = ds[pos];
            i++;
        }

        return result;
    }

    public static double calcVarianza(double[] vector) {
        double media = calcMedia(vector);
        double[] aux = new double[vector.length];

        int i = 0;
        for (double d : vector) {
            aux[i] = Math.pow(d - media, 2);
            i++;
        }

        return calcMedia(aux);
    }

    public static double calcMedia(double[] vector) {
        return sumVector(vector) / vector.length;
    }

    public static double sumVector(double[] vector) {
        double result = 0;
        for (double d : vector) {
            result += d;
        }
        return result;
    }

    public static int escolaridad2Int(String escolaridad) {
        int result = 0;
        try {
            result = Integer.parseInt(escolaridad);
            return result;
        } catch (Exception e) {
            try {
                String str = escolaridad.substring(escolaridad.length() - 3, escolaridad.length() - 2);
                result = Integer.parseInt(str);
                return result;
            } catch (Exception e2) {
            }
        }
        return result;
    }

    /**
     * Devuelve el ancho q debe tener una celda para que todo el texto q
     * contiene una celda se vea en la celda y no se solape con otras celdas
     * vecinas. Una celda por defecto tiene valor de width de 2048 y caben en
     * ella 9 caracteres mas o menos
     *
     * @param strlength El tamaño del texto str.length
     * @return El ancho de la celda
     */
    public static int getColumnWidthSize(int strlength) {
        return (int) (strlength * 2600 / 9);
    }

    /**
     * Devuelve el codigo ascii de la tecla presionada. Especificamente las
     * teclas del num lock con las cuales se controla el movimiento
     *
     * @param direccion Ladireccion del movimiento
     * @return El codigo ascci de la tecla presionada
     */
    public static int getKeyDireccion(int direccion) {
        int key = -1;
        switch (direccion) {
            case 1:
                key = 104;
                break;
            case 2:
                key = 98;
                break;
            case 3:
                key = 102;
                break;
            case 4:
                key = 100;
                break;
            case 5:
                key = 105;
                break;
            case 6:
                key = 103;
                break;
            case 7:
                key = 99;
                break;
            case 8:
                key = 97;
                break;
            default:
                key = -1;
        }
        return key;
    }

    /**
     * Devuelve el nombre del panel en el que ha ocurrido el movimiento
     *
     * @param prueba El tipo de prueba efectuada
     * @param panel El número del panel donde se efectuó el movimiento
     * @return El nombre del panel
     */
    public static String getPanelMovimiento(Prueba prueba, int panel) {
        if (prueba instanceof PruebaFormaA) {
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

    /**
     * Salva una prueba en el registro. Luego de la realización de una prueba si
     * existía en el registro del paciente una prueba igual q la q se realizó se
     * pregunta si se desea sobre escrbir la antigua prueba.
     *
     * @param principalView Ventana Principal
     * @param testView Ventana de la Prueba (Fovela o Periférica)
     * @param prueba Tipo de prueba realizada
     */
    public static void salvarPruebaEnRegistro(PrincipalView principalView, JDialog testView, Prueba prueba) {
        int option = -1;

        /*Prueba pruebaActual = prueba instanceof PruebaFoveal ? principalView.getPacienteActual().getFoveal()
         : principalView.getPacienteActual().getPeriferica();*/
        //String nombrePrueba = prueba instanceof PruebaFoveal ? "Foveal" : "Periférica";
        Prueba pruebaPaciente = null;
        if (prueba instanceof PruebaFormaA) {
            pruebaPaciente = principalView.getPacienteActual().getFoveal();
        } else if (prueba instanceof PruebaFormaB) {
            pruebaPaciente = principalView.getPacienteActual().getPeriferica();
        } else if (prueba instanceof PruebaShape) {
            pruebaPaciente = principalView.getPacienteActual().getForma();
        } else if (prueba instanceof PruebaVelocidad) {
            pruebaPaciente = principalView.getPacienteActual().getVelocidad();
        } else if (prueba instanceof PruebaGabor) {
            pruebaPaciente = principalView.getPacienteActual().getGabor();
        } else if (prueba instanceof PruebaEnrejado) {
            pruebaPaciente = principalView.getPacienteActual().getEnrejado();
        } else if (prueba instanceof PruebaOrientacion) {
            pruebaPaciente = principalView.getPacienteActual().getOrientacion();
        }

        try {
            if (pruebaPaciente != null) {
                option = JOptionPane.showConfirmDialog(testView, "El(La) paciente " + principalView.getPacienteActual().getNombre() + "\n"
                        + "tiene registrado(a) una prueba de tipo " + pruebaPaciente.toString() + "\n"
                        + "¿Desea sobreescribir la prueba realizada?", "Advertencia", JOptionPane.YES_NO_OPTION);
                switch (option) {
                    case 0: {
                        actualizarPruebaDePaciente(principalView, prueba);
                    }
                    break;
                    case 1:
                        break;
                }
            } else {
                actualizarPruebaDePaciente(principalView, prueba);
            }
        } catch (Exception e) {
            ErrorDialog err = new ErrorDialog(principalView, true, "No se ha podido guardar la prueba");
            err.setVisible(true);
        }
    }

    private static void actualizarPruebaDePaciente(PrincipalView principalView, Prueba prueba) {

        if (prueba instanceof PruebaFormaA) {
            principalView.getPacienteActual().setFoveal((PruebaFormaA) prueba);
        } else if (prueba instanceof PruebaFormaB) {
            principalView.getPacienteActual().setPeriferica((PruebaFormaB) prueba);
        } else if (prueba instanceof PruebaShape) {
            principalView.getPacienteActual().setForma((PruebaShape) prueba);
        } else if (prueba instanceof PruebaVelocidad) {
            principalView.getPacienteActual().setVelocidad((PruebaVelocidad) prueba);
        } else if (prueba instanceof PruebaGabor) {
            principalView.getPacienteActual().setGabor((PruebaGabor) prueba);
        } else if (prueba instanceof PruebaEnrejado) {
            principalView.getPacienteActual().setEnrejado((PruebaEnrejado) prueba);
        } else if (prueba instanceof PruebaOrientacion) {
            principalView.getPacienteActual().setOrientacion((PruebaOrientacion) prueba);
        }

        principalView.getRegistro().salvarRegistro(ConfigApp.REGISTRO_FILE_NAME);
        principalView.modificarTableModel();
    }

    /**
     * Dado una cadena de caracteres, devuelve true si cada valor del String es
     * un número
     *
     * @param valor Cadena de caracteres del suppuesto número
     * @return True si es un numero, false en caso contrario.
     */
    private static boolean isNumero(String valor) {

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

    /**
     * Chequea si el carnet de identidad de un paciente es válido
     *
     * @param ci Cadena de caracteres del CI del paciente
     * @return El carnét de identidad formateado a long
     * @throws Exception lanza excepción en caso de no ser válido el carné
     */
    public static long checkCI(String ci) throws Exception {
        int[] DaysByMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isNumero(ci)) //Si todos sus elementos son números
        {
            if (ci.length() == 11) //Si contiene 11 dígitos
            {
                int day = new Integer(String.valueOf(ci.charAt(4)) + String.valueOf(ci.charAt(5))).intValue();
                int month = new Integer(String.valueOf(ci.charAt(2)) + String.valueOf(ci.charAt(3))).intValue();
                //int year = new Integer(String.valueOf(ci.charAt(0)) + String.valueOf(ci.charAt(1))).intValue();

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

    /**
     * Salva un libro excel en la dirección dada.
     *
     * @param path Dirección para guardar el archivo
     * @param book Libro excel a guardar
     */
    public static void salvarLibroExcel(String path, HSSFWorkbook book) {
        FileOutputStream archivoSalida = null;
        try {
            File objFile = new File(path);
            archivoSalida = new FileOutputStream(objFile);
            book.write(archivoSalida);
            archivoSalida.close();
        } catch (Exception ex) {
            Logger.getLogger(QuinoTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * salva la configuración de la aplicación
     */
    public static void salvarConfiguracion() {
        try {
            ConfigApp impl = new ConfigApp(ConfigApp.CANT_ENSAYOS, ConfigApp.TIEMPO_DURACION,
                    ConfigApp.PC_EN_ESPERA, ConfigApp.PC_PREPARADO, ConfigApp.PC_ESPERANDO_RESPUESTA);
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(ConfigApp.CONFIG_FILE_NAME)));
            e.writeObject(impl);
            e.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga la configuración de la aplicación
     */
    public static void cargarConfiguracion() {
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(ConfigApp.CONFIG_FILE_NAME)));
            ConfigApp impl = (ConfigApp) (d.readObject());
            d.close();

            ConfigApp.CANT_ENSAYOS = impl.getCantEnsayos();
            ConfigApp.TIEMPO_DURACION = impl.getTiempoDuracion();

            ConfigApp.PC_EN_ESPERA = impl.getPcEnEspera();
            ConfigApp.PC_PREPARADO = impl.getPcPreparado();
            ConfigApp.PC_ESPERANDO_RESPUESTA = impl.getPcEsperandoRespuesta();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuinoTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve el valor del porciento de tiempo que se utiliza en una tarea a
     * partir del tiempo de duración total IConfiguracion.TIEMPO_DURACION
     *
     * @param porcentaje Porcentaje asignado en IConfiguracion para la tarea en
     * cuestión, por ejemplo: IConfiguracion.PC_EN_ESPERA
     * @return El valor del porciento respecto al tiempo de duración
     */
    public static int porcientoDuracion(double porcentaje) {
        return (int) (porcentaje * ConfigApp.TIEMPO_DURACION / 100);
    }

    /**
     * Devuelve el valor del porciento de tiempo que se utiliza en una tarea a
     * partir del tiempo de duración total tiempoDuracion
     *
     * @param porcentaje Valor del procentaje
     * @return El valor del porciento respecto al tiempo de duración
     */
    public static double porciento(double porcentaje, double tiempoDuracion) {
        //return Double.parseDouble(ConfigApp.DECIMAL_FORMAT.format(porcentaje * tiempoDuracion / 100));
        return Math.rint((porcentaje * tiempoDuracion / 100) * 100) / 100;
    }

    /**
     * Calcula la distancia desde el punto p1 a punto p2
     *
     * @param p1 Punto de inicio
     * @param p2 Punto de fin
     * @return Distancia entre ambos puntos
     */
    public static double getDistancia(Punto p1, Punto p2) {
        Point pt1 = new Point((int) p1.getX(), (int) p1.getY());
        Point pt2 = new Point((int) p2.getX(), (int) p2.getY());

        //Resolución en puntos x pulgada
        int resolucion = Toolkit.getDefaultToolkit().getScreenResolution();
        //Una pulgada tiene 2.54 cm, la distancia se da en cm
        double dst = pt1.distance(pt2) / resolucion * 2.54;

        return dst;
    }

    /**
     * Calcula el angulo P1-PA-P2, donde PA es vértices del ángulo y P1 y P2 los
     * extremos. PA representa la posición del paciente frente a la pantalla
     *
     * @param p1 Punto 1 de inicio del movimiento
     * @param p2 Punto 2 de fin
     * @return El ángulo en el vértice PA
     */
    public static double getAngulo(Punto p1, Punto p2) {
        double distancia = getDistancia(p1, p2);
        //60cm de separación del paciente y la pantalla mirando al punto central
        //tangente = seno / coseno; cateto opuesto / cateto adyacente
        double anguloRad = Math.atan(distancia / 60);
        double anguloGrados = Math.toDegrees(anguloRad);

        return Math.rint(anguloGrados * 100) / 100;
    }

    /**
     * Calcula el angulo P1-PA-P2, donde PA es vértices del ángulo y P1 y P2 los
     * extremos. PA representa la posición del paciente frente a la pantalla y
     * P1 es el centro de la pantalla
     *
     * @param p2 Punto de la pantalla
     * @return El ángulo en el vértice PA
     */
    public static double getAngulo(Punto p2) {
        Punto p1 = getCentroPantalla();
        return getAngulo(p1, p2);
    }

    /**
     * Calcula el angulo P1-PA-P2, donde PA es vértices del ángulo y P1 y P2 los
     * extremos. PA representa la posición del paciente frente a la pantalla y
     * P1 es el centro de la pantalla
     *
     * @param p2 Punto de la pantalla
     * @param desplazamientX El desplazamiento en el eje X respecto al borde
     * lateral izquierdo de la pantalla
     * @param desplazamientoY El desplazamiento en el eje Y respecto al borde
     * superior de la pantalla
     * @return El ángulo en el vértice PA
     */
    public static double getAngulo(Punto p2, double desplazamientX, double desplazamientoY) {
        Punto p1 = getCentroPantalla();
        p2 = new Punto(p2.getX() + desplazamientX, p2.getY() + desplazamientoY);
        return getAngulo(p1, p2);
    }

    /**
     * Devuelve las cordenadas del centro de la pantalla
     *
     * @return El Punto(X,Y) del centro de la pantalla
     */
    public static Punto getCentroPantalla() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double x = d.getWidth() / 2;
        double y = d.getHeight() / 2;
        return new Punto(x, y);
    }

    /**
     * Calcula la velocidad de traslación de los puntos
     *
     * @param tiempoMovimiento El tiempo de movimiento, debe ser un valor entre
     * y 200
     * @return
     */
    public static double getVelocidad(double tiempoMovimiento) {
        double distancia = (4.00 / Toolkit.getDefaultToolkit().getScreenResolution()) * 2.5;
        double velocidad = distancia / tiempoMovimiento;
        velocidad = Math.rint(velocidad * 100000) / 100000;
        //velocidad = Double.parseDouble(ConfigApp.DECIMAL_FORMAT.format(velocidad));
        return velocidad;
    }

    /**
     * Convierte una matrix de tipo Mat de opencv a BufferedImage para mostrar
     * por pantalla
     *
     * @param matrix Matrix de la imagen generada con opencv
     * @return La representación de la imagen en el tipo BufferedImage
     */
    public static BufferedImage matToBufferedImage(Mat matrix) {
        int cols = matrix.cols();
        int rows = matrix.rows();
        int elemSize = (int) matrix.elemSize();
        byte[] data = new byte[cols * rows * elemSize];
        int type;
        matrix.get(0, 0, data);
        switch (matrix.channels()) {
            case 1:
                type = BufferedImage.TYPE_BYTE_GRAY;
                break;
            case 3:
                type = BufferedImage.TYPE_3BYTE_BGR;
                // bgr to rgb
                byte b;
                for (int i = 0; i < data.length; i = i + 3) {
                    b = data[i];
                    data[i] = data[i + 2];
                    data[i + 2] = b;
                }
                break;
            default:
                return null;
        }
        BufferedImage image2 = new BufferedImage(cols, rows, type);
        image2.getRaster().setDataElements(0, 0, cols, rows, data);
        return image2;
    }

    public static String getNombreFigura(int numFigura) {
        String nombre = "";
        switch (numFigura) {
            case 1:
                nombre = "Cuadrado";
                break;
            case 2:
                nombre = "Rectángulo";
                break;
            case 3:
                nombre = "Triangulo";
                break;
            case 4:
                nombre = "Círculo";
                break;
        }
        return nombre;
    }
}
