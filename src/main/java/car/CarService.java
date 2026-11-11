package car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarService {

private final CarDAO carDAO;
    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }
     public List<Car> getAllCars(){
        return carDAO.selectAllCars();
     }
     public List<Car> getAllElectricCars(){
        return carDAO.selectAllElectricCars();
     }
     public Car getCarById(UUID carId){
       return carDAO.selectCarById(carId);
     }

}
