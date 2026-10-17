package car;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CarArrayDataAccessService implements CarDAO {
     private static Car[] cars;
     static {
         cars = new Car[]{
                 new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                         "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                 new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                         "123reg",BigDecimal.TEN,Brand.TESLA,false),
         };
         }
//         static {
//         cars = List.of();
//         }

    @Override
    public List<Car> selectAllCars() {
        return List.of(cars);
    }
    @Override
    public Car selectCarById(UUID carId) {
         for (Car car : cars) {
             if (car.getId().equals(carId)) {
                 return car;
             }
         }
        return null;
    }
}


