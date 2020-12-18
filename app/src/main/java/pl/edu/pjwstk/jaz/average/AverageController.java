package pl.edu.pjwstk.jaz.average;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class AverageController {

    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers){
        if (numbers == null){
            return new AverageResult ("Please put parameters.");
        }

        String[] numbersArray = numbers.split(",");
        float sum = 0;

        for (int i = 0; i < numbersArray.length; i++){
            sum += Float.parseFloat(numbersArray[i]);
        }

        BigDecimal average = new BigDecimal(sum / numbersArray.length);
        average = average.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
        return new AverageResult ("Average equals: " + average);
    }

}
