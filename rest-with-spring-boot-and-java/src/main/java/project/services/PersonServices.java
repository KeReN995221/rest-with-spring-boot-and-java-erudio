package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.data.vo.v1.PersonVO;
import project.data.vo.v2.PersonVOV2;
import project.exceptions.ResourceNotFoundException;
import project.mapper.ModelMapperPerson;
import project.mapper.custom.PersonMapper;
import project.model.Person;
import project.repositories.PersonRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public List<PersonVO> findAll(){
        logger.info("Finding all person ");
        return ModelMapperPerson.parseListObjects( personRepository.findAll(), PersonVO.class);
    }

    public PersonVO searchById(Long id){
        logger.info("Finding a person ");
        var entity =  personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        return ModelMapperPerson.parseObject( entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating a new person");
        var entity =  ModelMapperPerson.parseObject(person, Person.class);
        var vo =  ModelMapperPerson.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person){
        logger.info("Loading a person");
        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo =  ModelMapperPerson.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }


    public void delete(Long id) {
        logger.info("person deletada id: " + id);
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(entity);
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating a new person");
        var entity = personMapper.convertVOtoEntity( person);
        var vo = personMapper.convertEntitytoVO(personRepository.save(entity));
        return vo;
    }
}
