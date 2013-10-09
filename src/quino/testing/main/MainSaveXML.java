/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.testing.main;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import quino.clases.config.ConfigEnsayoShapeDetectAuto;
import quino.clases.model.Resultado;
import quino.util.Aleatorio;
import quino.util.QuinoTools;
import quino.util.test.PruebaShape;

/**
 *
 * @author farias
 */
public class MainSaveXML {

    public static void main(String[] args) {

        /*Aleatorio a = new Aleatorio();
        System.out.println(a.nextFloat());*/

        long time1 = System.currentTimeMillis();
        //QuinoTools.cargarConfiguracion();
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);

        /*try {
            Aleatorio random = new Aleatorio();
            PruebaShape pruebaShape = new PruebaShape(new ConfigEnsayoShapeDetectAuto(), random.nextInt(2, 3));
            for (int j = 0; j < pruebaShape.getEnsayos().size(); j++) {
                boolean error = j % 3 == 0 ? true : false;
                Resultado results = new Resultado(random.nextInt(300, 620),
                        error, "Descrip", 32, random.nextDouble(),
                        random.nextInt(5, 15));
                pruebaShape.getEnsayos().get(j).setResultado(results);
            }

            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("testxml.xml")));
            e.writeObject(pruebaShape);
            e.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PruebaShape.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
