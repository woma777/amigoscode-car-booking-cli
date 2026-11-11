package booking;

import user.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarBookingDAO  {
    List<CarBooking> getCarBookings() ;
    CarBooking getCarBooking(UUID bookingId) throws IOException;
    void bookCar(CarBooking booking);
    void deleteBooking(UUID bookingId) ;
    List<User> allUsersBookedCars();
}
