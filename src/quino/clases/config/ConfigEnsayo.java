/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.config;

/**
 *
 * @author Felipe Rodriguez Arias
 */
public abstract class ConfigEnsayo {

    /**
     * Valor de la tecla presionada
     */
    protected int key;

    /**
     * Panel de estimulo en el q se produjo el movimiento
     */
    protected int panelEstimulo;

    public ConfigEnsayo() {
    }

    public ConfigEnsayo(int key, int panelEstimulo) {
        this.key = key;
        this.panelEstimulo = panelEstimulo;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getPanelEstimulo() {
        return panelEstimulo;
    }

    public void setPanelEstimulo(int panelEstimulo) {
        this.panelEstimulo = panelEstimulo;
    }
}
