/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.prueba;

import java.io.Serializable;
import java.util.ArrayList;


import java.util.Date;

/**
 *
 * @author davisito
 */
public class Prueba implements Serializable{
    private int cant_ensayos;
    private ArrayList<Results> resultados;
    private ConfiguracionAvanzada conf_avanzada;
    private Date fecha;
    private boolean fobeal;
    private int key;

    public Prueba(int cant_ensayos, ConfiguracionAvanzada conf) {
        this.cant_ensayos = cant_ensayos;
        resultados = new ArrayList<Results>();
        conf_avanzada = conf;
        
    }

    public Prueba() {
    }

    public boolean isFobeal() {
        return fobeal;
    }

    public void setFobeal(boolean fobeal) {
        this.fobeal = fobeal;
    }

    

    public int getCant_ensayos() {
        return cant_ensayos;
    }

    public ArrayList<Results> getResultados() {
        return resultados;
    }
    public void decre_Ensayos(){
        cant_ensayos--;
    }
    
    public void add_Result(Results resultado){
        resultados.add(resultado);
    }

    public int cant_Errores(){
        int count=0;
        for (int i = 0; i < resultados.size(); i++) {
            if(resultados.get(i).isError())
                count++;
        }
        return count;
    }
    public int densidad_Promedio(){
        int sum=0;
        int prom=0;
        for (int i = 0; i < resultados.size(); i++) {
            sum = sum+resultados.get(i).getDensidad();
        }
        prom=sum/resultados.size();
        return prom;
    }
    public int tr_Promedio()
    {
        int sum=0;
        int prom=0;
        int cont=0;
        for (int i = 0; i < resultados.size(); i++) {
            if(resultados.get(i).getT_respuesta()!=0){
                sum = sum+resultados.get(i).getT_respuesta();
                cont++;
            }
        }
        if(cont!=0){
            prom=sum/cont;
        }
        return prom;
    }

    public ConfiguracionAvanzada getConf_avanzada() {
        return conf_avanzada;
    }
    public void setKey(int k){
        this.key=k;
    }
    public int getKey(){
        return this.key;
    }
}
