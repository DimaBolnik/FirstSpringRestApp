package ru.bolnik.springcourse.firstspringrestapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {


    @NotEmpty(message = "Name not be empty")
    @Size(min = 2, max = 30,message = "Имя от 2 до 30 симовлов")
    private String name;


    @Min(value = 0, message = "Возраст не должен быть меньше 0")
    private int age;


    @Email()
    @NotEmpty(message = "Email not be empty")
    private String email;

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
}
