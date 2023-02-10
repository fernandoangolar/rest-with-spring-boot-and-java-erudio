package br.com.erudio.service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById( Long id ) {

        logger.info("Finding one person");

        return personRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("No record found for this ID!"));
    }

    public List<Person> findAll( ) {

        logger.info("Finding All people");

        return personRepository.findAll();
    }

    public Person create( Person person ) {

        logger.info("Creating one person");

        return personRepository.save(person);
    }

    public Person update( Person person ) {

        logger.info("updating one person");

        var entity = personRepository.findById( person.getId() )
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete( Long id ) {
        logger.info("Deleting on person");

        var entity = personRepository.findById( id )
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        personRepository.delete(entity);
    }


}
