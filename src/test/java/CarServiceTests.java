import business_logic.CarService;
import data.Car;
import data.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTests {
    private CarRepository repo;
    private CarService service;

    @BeforeEach
    public void setup() {
        repo = new CarRepository();
        service = new CarService(repo);
    }

    @Test
    public void testAddNewCarAddsCar() {
        service.addNewCar("VIN1", "Toyota", "Camry", 2020, 25000.0);
        Car car = service.getCarInfo("VIN1");
        assertEquals("Toyota", car.getBrand());
        assertEquals("Camry", car.getModel());
        assertTrue(car.isInStock());
    }

    @Test
    public void testSellCarChangesInStock() {
        service.addNewCar("VIN2", "Honda", "Civic", 2019, 18000.0);
        boolean sold = service.sellCar("VIN2");
        assertTrue(sold);
        assertFalse(service.getCarInfo("VIN2").isInStock());
    }

    @Test
    public void testCalculateInventoryValue() {
        service.addNewCar("VIN3", "Ford", "Focus", 2018, 12000.0);
        service.addNewCar("VIN4", "BMW", "320i", 2021, 32000.0);
        service.sellCar("VIN3");
        double value = service.calculateTotalInventoryValue();
        assertEquals(32000.0, value, 0.01); // только BMW осталась в наличии
    }
}