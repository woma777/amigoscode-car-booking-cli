import booking.*;
import car.*;
import user.UserDAO;
import user.UserFakerDataAccessService;
import user.UserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;
public class Main {
    public static void main(String[] args) throws IOException {
       CarBookingDAO carBookingDao = new CarBookingFileDataAccessService("bookings.dat");

       CarDAO carDao = new CarArrayDataAccessService();
       CarService carService = new CarService(carDao);
     //  System.out.println(Arrays.toString(carService.getAllCars()));
      // UserDAO userDao = new UserArrayDataAccessService();
        UserDAO usersDao = new UserFakerDataAccessService();
       UserService userService = new UserService(usersDao);
       LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
     CarBooking carBooking = new CarBooking(UUID.randomUUID(), userService.selectAllUsers(),
             carService.getAllCars(), LocalDate.now(),endDate,
              BigDecimal.TWO,BookingStatus.CANCELLED,LocalDateTime.now());
       CarBookingService carBookingService = new CarBookingService(carBookingDao, usersDao,carDao);
     // System.out.println(carBookingService);
        System.out.println(carBookingService.registerCarBooking(carBooking));
    System.out.println(carBookingService.getCarBooking());
       // carBookingService.getCarBooking(UUID.fromString("b0c77a5b-2e69-4618-8f1e-f26160940fb9"));

}}
