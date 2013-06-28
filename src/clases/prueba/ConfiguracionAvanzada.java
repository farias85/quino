/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.prueba;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author davisito
 */
public class ConfiguracionAvanzada  implements Serializable{
    private int duracion;
    private int t_interestimulo;

    public ConfiguracionAvanzada(int duracion, int t_interestimulo) {
        this.duracion = duracion;
        this.t_interestimulo = t_interestimulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getT_interestimulo() {
        return t_interestimulo;
    }

    public void setT_interestimulo(int t_interestimulo) {
        this.t_interestimulo = t_interestimulo;
    }
     public void SaveObject(String path) throws IOException, ClassNotFoundException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(this);
        out.close();
    }
    public static ConfiguracionAvanzada OpenObject(String path) throws IOException, ClassNotFoundException
    {
       ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
       ConfiguracionAvanzada pacientes = (ConfiguracionAvanzada)in.readObject();
       in.close();
       return pacientes;
    }
    
}
