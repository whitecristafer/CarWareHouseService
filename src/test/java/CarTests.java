import data.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTests {

    @Test
    public void testCarConstructorSetsFields() {
        Car car = new Car("VIN9", "Kia", "Rio", 2016, 9000.0);
        assertEquals("VIN9", car.getVin());
        assertEquals("Kia", car.getBrand());
        assertEquals("Rio", car.getModel());
        assertEquals(2016, car.getYear());
        assertEquals(9000.0, car.getPrice(), 0.01);
        assertTrue(car.isInStock());
    }

    @Test
    public void testSetPriceChangesPrice() {
        Car car = new Car("VIN10", "Skoda", "Octavia", 2015, 8000.0);
        car.setPrice(7500.0);
        assertEquals(7500.0, car.getPrice(), 0.01);
    }

    @Test
    public void testCarToStringContainsBrandModelVin() {
        Car car = new Car("VIN11", "Renault", "Logan", 2014, 7000.0);
        String info = car.toString();
        assertTrue(info.contains("Renault"));
        assertTrue(info.contains("Logan"));
        assertTrue(info.contains("VIN: VIN11"));
    }
}