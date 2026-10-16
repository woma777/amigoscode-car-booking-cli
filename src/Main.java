import booking.BookingStatus;
import booking.CarBooking;
import booking.CarBookingDAO;
import booking.CarBookingService;
import car.Car;
import car.CarDAO;
import car.CarService;
import car.Brand;
import user.User;
import user.UserDAO;
import user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
     Car car2 = new Car(UUID.randomUUID(), "52662g", BigDecimal.TEN,Brand.TESLA,false);
        CarDAO carDAO = new CarDAO();
        carDAO.registerCar(car2);
        UserDAO userDAO = new UserDAO();
        LocalDate endDate = LocalDate.of(2026, 10, 19);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),userDAO.selectAllUsers(),carDAO.selectAllCars(),
                LocalDate.now(), endDate,BigDecimal.TWO,
                BookingStatus.CANCELLED,
                LocalDateTime.now()
        );
        CarBooking carBooking1 = new CarBooking(
                UUID.randomUUID(),userDAO.selectAllUsers(), carDAO.selectAllCars(),
                LocalDate.now(), endDate,BigDecimal.TEN,
                BookingStatus.CANCELLED,
                LocalDateTime.now()
        );

        CarBookingDAO carBookingDAO = new CarBookingDAO();
        carBookingDAO.bookCar(carBooking);
        carBookingDAO.bookCar(carBooking1);
        CarBookingService carBookingService = new CarBookingService(carBookingDAO);
        carBookingService.registerCarBooking(carBooking);
        carBookingService.registerCarBooking(carBooking1);
        System.out.println(Arrays.toString(carBookingService.getCarBooking()));

    }
}
