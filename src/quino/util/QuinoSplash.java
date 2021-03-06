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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

public final class QuinoSplash {

    final SplashScreen splash;
    //texto que se muestra a medida que se va cargando el screensplah
    final String[] texto = {"configuración", "librerías",
        "formularios", "iconos", "propiedades",
        "archivos XML", "pacientes", "pruebas",
        "base de datos", "servicios", "resultados",
        "ensayos"};

    public QuinoSplash() {
        splash = SplashScreen.getSplashScreen();
    }

    public void animar() {
        if (splash != null) {
            Graphics2D g = splash.createGraphics();
            Punto punto = new Punto(180.0, 150.0);

            for (int i = 1; i < texto.length; i++) {
                //se pinta texto del array
                g.setColor(new Color(4, 52, 101));//color de fondo
                g.fillRect((int)punto.getX(), (int)punto.getY(), 280, 12);//para tapar texto anterior
                g.setColor(Color.white);//color de texto
                g.drawString("Cargando " + texto[i - 1] + "...", (int)punto.getX(), (int)punto.getY() + 10);
                g.setColor(Color.green);//color de barra de progeso
                g.fillRect((int)punto.getX() + 1, (int)punto.getY() - 20, (i * 307 / texto.length), 12);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                float dash1[] = {2.0f};
                BasicStroke dashed = new BasicStroke(9.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
                g.setStroke(dashed);
                g.setColor(Color.GREEN);//color de barra de progeso
                g.setColor(new Color(4, 52, 101));
                g.drawLine((int)punto.getX() + 2, (int)punto.getY() - 14, (int)punto.getX() + 307, (int)punto.getY() - 14);
                //se actualiza todo
                splash.update();
                try {
                    Thread.sleep(/*321*/2);
                } catch (InterruptedException e) {
                }
            }
            splash.close();
        }
        //una vez terminada la animación se muestra la aplicación principal
        try {
            //new PrincipalView().setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
