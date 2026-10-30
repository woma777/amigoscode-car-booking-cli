package car;

import booking.CarBooking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarArrayDataAccessService implements CarDAO {
     private static final List<Car> carList;
     static{
         carList = new ArrayList<Car>(
                 List.of(
                         new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                                 "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                         new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                                 "123reg",BigDecimal.TEN,Brand.TESLA,false)
                 )
         );
     }
    @Override
    public List<Car> selectAllCars() {
         return carList;
    }
    @Override
    public Car selectCarById(UUID carId) {
        return carList.stream().
                filter(car -> car.getId().equals(carId))
                .findFirst().orElse(null);
    }
}


