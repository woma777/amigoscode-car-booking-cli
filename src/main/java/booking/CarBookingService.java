package booking;
import car.Car;
import car.CarDAO;
import user.User;
import user.UserDAO;

import java.io.IOException;
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

    public CarBooking registerCarBooking(CarBooking carBooking) throws IOException {

        List<User> users = carBooking.getUsers();
        if (users == null || users.isEmpty() || users.getFirst() == null) {
            throw new IllegalArgumentException("At least one user is required");
        }

        List<Car> cars = carBooking.getCars();
        Car car = cars.getFirst();
        if (cars == null || cars.isEmpty() || cars.get(cars.size() - 1) == null) {
            throw new IllegalArgumentException("At least one car is required");
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
    public List<CarBooking> getCarBooking() throws IOException {

        return carBookingDAO.getCarBookings();
    }
    public CarBooking getCarBooking(UUID bookingId) {
        return carBookingDAO.getCarBooking(bookingId);
    }
}
