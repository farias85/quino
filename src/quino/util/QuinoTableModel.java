/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util;

import javax.swing.table.DefaultTableModel;
import quino.clases.model.Registro;

/**
 * Representa la configuración interna de datos q se le envía al jtable de la 
 * ventana principal
 * @author Felipe Rodriguez Arias
 */
public class QuinoTableModel extends DefaultTableModel {

    /**
     * Para la edicion de las celdas por columnas
     */
    private boolean[] celdasNoEditables = {false, false, false, false, false, false, false};

    /**
     * Define los tipos de datos q se presentan en cada columna de la tabla
     */
    private Class[] types = new Class[]{
        java.lang.Integer.class,
        java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
        java.lang.Integer.class, java.lang.String.class, java.lang.String.class
    };

    /**
     * Contruye el objeto QuinoTableModel a partir de un registro
     * @param registro
     */
    public QuinoTableModel(Registro registro) {
        super();

        addColumn("#");
        addColumn("Historia Clínica");
        addColumn("Nombre y Apellidos");
        addColumn("Carné de Identidad");
        addColumn("Edad");
        addColumn("Escolaridad");
        addColumn("Escuela");

        for (int i = 0; i < registro.getPacientes().size(); i++) {
            Object[] fila = {i + 1,
                registro.getPacientes().get(i).getHistoria(),
                registro.getPacientes().get(i).getNombre(),
                registro.getPacientes().get(i).getCi(),
                registro.getPacientes().get(i).getEdad(),
                registro.getPacientes().get(i).getEscolaridad(),
                registro.getPacientes().get(i).getEscuela()};
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
