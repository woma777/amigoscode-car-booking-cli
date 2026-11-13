package car;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class CarFakerDataAccessService implements CarDAO{
    List<Car> cars = new ArrayList<>();
    @Override
    public List<Car> selectAllCars() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Car car = new Car(
                    UUID.randomUUID(),
                    faker.regexify("[A-Z]{2}[0-9]{3}[A-Z]{2}"),
                  BigDecimal.valueOf(faker.number().numberBetween(100,600))
                    ,
                    faker.options().option(Brand.values()),
                    faker.bool().bool()
            );
            cars.add(car);
        }
        return cars;
    }
    @Override
    public Car selectCarById(UUID carId) {
        return cars.stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst().orElse(null);
    }
    @Override
    public List<Car> selectAllElectricCars() {
        return cars.stream()
                .filter(Car::isElectric)
                .collect(Collectors.toList());
    }
}
