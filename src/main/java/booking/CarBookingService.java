package booking;
import car.Car;
import car.CarDAO;
import user.User;
import user.UserDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarBookingService  {
    private final CarBookingDAO carBookingDAO;
    private final UserDAO userDAO ;
    private final CarDAO carDAO ;
    public CarBookingService(CarBookingDAO carBookingDAO, UserDAO userDAO, CarDAO carDAO) {
        this.carBookingDAO = carBookingDAO;
        this.userDAO = userDAO;
        this.carDAO = carDAO;
    }
    public CarBooking book(UUID uuid,UUID userId, UUID carId,
                              LocalDate startDate, LocalDate endDate) {
        User user = userDAO.getUserByID(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Car car = carDAO.selectCarById(carId);
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Start date cannot be in the past");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(
                    "End date must be after start date");
        }
        List<CarBooking> bookings = carBookingDAO.getCarBookings();
        boolean isCarBooked = bookings.stream()
                .anyMatch(booking ->
                        booking.getCars().getId().equals(carId)
                                && booking.getBookingStatus() == BookingStatus.ACTIVE
                                && startDate.isBefore(booking.getEndDate())
                                && booking.getStartDate().isBefore(endDate)
                );
        if (isCarBooked) {
            throw new IllegalStateException(
                    "All car is booked");
        }
        long numberOfDays = ChronoUnit.DAYS.between(
                startDate, endDate);
        BigDecimal totalPrice = car.getRentalPricePerDay()
                .multiply(BigDecimal.valueOf(numberOfDays));
        CarBooking booking = new CarBooking(
                uuid,
                user,
                car,
                startDate,
                endDate,
                totalPrice,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        carBookingDAO.bookCar(booking);
        return booking;
    }
    public List<CarBooking> getCarBooking()  {
        return carBookingDAO.getCarBookings();
    }
    public CarBooking getCarBooking(UUID bookingId) throws IOException {
        return carBookingDAO.getCarBooking(bookingId);
    }
    public List<User> allUsersBookedCar(){
        return carBookingDAO.allUsersBookedCars();
    }
    public void deleteCarBooking(UUID bookingId) {
        carBookingDAO.deleteBooking(bookingId);
    }
}
