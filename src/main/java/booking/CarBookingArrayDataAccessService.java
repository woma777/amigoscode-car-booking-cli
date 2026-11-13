package booking;

import car.Car;
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
    public List<Car> allUsersBookedCars() {
        return carBookingList.stream()
                .filter(carBooking -> carBooking.getBookingStatus().equals(BookingStatus.ACTIVE))
                .map(CarBooking::getCars)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getCarBookedByUserId(UUID userId) {
        return carBookingList.stream()
                .filter(booking ->
                        booking.getUsers().getId().equals(userId))
                .map(CarBooking::getCars)
                .collect(Collectors.toList());
    }

}
