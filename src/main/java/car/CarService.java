package car;

import booking.BookingStatus;
import booking.CarBooking;
import booking.CarBookingDAO;

import java.util.*;
import java.util.stream.Collectors;

public class CarService {
private final CarBookingDAO carBookingDAO;
private final CarDAO carDAO;
    public CarService(CarBookingDAO carBookingDAO, CarDAO carDAO) {
        this.carBookingDAO = carBookingDAO;
        this.carDAO = carDAO;
    }
     public List<Car> getAllCars(){
        List<Car> allCars = carDAO.selectAllCars();
         Set<UUID> bookedCarIds = getBookedCarIds();
         return allCars.stream()
                 .filter(car -> !bookedCarIds.contains(car.getId()))
                 .collect(Collectors.toList());
     }
     public List<Car> getAllElectricCars(){
         List<Car> allCars = carDAO.selectAllCars();
         Set<UUID> bookedCarIds = getBookedCarIds();
         return allCars.stream()
                 .filter(car -> !bookedCarIds.contains(car.getId()) && car.isElectric())
                 .collect(Collectors.toList());

     }
    private Set<UUID> getBookedCarIds() {
        return carBookingDAO.getCarBookings().stream()
                .filter(booking ->
                        booking.getBookingStatus() == BookingStatus.ACTIVE)
                .map(booking -> booking.getCars().getId())
                .collect(Collectors.toSet());
    }
     public Car getCarById(UUID carId){
       return carDAO.selectCarById(carId);
     }

}
