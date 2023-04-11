package project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyConverterTest {
    @Test
    public void convertTest() throws Exception {
        double testPrice = 10;

        double result = CurrencyConverter.convertGBP_PLN(testPrice, true);
        double result2 = CurrencyConverter.convertGBP_PLN(result, false);

        assertEquals(testPrice, result2, 0.01);
        assertTrue(result>testPrice);
    }
}
