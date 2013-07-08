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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representa el registro de pacientes y pruebas del sistema
 * @author Felipe Rodriguez Arias
 */
public class Registro {

    /**
     * Lista de los pacientes del registro q se maneja en la aplicación
     */
    private LinkedList<Paciente> pacientes;

    public Registro() {
        pacientes = new LinkedList<Paciente>();
    }

    public Registro(LinkedList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * Chequea si existe o no un paciente en el registro
     * @param paciente El objeto paciente q se desea buscar
     * @return True si existe el paciente en el registro, false en caso contrario
     */
    public boolean existePaciente(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (paciente.getCi() == pacientes.get(i).getCi() || paciente.getHistoria().equals(pacientes.get(i).getHistoria())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un paciente en el registro por su historia clinica
     * @param historia El identificador de la historia clinica
     * @return El objeto paciente de dicha historia clinica
     * @throws Exception Lanza una excepcion si no existiera ningun paciente
     * con dicha historia clinica
     */
    public Paciente buscarPaciente(String historia) throws Exception {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getHistoria().equals(historia)) {
                return pacientes.get(i);
            }
        }
        throw new Exception("No existe ningún paciente con ese número de historia clínica");
    }

    /**
     * Agrega un paciente al registro
     * @param p El paciente para adicionar
     * @return True si se logró la adición, false en caso contrario
     */
    public boolean nuevoPaciente(Paciente p) {
        if (existePaciente(p)) {
            return false;
        }

        pacientes.add(p);
        return true;
    }

    /**
     * Elimina un paciente dada su historia clinica
     * @param historia El identificador de la historia clinica
     * @throws Exception
     */
    public void eliminarXHistoria(String historia) throws Exception {
        try {
            pacientes.remove(buscarPaciente(historia));
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error eliminando al paciente.");
        }
    }

    /**
     * Salva los datos del registro en un fichero
     * @param path La direccion del fichero
     */
    public void salvarRegistro(String path){
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
            e.writeObject(this);
            e.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga los datos de un registro desde un fichero
     * @param path la dirección del fichero
     * @return El registro cargado
     */
    public static Registro cargarRegistro(String path) {
        Registro result = null;
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
            result = (Registro) (d.readObject());
            d.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Agrega nuevo pacientes de un registro existente en un fichero .tls
     * al registro principal del sistema
     * @param nuevo El nuevo registro q se desea cargar
     * @throws Exception
     */
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
