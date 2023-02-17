package br.com.erudio.service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
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

    @Autowired
    private PersonMapper personMapper;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonVO findById(Long id ) {
        logger.info("Finding one person");

        var entity = personRepository.findById( id )
                .orElseThrow(() -> new  ResourceNotFoundException("Id no found"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Findind all person");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO create( PersonVO person ) {
        logger.info("Creating person");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class );

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 personVOV2 ) {
        logger.info("Creating one person with v2");

        var entity = personMapper.convertVoToEntity(personVOV2);
        var vo = personMapper.convertEntityToVo(personRepository.save(entity));
        return vo;
    }

    public PersonVO update( PersonVO person ) {
        logger.info("Updating person");

        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete( Long id ) {
        logger.info("deleting one person");

        var entity = personRepository.findById( id )
                .orElseThrow();

        personRepository.delete( entity );
    }

}
