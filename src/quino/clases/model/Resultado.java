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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 28/06/2013.
 */
package quino.clases.model;

/**
 * Representa el resultado final luego de la ejecución de un ensayo
 */
public class Resultado {

    /**
     * Tiempo de respuesta a la acción del movimiento
     */
    private int tiempoRespuesta;
    /**
     * True si el resultado del ensayo es un error
     */
    private boolean error;
    /**
     * La descripción del resultado o del error
     */
    private String descripcion = "";
    /**
     * La tecla presionada luego del ensayo
     */
    private int key;
    /**
     * Velocidad del movimiento de los puntos
     */
    private double velocidad;
    /**
     * El angulo promedio de movimiento de los puntos
     */
    private double angulo;

    public Resultado() {
    }

    public Resultado(int tiempoRespuesta, boolean error, String descripcion,
            int key, double velocidad, double angulo) {
        this.tiempoRespuesta = tiempoRespuesta;
        this.error = error;
        this.descripcion = descripcion;
        this.key = key;
        this.velocidad = velocidad;
        this.angulo = angulo;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(int tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }
}
