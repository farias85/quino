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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 04/07/2013.
 */
package quino.clases.config;

import java.awt.Toolkit;
import quino.clases.model.Velocidad;

public abstract class ConfigEnsayoSinusoide extends ConfigEnsayo {

    /**
     * sampling frequency in frames / s
     */
    protected int fs = 20;
    /**
     * spatial frequency in x, cicles / inch
     */
    protected double fspa_cpi_x = -0.4;
    /**
     * spatial frequency in y, cicles / inch
     */
    protected double fspa_cpi_y = 1.0;
    /**
     * screen pixels x inch
     */
    protected double ppi = Toolkit.getDefaultToolkit().getScreenResolution() / 3;
    /**
     * spatial frequency in x,cicles / pixels
     */
    protected double fspa_cpp_x = fspa_cpi_x / ppi;
    /**
     * spatial frequency in y,cicles / pixels
     */
    protected double fspa_cpp_y = fspa_cpi_y / ppi;
    /**
     * Sentido del movimiento de las barras, cuando es true y en dependencia de
     * los valores de fspa_cpi_x y fspa_cpi_y el sentido del movimiento es hacia
     * arriba o hacia la izquierda. Por ejemplo para sentidoUpLeft = true,
     * fspa_cpi_x = 0 y fspa_cpi_y = 1 => El movimiento es hacia arriba para
     * sentidoUpLeft = true, fspa_cpi_x = 1 y fspa_cpi_y = 0 => El movimiento es
     * hacia la izquierda Si cambiamos el valor de sentidoUpLeft a false en los
     * ejemplos anteriores entonces el movimiento sería hacia abajo para el
     * ejemplo 1 y hacia la derecha para el ejemplo 2 para los valores mismos
     * valores de fspa_cpi_x y fspa_cpi_y
     */
    protected boolean sentidoUpLeft = false;
    /**
     * Dirección del movimiento
     */
    protected int direccion = 1;
    /**
     * El contraste de las rejas
     */
    protected double contrat = 0.99;
    /**
     * La intensidad media q se desea tener entre rejas
     */
    protected double intensidadMedia = 128;
    /**
     * Intensidad máxima
     */
    protected double intensidadMax = contrat * intensidadMedia;

    protected Velocidad velocidadPrimaria = new Velocidad(24);

    protected double frecuenciaMuestreo = 8;

    public ConfigEnsayoSinusoide() {
        super();
    }

    public ConfigEnsayoSinusoide(int key, int panelEstimulo, int fs, double fspa_cpi_x,
            double fspa_cpi_y, double ppi, double fspa_cpp_x, double fspa_cpp_y,
            boolean sentidoUpLeft, int direccion, double contrat,
            double intensidadMedia) {
        super(key, panelEstimulo);

        this.fs = fs;
        this.fspa_cpi_x = fspa_cpi_x;
        this.fspa_cpi_y = fspa_cpi_y;
        this.ppi = ppi;
        this.fspa_cpp_x = fspa_cpp_x;
        this.fspa_cpp_y = fspa_cpp_y;
        this.sentidoUpLeft = sentidoUpLeft;
        this.direccion = direccion;
        this.contrat = contrat;
        this.intensidadMedia = intensidadMedia;

        this.intensidadMax = contrat * intensidadMedia;
    }

    public ConfigEnsayoSinusoide(int direccion, double ppi) {
        super(0, 0);
        this.ppi = ppi;
        getConfiguracionXDireccion(direccion);
    }

    public ConfigEnsayoSinusoide(int direccion, double ppi, double contrat,
            double intensidadMedia) {
        this(direccion, ppi);

        this.contrat = contrat;
        this.intensidadMedia = intensidadMedia;
        this.intensidadMax = contrat * intensidadMedia;
    }

    protected void getConfiguracionXDireccion(int direccion) {
        this.direccion = direccion;

        switch (direccion) {
            case 1: {
                fspa_cpi_x = 0;
                fspa_cpi_y = 1.0;
                sentidoUpLeft = true;
            }
            break;
            case 2: {
                fspa_cpi_x = 0;
                fspa_cpi_y = 1.0;
                sentidoUpLeft = false;
            }
            break;
            case 3: {
                fspa_cpi_x = 1.0;
                fspa_cpi_y = 0;
                sentidoUpLeft = false;
            }
            break;
            case 4: {
                fspa_cpi_x = 1.0;
                fspa_cpi_y = 0;
                sentidoUpLeft = true;
            }
            break;
            case 5: {
                fspa_cpi_x = -0.4;
                fspa_cpi_y = 1.0;
                sentidoUpLeft = true;
            }
            break;
            case 6: {
                fspa_cpi_x = 1.0;
                fspa_cpi_y = 1.0;
                sentidoUpLeft = true;
            }
            break;
            case 7: {
                fspa_cpi_x = 1.0;
                fspa_cpi_y = 1.0;
                sentidoUpLeft = false;
            }
            break;
            case 8: {
                fspa_cpi_x = -0.4;
                fspa_cpi_y = 1.0;
                sentidoUpLeft = false;
            }
            break;
            default:
                this.direccion = 0;
        }

        fspa_cpp_x = fspa_cpi_x / ppi;
        fspa_cpp_y = fspa_cpi_y / ppi;
    }

    public int getFs() {
        return fs;
    }

    public void setFs(int fs) {
        this.fs = fs;
    }

    public double getFspa_cpi_x() {
        return fspa_cpi_x;
    }

    public void setFspa_cpi_x(double fspa_cpi_x) {
        this.fspa_cpi_x = fspa_cpi_x;
    }

    public double getFspa_cpi_y() {
        return fspa_cpi_y;
    }

    public void setFspa_cpi_y(double fspa_cpi_y) {
        this.fspa_cpi_y = fspa_cpi_y;
    }

    public double getFspa_cpp_x() {
        return fspa_cpp_x;
    }

    public void setFspa_cpp_x(double fspa_cpp_x) {
        this.fspa_cpp_x = fspa_cpp_x;
    }

    public double getFspa_cpp_y() {
        return fspa_cpp_y;
    }

    public void setFspa_cpp_y(double fspa_cpp_y) {
        this.fspa_cpp_y = fspa_cpp_y;
    }

    public double getPpi() {
        return ppi;
    }

    public void setPpi(double ppi) {
        this.ppi = ppi;
    }

    public boolean isSentidoUpLeft() {
        return sentidoUpLeft;
    }

    public void setSentidoUpLeft(boolean sentidoUpLeft) {
        this.sentidoUpLeft = sentidoUpLeft;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public double getContrat() {
        return contrat;
    }

    public void setContrat(double contrat) {
        this.contrat = contrat;
    }

    public double getIntensidadMax() {
        return intensidadMax;
    }

    public void setIntensidadMax(double intensidadMax) {
        this.intensidadMax = intensidadMax;
    }

    public double getIntensidadMedia() {
        return intensidadMedia;
    }

    public void setIntensidadMedia(double intensidadMedia) {
        this.intensidadMedia = intensidadMedia;
    }

    public Velocidad getVelocidadPrimaria() {
        return velocidadPrimaria;
    }

    public void setVelocidadPrimaria(Velocidad velocidadPrimaria) {
        this.velocidadPrimaria = velocidadPrimaria;
    }

    public double getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }

    public void setFrecuenciaMuestreo(double frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }
}
