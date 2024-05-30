package project.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.data.vo.v1.PersonVO;
import project.data.vo.v2.PersonVOV2;
import project.services.PersonServices;
import project.util.MediaType;
import java.util.List;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;


    @GetMapping(
            produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
                ,MediaType.APPLICATION_YML})
    public List<PersonVO> getAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}"
            ,produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
                ,MediaType.APPLICATION_YML})
    public PersonVO getById(@PathVariable (value = "id") Long id){
        return service.searchById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}
            ,produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            ,MediaType.APPLICATION_YML})
    public PersonVO create(@RequestBody PersonVO person){
        return service.create(person);
    }

    @PostMapping(value = "/v2"
            ,consumes = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}
            ,produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            ,MediaType.APPLICATION_YML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2  person){
        return service.createV2(person);
    }

    @PutMapping(value = "/update"
            ,consumes = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}
            ,produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            ,MediaType.APPLICATION_YML})
    public PersonVO update(@RequestBody PersonVO person){
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
