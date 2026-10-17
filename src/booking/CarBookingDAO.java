package booking;

import car.Car;
import user.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarBookingDAO  {

    List<CarBooking> getCarBookings();
    CarBooking getCarBooking(UUID bookingId);
    CarBooking bookCar(CarBooking booking);
    //void deleteBooking(UUID bookingId);



//    public void bookCar(CarBooking carBooking){
//        Arrays.fill(carBookings, carBooking);
//    }
//    public CarBooking[] getCarBookings() {
//        return carBookings;
//    }
//    public Optional<CarBooking> getCarBooking(UUID id){
//        for (CarBooking carBooking : carBookings) {
//            if (carBooking.getId().equals(id)) {
//                return Optional.of(carBooking);
//            }
//        }
//        return Optional.empty();
//    }
}
