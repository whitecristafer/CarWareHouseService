package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepository {
    private List<Car> cars;

    public CarRepository() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        if (findCarByVin(car.getVin()).isPresent()) {
            throw new IllegalArgumentException("Автомобиль с VIN " + car.getVin() + " уже существует");
        }
        cars.add(car);
    }

    public Optional<Car> findCarByVin(String vin) {
        return cars.stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst();
    }

    public List<Car> findCarsByBrand(String brand) {
        return cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .toList();
    }

    public List<Car> getCarsInStock() {
        return cars.stream()
                .filter(Car::isInStock)
                .toList();
    }

    public boolean sellCar(String vin) {
        Optional<Car> car = findCarByVin(vin);
        if (car.isPresent() && car.get().isInStock()) {
            car.get().setInStock(false);
            return true;
        }
        return false;
    }

    public boolean updateCarPrice(String vin, double newPrice) {
        Optional<Car> car = findCarByVin(vin);
        if (car.isPresent()) {
            car.get().setPrice(newPrice);
            return true;
        }
        return false;
    }

    public int getTotalCarsCount() {
        return cars.size();
    }

    public int getCarsInStockCount() {
        return (int) cars.stream().filter(Car::isInStock).count();
    }

    public void clear() {
        cars.clear();
    }
}
