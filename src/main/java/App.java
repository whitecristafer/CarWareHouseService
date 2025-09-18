import business_logic.CarService;
import data.Car;
import data.CarRepository;

import java.util.List;

public class App {
    public static void main(String[] args) {
        CarRepository repository = new CarRepository();
        CarService carService = new CarService(repository);

        System.out.println("🚗 ПРОГРАММА УЧЕТА АВТОМОБИЛЕЙ НА СКЛАДЕ 🚗");
        System.out.println();

        try {
            System.out.println("Добавляем автомобили...");
            carService.addNewCar("VIN001", "Toyota", "Camry", 2022, 25000.0);
            carService.addNewCar("VIN002", "Honda", "Accord", 2021, 22000.0);
            carService.addNewCar("VIN003", "BMW", "X5", 2023, 55000.0);
            carService.addNewCar("VIN004", "Toyota", "Corolla", 2020, 18000.0);
            carService.addNewCar("VIN005", "Tesla", "Model 3", 2023, 42000.0);

            System.out.println("✅ Автомобили успешно добавлены!");
            System.out.println();

            carService.printInventoryReport();
            System.out.println();

            carService.printAllCars();
            System.out.println();

            System.out.println("🔍 Поиск автомобилей Toyota:");
            List<Car> toyotaCars = carService.searchCarsByBrand("Toyota");
            toyotaCars.forEach(System.out::println);
            System.out.println();

            System.out.println("💰 Продаем автомобиль VIN001...");
            boolean sold = carService.sellCar("VIN001");
            if (sold) {
                System.out.println("✅ Автомобиль VIN001 продан!");
            } else {
                System.out.println("❌ Не удалось продать автомобиль");
            }
            System.out.println();

            System.out.println("📈 Обновляем цену автомобиля VIN002...");
            boolean priceUpdated = carService.updateCarPrice("VIN002", 23000.0);
            if (priceUpdated) {
                System.out.println("✅ Цена обновлена!");
            } else {
                System.out.println("❌ Не удалось обновить цену");
            }
            System.out.println();

            System.out.println("ℹ️ Информация об автомобиле VIN003:");
            Car carInfo = carService.getCarInfo("VIN003");
            System.out.println(carInfo);
            System.out.println();

            System.out.println("⚠️ Пытаемся добавить автомобиль с существующим VIN...");
            try {
                carService.addNewCar("VIN001", "Ford", "Focus", 2022, 20000.0);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
            }
            System.out.println();

            System.out.println("⚠️ Пытаемся установить отрицательную цену...");
            try {
                carService.updateCarPrice("VIN002", -1000.0);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
            }
            System.out.println();

            System.out.println("📊 ФИНАЛЬНЫЙ ОТЧЕТ:");
            carService.printInventoryReport();
            System.out.println();

            System.out.println("🚗 Автомобили в наличии после продажи:");
            carService.printAllCars();

        } catch (Exception e) {
            System.out.println("❌ Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
