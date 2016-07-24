package com.mmcoe.dbms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nachiket
 */
public class Student {
    int id,age, section;
    String name, clas;
    float gpa;
    
    public Student(int ID, int AGE, int SECTION, float GPA, String NAME, String CLAS) {
        id=ID;
        age=AGE;
        gpa=GPA;
        name = NAME;
        section=SECTION;
        clas=CLAS;
    }
    public int getID() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public float getGpa() {
        return gpa;
    }
    public String getName() {
        return name;
    }
    public int getSection() {
        return section;
    }
    public String getClas() {
        return clas;
    }
}
