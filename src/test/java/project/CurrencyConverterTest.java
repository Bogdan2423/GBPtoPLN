package project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyConverterTest {
    @Test
    public void convertTest() throws Exception {
        double testPrice = 10;

        Double[] result = CurrencyConverter.convertGBP_PLN(testPrice, true);
        Double[] result2 = CurrencyConverter.convertGBP_PLN(result[1], false);

        assertEquals(testPrice, result2[1], 0.01);
        assertTrue(result[1]>testPrice);
    }
}
