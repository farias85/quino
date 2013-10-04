/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.testing.main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farias
 */
public class TestBean {

    private String name;
    private int age;
    private int ci;
    private TinyBean tbean;
    private Staff staff;
    private List<Staff> ls = new ArrayList<Staff>();

    public List<Staff> getLs() {
        return ls;
    }

    public void setLs(List<Staff> ls) {
        this.ls = ls;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public TinyBean getTbean() {
        return tbean;
    }

    public void setTbean(TinyBean tbean) {
        this.tbean = tbean;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public TestBean() {
        this.name = "bean";
        this.age = 444;
    }

    public TestBean(String name, int age, int ci, TinyBean tbean, List<Staff> ls) {
        this.name = name;
        this.age = age;
        this.ci = ci;
        this.tbean = tbean;
        this.ls = ls;
    }

    // Getter and setter ...
    @Override
    public String toString() {
        return String.format("[TestBean: name='%s', age=%d]", name, age);
    }
}
