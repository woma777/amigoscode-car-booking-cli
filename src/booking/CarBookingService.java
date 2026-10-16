package booking;
import car.Car;
import car.CarDAO;
import user.User;
import user.UserDAO;

import java.time.LocalDate;
import java.util.UUID;

public class CarBookingService {

    private final CarBookingDAO carBookingDAO;
    private final UserDAO userDAO = new UserDAO();
    private final CarDAO carDAO = new CarDAO();

    public CarBookingService(CarBookingDAO carBookingDAO) {
        this.carBookingDAO = carBookingDAO;
    }

    public void registerCarBooking(CarBooking carBooking) {

        // 1. Check the user
        User[] users = carBooking.getUsers();

        if (users == null || users.length == 0 || users[0] == null) {
            throw new IllegalArgumentException("User is required");
        }

        User user = userDAO.getUserByID(users[0].getId());

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // 2. Check the car
        Car[] cars = carBooking.getCars();

        if (cars == null || cars.length == 0 || cars[0] == null) {
            throw new IllegalArgumentException("Car is required");
        }

        Car car = carDAO.selectCarById(cars[0].getId());

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
        CarBooking[] carBookings = carBookingDAO.getCarBookings();

        for (CarBooking booking : carBookings) {

            if (booking == null) {
                continue;
            }

            Car[] bookedCars = booking.getCars();

            if (bookedCars == null || bookedCars.length == 0
                    || bookedCars[0] == null) {
                continue;
            }
            System.out.println(bookedCars[0].getId().equals(car.getId()));
            System.out.println("booking status " + BookingStatus.ACTIVE);
            System.out.println("is before end date: " + booking.getEndDate());
            System.out.println("is after start date: " + booking.getStartDate());

            // 5. Check whether the car is already booked
            if (bookedCars[0].getId().equals(car.getId())
                    && booking.getBookingStatus() == BookingStatus.ACTIVE
                    && startDate.isBefore(booking.getEndDate())
                    && endDate.isAfter(booking.getStartDate())) {

                throw new IllegalArgumentException(
                        "Car is not available for the selected dates");
            }
        }

        // 6. Save the booking
        carBookingDAO.bookCar(carBooking);
    }

    public CarBooking[] getCarBooking() {
        return carBookingDAO.getCarBookings();
    }
}
