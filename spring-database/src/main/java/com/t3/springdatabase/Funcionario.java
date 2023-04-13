package com.t3.springdatabase;

public class Funcionario {
    private long id;
    private String firstName;
    private String lastName;

    public Funcionario(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
    
}
