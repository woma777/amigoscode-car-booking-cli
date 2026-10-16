package car;

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

     public Car[] getAllCars(){

        return carDAO.selectAllCars();
     }
}
