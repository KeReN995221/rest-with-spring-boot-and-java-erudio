package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;
import project.controller.PersonController;
import project.data.vo.v1.PersonVO;
import project.data.vo.v2.PersonVOV2;
import project.exceptions.RequiredObjectIsNullException;
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
        var people = ModelMapperPerson.parseListObjects( personRepository.findAll(), PersonVO.class);
        people
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).getById(p.getKey())).withSelfRel()));
        return people;
    }

    public PersonVO searchById(Long id){
        logger.info("Finding a person ");
        var entity =  personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        var vo =  ModelMapperPerson.parseObject( entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).getById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person){
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating a new person");
        var entity =  ModelMapperPerson.parseObject(person, Person.class);
        var vo =  ModelMapperPerson.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).getById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person){
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Loading a person");
        var entity = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo =  ModelMapperPerson.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).getById(vo.getKey())).withSelfRel());
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
