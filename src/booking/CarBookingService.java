package booking;
import car.Car;
import car.CarDAO;
import user.User;
import user.UserDAO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
    public CarBooking registerCarBooking(CarBooking carBooking) {

        // 1. Check the user
        List<User> users = carBooking.getUsers();

        User user = userDAO.getUserByID(users.getFirst().getId());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        // 2. Check the car
        List<Car> cars = carBooking.getCars();
        Car car = carDAO.selectCarById(cars.getFirst().getId());
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }
        // 3. Validate dates
        LocalDate startDate = carBooking.getStartDate();
        LocalDate endDate = carBooking.getEndDate();
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates are required");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Start date cannot be in the past");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(
                    "End date must be after start date");
        }
        // 4. Check existing bookings
        List<CarBooking> carBookings = carBookingDAO.getCarBookings();
        for (CarBooking booking : carBookings) {
            if (booking == null) {
                continue;
            }
            List<Car> bookedCars = booking.getCars();


            // 5. Check whether the car is already booked
            if (bookedCars.contains(car.getId())
                    && booking.getBookingStatus() == BookingStatus.ACTIVE
                    && startDate.isBefore(booking.getEndDate())
                    && endDate.isAfter(booking.getStartDate())) {
                throw new IllegalArgumentException(
                        "Car is not available for the selected dates");
            }
        }
        // 6. Save the booking
        return carBookingDAO.bookCar(carBooking);
    }
    public List<CarBooking> getCarBooking() {
        return carBookingDAO.getCarBookings();
    }
    public CarBooking getCarBooking(UUID bookingId) {
        return carBookingDAO.getCarBooking(bookingId);
    }
}
