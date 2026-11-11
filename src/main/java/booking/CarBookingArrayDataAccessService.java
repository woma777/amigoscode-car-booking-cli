package booking;

import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CarBookingArrayDataAccessService implements CarBookingDAO {
    private  final List<CarBooking> carBookingList = new ArrayList<>();
    @Override
    public List<CarBooking> getCarBookings() {
        return carBookingList;
    }
    @Override
    public CarBooking getCarBooking(UUID bookingId) {
        return carBookingList.stream()
                .filter(carBooking -> carBooking.getId().equals(bookingId))
                .findFirst()
                .orElse(null);
    }
    @Override
    public void bookCar(CarBooking booking) {
        carBookingList.add(booking);
    }
    @Override
    public void deleteBooking(UUID bookingId) {
        carBookingList.removeIf(
                carBooking -> carBooking.getId().equals(bookingId));
    }
    @Override
    public List<User> allUsersBookedCars() {
        return carBookingList.stream()
                .map(CarBooking::getUsers)
                .distinct()
                .collect(Collectors.toList());
    }

}
