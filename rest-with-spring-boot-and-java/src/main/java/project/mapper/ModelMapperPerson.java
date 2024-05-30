package project.mapper;

import org.modelmapper.ModelMapper;
import project.data.vo.v1.PersonVO;
import project.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperPerson {

    private static ModelMapper modelMapper = new ModelMapper();
    static{
        modelMapper.createTypeMap(Person.class, PersonVO.class)
                .addMapping(Person::getId, PersonVO::setKey);
        modelMapper.createTypeMap(PersonVO.class, Person.class)
                .addMapping(PersonVO::getKey, Person::setId);
    }

    public static <O,D> D parseObject(O origin, Class<D> destination) {
        return modelMapper.map(origin, destination);
    }

    public static <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> result = new ArrayList<>();
        for (O object : origin) {
            result.add(modelMapper.map(object, destination));
        }
        return result;
    }

}
