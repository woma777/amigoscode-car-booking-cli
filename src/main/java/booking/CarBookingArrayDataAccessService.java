package booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingArrayDataAccessService implements CarBookingDAO {
    private static final List<CarBooking> carBookingList = new ArrayList<>();

    @Override
    public List<CarBooking> getCarBookings() {
        return carBookingList;
    }
    @Override
    public CarBooking getCarBooking(UUID bookingId) {
        return carBookingList.stream()
                .filter(carBooking -> carBooking.getId().equals(bookingId))
                .findFirst().orElse(null);
    }

    @Override
    public CarBooking bookCar(CarBooking booking) {
        for (CarBooking carBooking : carBookingList) {
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
