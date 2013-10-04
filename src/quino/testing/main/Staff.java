/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.testing.main;

/**
 *
 * @author farias
 */
public class Staff {

    private String st;
    private double numXXX;

    public double getNumXXX() {
        return numXXX;
    }

    public void setNumXXX(double numXXX) {
        this.numXXX = numXXX;
    }

    public Staff() {
        st = "xc";
    }

    public Staff(String st) {
        this.st = st;
    }

    public Staff(String st, double numXXX) {
        this.st = st;
        this.numXXX = numXXX;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
}
