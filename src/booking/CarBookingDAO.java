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
}
