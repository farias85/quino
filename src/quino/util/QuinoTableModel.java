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
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 02/07/2013.
 */
package quino.util;

import javax.swing.table.DefaultTableModel;
import quino.clases.model.Registro;

/**
 * Representa la configuración interna de datos q se le envía al jtable de la 
 * ventana principal
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
