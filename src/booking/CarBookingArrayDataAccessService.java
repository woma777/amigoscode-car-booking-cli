package booking;

import java.util.List;
import java.util.UUID;

public class CarBookingArrayDataAccessService implements CarBookingDAO {
    private static final CarBooking[] carBookings;
    static{
        carBookings= new CarBooking[50];
    }
    @Override
    public List<CarBooking> getCarBookings() {
        return List.of(carBookings);
    }

    @Override
    public CarBooking getCarBooking(UUID bookingId) {
        for (CarBooking carBooking : carBookings) {
            if (carBooking.getId().equals(bookingId)) {
                return carBooking;
            }
        }
        return null;
    }

    @Override
    public CarBooking bookCar(CarBooking booking) {
        for (CarBooking carBooking : carBookings) {
            carBooking.setId(booking.getId());
            carBooking.setUsers(booking.getUsers());
            carBooking.setCars(booking.getCars());
            carBooking.setPrice(booking.getPrice());
            carBooking.setStartDate(booking.getStartDate());
            carBooking.setEndDate(booking.getEndDate());
            carBooking.setBookingStatus(booking.getBookingStatus());
            carBooking.setBookedAt(booking.getBookedAt());
        }

        return booking;
    }
}
