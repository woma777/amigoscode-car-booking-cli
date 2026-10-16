package car;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

public class CarDAO {
    private static Car[] cars;
    private static int nextAvailableSlot = 0;

    private static final int CAPACITY = 5;

    static{
        cars = new Car[CAPACITY];
    }

    public void registerCar(Car car){
        if(nextAvailableSlot >= CAPACITY){
            Arrays.fill(cars, car);
        }
        cars[nextAvailableSlot] = car;
        nextAvailableSlot++;
    }


    public Car[] selectAllCars(){
        return cars;
    }

    public Car selectCarById(UUID id){
        for(Car car : cars){
            if(car.getId().equals(id)){
                return car;
            }
        }
        return null;
    }


}
