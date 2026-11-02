import booking.*;
import car.*;
import user.User;
import user.UserArrayDataAccessService;
import user.UserDAO;
import user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
       CarBookingDAO carBookingDao = new CarBookingFileDataAccessService("bookings.dat");
// CarBookingDao carBookingDao = new CarBookingArrayDataAccessService();
       CarDAO carDao = new CarArrayDataAccessService();
       CarService carService = new CarService(carDao);
     //  System.out.println(Arrays.toString(carService.getAllCars()));
       UserDAO userDao = new UserArrayDataAccessService();
        System.out.println(userDao.selectAllUsers());
       UserService userService = new UserService(userDao);
       LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
     CarBooking carBooking = new CarBooking(UUID.randomUUID(), userService.selectAllUsers(),
             carService.getAllCars(), LocalDate.now(),endDate,
              BigDecimal.TWO,BookingStatus.CANCELLED,LocalDateTime.now());
      // CarBooking carBooking = new CarBooking();
       CarBookingService carBookingService = new CarBookingService(carBookingDao, userDao,carDao);
      // System.out.println(carBookingService);
       // carBookingService.registerCarBooking(carBooking);
      //  System.out.println(carBookingService.getCarBooking());
        //carBookingService.getCarBooking(UUID.fromString("b0c77a5b-2e69-4618-8f1e-f26160940fb9"));

}}
