/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

/**
 *
 * @author Mega Store
 */
public class finalc {
    
    int id ;
    String name ;
    int hours;
    String profname;
    int noofstudent;  

    public finalc(int id, String name, int hours, String profname, int noofstudent) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.profname = profname;
        this.noofstudent = noofstudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getProfname() {
        return profname;
    }

    public void setProfname(String profname) {
        this.profname = profname;
    }

    public int getNoofstudent() {
        return noofstudent;
    }

    public void setNoofstudent(int noofstudent) {
        this.noofstudent = noofstudent;
    }
    
    
    
}
