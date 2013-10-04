/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.testing.main;

/**
 *
 * @author farias
 */
public class TinyBean {

    private String name;
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public TinyBean(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public TinyBean() {
    }
}
