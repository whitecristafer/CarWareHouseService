package data;

public class Car {
    private String vin;
    private String brand;
    private String model;
    private int year;
    private double price;
    private boolean inStock;

    public Car(String vin, String brand, String model, int year, double price) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.inStock = true;
    }

    public String getVin() { return vin; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("%s %s %d (VIN: %s) - $%.2f [%s]",
                brand, model, year, vin, price, inStock ? "В наличии" : "Продано");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return vin.equals(car.vin);
    }

    @Override
    public int hashCode() {
        return vin.hashCode();
    }
}
