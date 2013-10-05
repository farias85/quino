/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.config.ConfigEnsayoEnrejado;
import quino.clases.model.Ensayo;

/**
 *
 * @author farias
 */
public class PruebaEnrejado extends PruebaSingleEnsayo {

    public PruebaEnrejado(ConfigEnsayoEnrejado configEnsayo) {
        super();
        Ensayo ensayo = new Ensayo(configEnsayo);
        ensayos.add(ensayo);
    }

    public PruebaEnrejado(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaEnrejado() {
        super();
    }

    @Override
    public String toString() {
        return "Enrejado";
    }
}
