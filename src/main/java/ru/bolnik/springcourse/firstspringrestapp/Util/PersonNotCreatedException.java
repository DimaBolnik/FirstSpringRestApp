package ru.bolnik.springcourse.firstspringrestapp.Util;

public class PersonNotCreatedException extends RuntimeException {

    public PersonNotCreatedException(String message) {
        super(message);
    }
}
