package ru.bolnik.springcourse.firstspringrestapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.bolnik.springcourse.firstspringrestapp.Util.PersonErrorResponse;
import ru.bolnik.springcourse.firstspringrestapp.Util.PersonNotCreatedException;
import ru.bolnik.springcourse.firstspringrestapp.Util.PersonNotFoundException;
import ru.bolnik.springcourse.firstspringrestapp.dto.PersonDTO;
import ru.bolnik.springcourse.firstspringrestapp.models.Person;
import ru.bolnik.springcourse.firstspringrestapp.service.PeopleService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping()
    public List<PersonDTO> getAllPeople() {
        return peopleService.findAll().stream()
                .map(this::convertToPersonDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public PersonDTO getPeopleById(@PathVariable("id") int id) {
        return convertToPersonDTO(peopleService.findOne(id)) ;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createPeople(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errors.toString());
        }
        peopleService.save(convertToPerson(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException ex) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person not found", LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonNotCreatedException.class)
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException ex) {
        PersonErrorResponse response = new PersonErrorResponse(
                ex.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }


}
