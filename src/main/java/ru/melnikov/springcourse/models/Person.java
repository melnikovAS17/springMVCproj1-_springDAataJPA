package ru.melnikov.springcourse.models;

import javax.validation.constraints.*;

public class Person {
    private  int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 20 character")
    private String name;
    @Min(value = 0, message = "Age should be grater then 0")
    private int age;
    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Please enter correct email")
    private String email;
    //Russia, Moscow, index -> (123456)
    @NotEmpty(message = "address shouldn't be empty")
    @Pattern(regexp = "[A-Z]\\w+, [A-z]\\w+, \\d{6}", message = "address should be correct: Russia, Moscow, 123456")
    private String address;

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }
    public Person(){}
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
