package car;

import java.util.List;

public class CarService {

private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }
//     public Car registerCar(Car[] car){
//        carDAO.addCar(car);
//
//        return car;
//     }

     public List<Car> getAllCars(){

        return carDAO.selectAllCars();
     }
}
