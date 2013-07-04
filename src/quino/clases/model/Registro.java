/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Felipao
 */
public class Registro {

    private LinkedList<Paciente> pacientes;

    public Registro() {
        pacientes = new LinkedList<Paciente>();
    }

    public Registro(LinkedList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public boolean existePaciente(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (paciente.getCi() == pacientes.get(i).getCi() || paciente.getHistoria().equals(pacientes.get(i).getHistoria())) {
                return true;
            }
        }
        return false;
    }

    public Paciente buscarPaciente(String historia) throws Exception {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getHistoria().equals(historia)) {
                return pacientes.get(i);
            }
        }
        throw new Exception("No existe ningún paciente con ese número de historia clínica");
    }

    public boolean nuevoPaciente(Paciente p) {
        if (existePaciente(p)) {
            return false;
        }

        pacientes.add(p);
        return true;
    }

    public void eliminarXHistoria(String historia) throws Exception {
        try {
            pacientes.remove(buscarPaciente(historia));
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error eliminando al paciente.");
        }
    }

    public void salvarRegistro(String path) throws IOException, ClassNotFoundException {
        
        XMLEncoder e = new XMLEncoder(
                new BufferedOutputStream(
                new FileOutputStream(path)));

        e.writeObject(this);
        e.close();
    }

    public static Registro cargarRegistro(String path) throws IOException, ClassNotFoundException {
        
        XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                new FileInputStream(path)));
        Registro result = (Registro) (d.readObject());
        d.close();

        return result;
    }

    public void importarRegistro(Registro nuevo) throws Exception {
        String msg = "";
        if (nuevo.getPacientes().size() == 0) {
            throw new Exception("La base que trata de importar no tiene pacientes");
        } else {
            for (int i = 0; i < nuevo.getPacientes().size(); i++) {
                if (!nuevoPaciente(nuevo.getPacientes().get(i))) {
                    msg += nuevo.getPacientes().get(i).getHistoria() + ", ";
                }
            }
        }

        if (!msg.equals("")) {
            throw new Exception("Los pacientes de hc número: \n"
                    + msg + "no se adicionaron debido a que su hc está repetida");
        }
    }

    public LinkedList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(LinkedList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
