package __FirstStepsInJavawithSpringBoot;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("sum")
public class MathController {
    private static final String template = "Hello, %s!";
    private  final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
           return 1D;
    }

}
