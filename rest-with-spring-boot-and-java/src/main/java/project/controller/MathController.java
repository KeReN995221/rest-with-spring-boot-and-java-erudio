package project.controller;

import project.converters.NumberConverter;
import org.springframework.web.bind.annotation.*;
import project.math.SimpleMath;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping()
public class MathController {
    private static final String template = "Hello, %s!";
    private  final AtomicLong counter = new AtomicLong();
    private static SimpleMath simpleMath = new SimpleMath();

    @GetMapping(value = "sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)  throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
              throw new UnsupportedOperationException("Please enter a valid number");
          }

          return simpleMath.sum( NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "sub/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)  throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please enter a valid number");
        }

        return simpleMath.subtraction(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "mult/{numberOne}/{numberTwo}")
    public Double multplication(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)  throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please enter a valid number");
        }

        return simpleMath.multplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "div/{numberOne}/{numberTwo}")
    public Double division(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)  throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please enter a valid number");
        }

        return simpleMath.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "avr/{numberOne}/{numberTwo}")
    public Double average(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)  throws Exception{

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please enter a valid number");
        }

        return simpleMath.average(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "sqrt/{number}")
    public Double squareRoot(@PathVariable(value = "number") String number)  throws Exception{

        if(!NumberConverter.isNumeric(number)){
            throw new UnsupportedOperationException("Please enter a valid number");
        }

        return simpleMath.squareRoot(NumberConverter.convertToDouble(number)) ;
    }
}
