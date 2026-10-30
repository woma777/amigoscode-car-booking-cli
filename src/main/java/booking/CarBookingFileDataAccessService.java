package booking;

import car.Brand;
import car.Car;
import car.CarDAO;
import car.CarService;
import user.User;
import user.UserDAO;
import user.UserService;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CarBookingFileDataAccessService implements CarBookingDAO   {


    private final String filePath;
    public CarBookingFileDataAccessService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CarBooking bookCar(CarBooking booking) {
        try {
            File file = new File(filePath);
            try {
              ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
              out.writeObject(booking);
              out.close();
            }catch (IOException ioe){
                System.out.println(ioe.getMessage());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return booking;
    }
    @Override
    public List<CarBooking> getCarBookings() throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return List.of();
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof CarBooking booking) {
                return List.of(booking);
            }
            throw new IOException("Unexpected object type in bookings file: "
                    + obj.getClass().getName());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }
//@Override
//public List<CarBooking> getCarBookings() throws IOException {
//
//    try (InputStream in = getClass()
//            .getClassLoader()
//            .getResourceAsStream("users.csv")) {
//
//        if (in == null) {
//            throw new FileNotFoundException(
//                    "users.csv not found"
//            );
//        }
//        // Map CSV user IDs to User objects
//        Map<String, User> allUsers = Map.of(
//                "USR-001",
//                new User(
//                        UUID.fromString(
//                                "8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"
//                        ),
//                        "James"
//                ),
//                "USR-002",
//                new User(
//                        UUID.fromString(
//                                "b10d126a-3608-4980-9f9c-aa179f5cebc3"
//                        ),
//                        "Jamila"
//                ),
//                "USR-003",
//                new User(
//                        UUID.fromString("a1234567-1234-4567-8901-123456789012"),
//                        "Alex"
//                )
//        );
//        // Map CSV car IDs to Car objects
//        Map<String, Car> allCars = Map.of(
//                "CAR-001",
//                new Car(
//                        UUID.fromString(
//                                "8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"
//                        ),
//                        "76hdgc",
//                        BigDecimal.TEN,
//                        Brand.TESLA,
//                        true
//                ),
//                "CAR-002",
//                new Car(
//                        UUID.fromString(
//                                "b10d126a-3608-4980-9f9c-aa179f5cebc3"
//                        ),
//                        "123reg",
//                        BigDecimal.TEN,
//                        Brand.TESLA,
//                        false
//                ),
//                "CAR-003",
//                new Car(
//                        UUID.fromString("c1234567-1234-4567-8901-123456789012"),
//                        "45xyz",
//                        new BigDecimal("180.50"),
//                        Brand.TESLA,
//                        false
//                )
//        );
//
//        List<CarBooking> bookings = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(
//                        in,
//                        StandardCharsets.UTF_8
//                ))) {
//
//            // Skip the CSV header
//            reader.readLine();
//
//            String line;
//            int lineNumber = 1;
//
//            while ((line = reader.readLine()) != null) {
//
//                lineNumber++;
//
//                if (line.isBlank()) {
//                    continue;
//                }
//
//                try {
//                    String[] fields = line.split(",", -1);
//
//                    if (fields.length != 8) {
//                        throw new IllegalArgumentException(
//                                "Expected 8 columns, got "
//                                        + fields.length
//                        );
//                    }
//
//                    // Booking ID is a UUID
//                    UUID id = UUID.fromString(
//                            fields[0].trim()
//                    );
//
//                    // Resolve user IDs
//                    List<User> users =
//                            Arrays.stream(fields[1].split(";"))
//                                    .map(String::trim)
//                                    .map(userId -> {
//                                        User user =
//                                                allUsers.get(userId);
//
//                                        if (user == null) {
//                                            throw new IllegalArgumentException(
//                                                    "User not found: "
//                                                            + userId
//                                            );
//                                        }
//
//                                        return user;
//                                    })
//                                    .toList();
//
//                    // Resolve car IDs
//                    List<Car> cars =
//                            Arrays.stream(fields[2].split(";"))
//                                    .map(String::trim)
//                                    .map(carId -> {
//                                        Car car =
//                                                allCars.get(carId);
//
//                                        if (car == null) {
//                                            throw new IllegalArgumentException(
//                                                    "Car not found: "
//                                                            + carId
//                                            );
//                                        }
//
//                                        return car;
//                                    })
//                                    .toList();
//
//                    LocalDate startDate =
//                            LocalDate.parse(fields[3].trim());
//
//                    LocalDate endDate =
//                            LocalDate.parse(fields[4].trim());
//
//                    BigDecimal price =
//                            new BigDecimal(fields[5].trim());
//
//                    BookingStatus status =
//                            BookingStatus.valueOf(
//                                    fields[6].trim()
//                            );
//
//                    LocalDateTime bookedAt =
//                            LocalDateTime.parse(
//                                    fields[7].trim()
//                            );
//
//                    // Create the booking
//                    CarBooking booking = new CarBooking(
//                            id,
//                            users,
//                            cars,
//                            startDate,
//                            endDate,
//                            price,
//                            status,
//                            bookedAt
//                    );
//
//                    bookings.add(booking);
//
//                } catch (RuntimeException e) {
//                    throw new IOException(
//                            "Invalid booking data at CSV line "
//                                    + lineNumber + ": " + line,
//                            e
//                    );
//                }
//            }
//        }
//
//        return bookings;
//    }
//}

    @Override
    public CarBooking getCarBooking (UUID bookingId){
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            return null;
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object obj = in.readObject();

            if (obj instanceof CarBooking booking) {
                if (booking.getId().equals(bookingId)) {
                    System.out.println("found booking with id " + bookingId + ":" + booking);
                    return booking;
                }
            }
            if (obj instanceof CarBooking[] bookings) {
                for (CarBooking booking : bookings) {
                    if (booking.getId().equals(bookingId)) {
                        System.out.println("found booking with id " + bookingId + ":" + booking);
                        return booking;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(
                    "Failed to read existing bookings", e);
        }

        return null;
    }
}



