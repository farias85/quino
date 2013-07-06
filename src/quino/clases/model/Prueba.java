/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.clases.model;

import java.util.ArrayList;


import java.util.Date;

/**
 *
 * @author Felipao
 */
public class Prueba {

    private int cantEnsayos;
    private ArrayList<Resultado> resultados;
    private Date fecha;
    private boolean foveal;

    public Prueba() {
    }
    
    public Prueba(int cantEnsayos, boolean foveal) {
        this.cantEnsayos = cantEnsayos;
        resultados = new ArrayList<Resultado>();
        this.foveal = foveal;
    }

    public Prueba(int cantEnsayos, ArrayList<Resultado> resultados, Date fecha, boolean foveal) {
        this.cantEnsayos = cantEnsayos;
        this.resultados = resultados;
        this.fecha = fecha;
        this.foveal = foveal;
    }

    public int cantErrores() {
        int count = 0;
        for (int i = 0; i < resultados.size(); i++) {
            if (resultados.get(i).isError()) {
                count++;
            }
        }
        return count;
    }

    public int densidadPromedio() {
        int sum = 0;
        int prom = 0;
        for (int i = 0; i < resultados.size(); i++) {
            sum = sum + resultados.get(i).getDensidad();
        }
        prom = sum / resultados.size();
        return prom;
    }

    public int tiempoRespuestaPromedio() {
        int sum = 0;
        int prom = 0;
        int cont = 0;
        for (int i = 0; i < resultados.size(); i++) {
            if (resultados.get(i).getTiempoRespuesta() != 0) {
                sum = sum + resultados.get(i).getTiempoRespuesta();
                cont++;
            }
        }
        if (cont != 0) {
            prom = sum / cont;
        }
        return prom;
    }

    public int getCantEnsayos() {
        return cantEnsayos;
    }

    public void setCantEnsayos(int cantEnsayos) {
        this.cantEnsayos = cantEnsayos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isFoveal() {
        return foveal;
    }

    public void setFoveal(boolean foveal) {
        this.foveal = foveal;
    }

    public ArrayList<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<Resultado> resultados) {
        this.resultados = resultados;
    }
}
