package project.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperPerson {

    private static ModelMapper modelMapper = new ModelMapper();
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
