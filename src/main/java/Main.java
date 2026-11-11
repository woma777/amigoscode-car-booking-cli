import booking.*;
import car.*;
import user.UserArrayDataAccessService;
import user.UserDAO;
import user.UserFakerDataAccessService;
import user.UserService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import java.util.UUID;
public class Main {
    public static void main(String[] args)  {
        CarBookingDAO carBookingDao =
                new CarBookingFileDataAccessService("bookings.dat");
       // CarDAO carDao = new CarArrayDataAccessService();
        CarDAO carDao = new CarFakerDataAccessService();
        CarService carService = new CarService(carDao);
      //  UserDAO usersDao = new UserFakerDataAccessService();
        UserDAO usersDao = new UserArrayDataAccessService();
        UserService userService = new UserService(usersDao);
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBookingService carBookingService =
                new CarBookingService(carBookingDao, usersDao, carDao);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n===== CAR BOOKING CLI =====");
            System.out.println("1. Book a car");
            System.out.println("2. Delete booking");
            System.out.println("3. View user bookings");
            System.out.println("4. View all bookings");
            System.out.println("5. View available cars");
            System.out.println("6. View electric cars");
            System.out.println("7. View all users");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("Book a car");
                    System.out.println(carBookingService.book(UUID.randomUUID(),userService.selectAllUsers().getLast().getId(),
                    carService.getAllCars().getLast().getId(), LocalDate.now(),endDate));
                    break;
                case 2:
                    carBookingService.deleteCarBooking(UUID.fromString(scanner.nextLine()));
                    System.out.println("booking is deleted");
                    break;
                case 3:
                    System.out.println("get all users booked car :");
                    System.out.println(carBookingService.allUsersBookedCar());
                    break;
                case 4:
                    System.out.println("All bookings");
                    System.out.println(carBookingService.getCarBooking());
                    break;
                case 5:
                    System.out.println("Available cars available");
                    System.out.println(carService.getAllCars());
                    break;
                case 6:
                    System.out.println(" Available Electric cars");
                    System.out.println(carService.getAllElectricCars());
                    break;
                case 7:
                    System.out.println("All users");
                    System.out.println(userService.selectAllUsers());
                    break;
                case 8:
                    running = false;
                    System.out.println(
                            "Thank you for using the Car Booking CLI");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}

