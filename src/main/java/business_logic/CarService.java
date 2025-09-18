package business_logic;

import data.Car;
import data.CarRepository;

import java.util.List;

public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addNewCar(String vin, String brand, String model, int year, double price) {
        validateCarParameters(vin, brand, model, year, price);
        Car car = new Car(vin, brand, model, year, price);
        carRepository.addCar(car);
    }

    private void validateCarParameters(String vin, String brand, String model, int year, double price) {
        if (vin == null || vin.trim().isEmpty()) {
            throw new IllegalArgumentException("VIN не может быть пустым");
        }
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка не может быть пустой");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой");
        }
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("Некорректный год выпуска");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
    }

    public boolean sellCar(String vin) {
        return carRepository.sellCar(vin);
    }

    public boolean updateCarPrice(String vin, double newPrice) {
        if (newPrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        return carRepository.updateCarPrice(vin, newPrice);
    }

    public List<Car> searchCarsByBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка для поиска не может быть пустой");
        }
        return carRepository.findCarsByBrand(brand);
    }

    public double calculateTotalInventoryValue() {
        return carRepository.getCarsInStock().stream()
                .mapToDouble(Car::getPrice)
                .sum();
    }

    public Car getCarInfo(String vin) {
        return carRepository.findCarByVin(vin)
                .orElseThrow(() -> new IllegalArgumentException("Автомобиль с VIN " + vin + " не найден"));
    }

    public void printInventoryReport() {
        int totalCars = carRepository.getTotalCarsCount();
        int inStockCars = carRepository.getCarsInStockCount();
        double totalValue = calculateTotalInventoryValue();

        System.out.println("=== ОТЧЕТ ПО СКЛАДУ АВТОМОБИЛЕЙ ===");
        System.out.printf("Всего автомобилей: %d%n", totalCars);
        System.out.printf("В наличии: %d%n", inStockCars);
        System.out.printf("Продано: %d%n", totalCars - inStockCars);
        System.out.printf("Общая стоимость инвентаря: $%.2f%n", totalValue);
        System.out.println("==================================");
    }

    public void printAllCars() {
        List<Car> allCars = carRepository.getCarsInStock();
        System.out.println("=== АВТОМОБИЛИ В НАЛИЧИИ ===");
        if (allCars.isEmpty()) {
            System.out.println("Автомобилей нет в наличии");
        } else {
            allCars.forEach(System.out::println);
        }
        System.out.println("============================");
    }
}
