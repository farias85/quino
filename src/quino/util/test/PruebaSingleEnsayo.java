/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util.test;

import java.util.ArrayList;
import java.util.Date;
import quino.clases.model.Ensayo;

/**
 *
 * @author farias
 */
public abstract class PruebaSingleEnsayo extends Prueba {

    public PruebaSingleEnsayo(Date fecha, ArrayList<Ensayo> ensayos) {
        super(fecha, ensayos);
    }

    public PruebaSingleEnsayo() {
        super();
    }
}
