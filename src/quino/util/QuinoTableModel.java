/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import javax.swing.table.DefaultTableModel;
import quino.clases.model.Registro;

/**
 *
 * @author farias
 */
public class QuinoTableModel extends DefaultTableModel {

    private Registro registro;

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }
    boolean[] celdasNoEditables = {false, false, false, false, false, false};
    Class[] types = new Class[]{
        java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
    };

    public QuinoTableModel(Registro registro) {
        super();
        
        addColumn("Historia Clínica");
        addColumn("Nombre y Apellidos");
        addColumn("Carné de Identidad");
        addColumn("Edad");
        addColumn("Escolaridad");
        addColumn("Sexo");

        for (int i = 0; i < registro.getPacientes().size(); i++) {
            Object[] fila = {registro.getPacientes().get(i).getHistoria(), registro.getPacientes().get(i).getNombre(),
                registro.getPacientes().get(i).getCi(), registro.getPacientes().get(i).getEdad(),
                registro.getPacientes().get(i).getEscolaridad(), registro.getPacientes().get(i).getSexo()};
            addRow(fila);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int fila, int columna) {
        return celdasNoEditables[columna];
    }
}
