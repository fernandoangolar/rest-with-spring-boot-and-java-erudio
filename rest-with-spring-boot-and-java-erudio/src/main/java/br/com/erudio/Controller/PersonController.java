package br.com.erudio.Controller;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
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
    public PersonVO findByid(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll( ){
        return personService.findAll();
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);
    }

    // Medodo que vai ter um novo campo na minha API
    @PostMapping( value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2 ) {
        return personService.createV2( personVOV2 );
    }

    @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person ) {
        return personService.update(person);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable( value = "id") Long id ) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
