/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

/**
 *
 * @author farias
 */
public abstract class ConfigEnsayo {

    /**
     * Valor de la tecla presionada
     */
    protected int key;

    public ConfigEnsayo() {
    }

    public ConfigEnsayo(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
