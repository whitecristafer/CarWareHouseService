import data.Car;
import data.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryTests {
    private CarRepository repo;

    @BeforeEach
    public void setup() {
        repo = new CarRepository();
    }

    @Test
    public void testAddCarWorks() {
        Car car = new Car("VIN5", "Mazda", "CX-5", 2022, 27000.0);
        repo.addCar(car);
        assertEquals(1, repo.getTotalCarsCount());
        assertEquals(car, repo.findCarByVin("VIN5").orElse(null));
    }

    @Test
    public void testSellCarSetsInStockFalse() {
        Car car = new Car("VIN6", "Nissan", "Juke", 2017, 15000.0);
        repo.addCar(car);
        boolean result = repo.sellCar("VIN6");
        assertTrue(result);
        assertFalse(repo.findCarByVin("VIN6").get().isInStock());
    }

    @Test
    public void testFindCarsByBrand() {
        repo.addCar(new Car("VIN7", "Audi", "A4", 2015, 14000.0));
        repo.addCar(new Car("VIN8", "Audi", "Q5", 2018, 20000.0));
        List<Car> audis = repo.findCarsByBrand("Audi");
        assertEquals(2, audis.size());
        assertTrue(audis.stream().allMatch(c -> c.getBrand().equals("Audi")));
    }
}