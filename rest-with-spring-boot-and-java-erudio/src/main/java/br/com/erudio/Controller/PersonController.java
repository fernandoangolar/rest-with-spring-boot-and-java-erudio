package br.com.erudio.Controller;

import br.com.erudio.model.Person;
import br.com.erudio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findByid(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll( ){
        return personService.findAll();
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person){
        return personService.create(person);
    }

    @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person ) {
        return personService.update(person);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable( value = "id") Long id ) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
