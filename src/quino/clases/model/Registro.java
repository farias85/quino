/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Davisito
 */
public class Registro implements Serializable{
    private LinkedList<Paciente> pacientes;

    public Registro() {
        pacientes = new LinkedList<Paciente>();
    }
    public boolean exist_Paciente(Paciente paciente){
        for (int i = 0; i < pacientes.size(); i++) {
           if(paciente.getCI()==pacientes.get(i).getCI()||paciente.getNo_historia().matches(pacientes.get(i).getNo_historia())){
               return true;
           }
        }
        return false;
    }
    public int Buscar(String historia)throws Exception{
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getNo_historia().matches(historia)) {
                return i;
            }
        }
        throw  new Exception("No existe ningún paciente con ese número de Historia Clínica");
    }
    public void Nuevo (Paciente p)throws Exception
    {
        if (exist_Paciente(p)){
            Exception error = new Exception("Ya existe un paciente con ese número de Carné o Historia Clínica");
            throw error;
        }
        else{
            pacientes.add(p);
        }
    }
    public Paciente paciente_Pos(int pos)
    {
        return pacientes.get(pos);
    }
    public void Eliminar(int pos){
        pacientes.remove(pacientes.get(pos));
    }
    public void Eliminar(String historia)throws Exception{
        try{
            pacientes.remove(pacientes.get(Buscar(historia)));
        }
        catch(Exception e){
            throw e;
        }
    }
    public void Modificar(int pos, Paciente p){
        pacientes.get(pos).setCI(p.getCI());
        pacientes.get(pos).setEdad(p.getEdad());
        pacientes.get(pos).setEscolaridad(p.getEscolaridad());
        pacientes.get(pos).setFicha(p.getFicha());
        pacientes.get(pos).setNo_historia(p.getNo_historia());
        pacientes.get(pos).setNombre(p.getNombre());
        pacientes.get(pos).setSexo(p.getSexo());
    }
    public void Modificar(String historia, Paciente p)throws Exception{
        int pos;
        try{
            pos=Buscar(historia);
            pacientes.get(pos).setCI(p.getCI());
            pacientes.get(pos).setEdad(p.getEdad());
            pacientes.get(pos).setEscolaridad(p.getEscolaridad());
            pacientes.get(pos).setFicha(p.getFicha());
            pacientes.get(pos).setNo_historia(p.getNo_historia());
            pacientes.get(pos).setNombre(p.getNombre());
            pacientes.get(pos).setSexo(p.getSexo());
        }
        catch(Exception e){
            throw e;
        }
    }
    public int Size(){
        return pacientes.size();
    }
    public void SaveObject(String path) throws IOException, ClassNotFoundException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(this);
        out.close();
    }
    public static Registro OpenObject(String path) throws IOException, ClassNotFoundException
    {
       ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
       Registro pacientes = (Registro)in.readObject();
       in.close();
       return pacientes;
    }
    public void Importar(Registro base)throws Exception {
        if(base.Size()==0){
            throw new Exception("La base que trata de importar no tiene pacientes");
        }else{
            for (int i = 0; i < base.Size(); i++) {
                this.Nuevo(base.paciente_Pos(i));
            }
        }
    }
}
