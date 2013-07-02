/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.config;

/**
 *
 * @author Administrador
 */
public interface IConfiguracion {
    public int CANT_ENSAYOS = 2;
    public int TIEMPO_DURACION = 3000;
    public int TIEMPO_ESTIMULO = 1600;
    
    public int PC_EN_ESPERA = 30;
    public int PC_PREPARADO = 40;
    public int PC_ESPERANDO_RESPUESTA = 30;

    public String RESOURCES_LOCATION = "/quino/view/main/icons/";
}
