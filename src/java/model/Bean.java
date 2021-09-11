/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mr. Savagery
 */
public class Bean {
    
    private int id;
    private String name;
    private String number;
    private String email;

    public Bean(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }
    
    public Bean(int id, String name, String number, String email) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Bean() {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Bean{" + "id=" + id + ", name=" + name + ", number=" + number + ", email=" + email + '}';
    }
}
