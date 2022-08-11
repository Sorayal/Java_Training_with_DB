package main_package;


import jakarta.persistence.*;

//@Entity
//@Table(name="Student")
public class Student {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "student_id", nullable = false)
    private int id;
    private String surName;
    private String lastName;
    private String city;

    public Student(String surName, String lastName, String city){
        super();
        this.surName = surName;
        this.lastName = lastName;
        this.city = city;
    }

    public Student(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
