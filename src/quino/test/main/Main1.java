/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.test.main;

import quino.clases.config.ConfigApp;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;
import quino.clases.config.IConfigApp;
import quino.clases.model.Paciente;
import quino.clases.model.Prueba;
import quino.clases.model.Registro;
import quino.clases.model.Resultado;
import quino.util.QuinoTools;

/**
 *
 * @author Administrador
 */
public class Main1 {

    public static void main(String[] args) {
        try {
            /*Results results = new Results(12, 12, 1, 2, 23, true, 3, true, 4, "Descrip", 1, true, 2, 23);

            Prueba foveal = new Prueba(3, new ConfiguracionAvanzada(IConfiguracion.TIEMPO_DURACION, IConfiguracion.TIEMPO_ESTIMULO));
            foveal.add_Result(results);

            Prueba periferica = new Prueba(4, new ConfiguracionAvanzada(IConfiguracion.TIEMPO_DURACION, IConfiguracion.TIEMPO_ESTIMULO));
            periferica.add_Result(results);

            Paciente paciente = new Paciente("Felipe", 23, "Masculino", "Primero", "4512", 34, "ficha");
            paciente.setPrueba(periferica);
            paciente.setFobeal(foveal);

            LinkedList<Paciente> ll = new LinkedList<Paciente>();
            ll.add(paciente);

            Registro registro = new Registro(ll);*/

            //registro.Nuevo(paciente);
            /*TinyBean tbean = new TinyBean("tbean", 12121);
            Staff staff = new Staff("felipon-pon");
            List<Staff> ls = new ArrayList<Staff>();
            ls.add(staff);

            Staff staff2 = new Staff("fel", 45);
            ls.add(staff2);
            
            TestBean bean = new TestBean("xxxx", 111, 2, tbean, ls);*/

            /*ConfigApp impl = new ConfigApp(ConfigApp.CANT_ENSAYOS,
            ConfigApp.TIEMPO_DURACION, ConfigApp.PC_EN_ESPERA,
            ConfigApp.PC_PREPARADO, ConfigApp.PC_ESPERANDO_RESPUESTA);

            XMLEncoder e = new XMLEncoder(
            new BufferedOutputStream(
            new FileOutputStream(IConfigApp.CONFIG_FILE_NAME)));

            e.writeObject(impl);
            e.close();*/


            /*XMLDecoder d = new XMLDecoder(
            new BufferedInputStream(
            new FileInputStream("registro.xml")));
            TestBean result = (TestBean) (d.readObject());
            d.close();

            System.out.println(result);*/

            /*XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
            new FileOutputStream("registro.xml")));
            encoder.writeObject(bean);
            encoder.close();*/

            /*try {
            FileOutputStream xmlos = new FileOutputStream("registro.xml");
            XMLEncoder encoder = new XMLEncoder(xmlos);
            encoder.writeObject(registro);
            encoder.close();
            xmlos.close();

            } catch (Exception e) {
            System.out.println(e.getMessage());
            }*/

            //QuinoTools.salvarConfiguracion();
            int con1 = ConfigApp.CANT_ENSAYOS;

            QuinoTools.cargarConfiguracion();

            int con = ConfigApp.CANT_ENSAYOS;
            double a = con /2;

        } catch (Exception ex) {
            Logger.getLogger(Main1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
