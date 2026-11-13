package booking;

import car.Car;
import user.User;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CarBookingFileDataAccessService implements CarBookingDAO   {
    private final String filePath;
    public CarBookingFileDataAccessService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void bookCar(CarBooking booking) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File does not exist in the given path" + "_" +filePath);
        }
        List<CarBooking> bookings = new ArrayList<>();
        bookings.add(booking);
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(bookings);
        } catch (IOException ioe) {
            throw new RuntimeException("Failed to save booking", ioe);
        }
    }
    @Override
    public void deleteBooking(UUID bookingId) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File does not exist in the given path" + "_" +filePath);
        }
        List<CarBooking> bookings;
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            bookings = (List<CarBooking>) in.readObject();
            boolean removed = bookings.removeIf(
                    booking -> booking.getId().equals(bookingId));
            if (removed) {
                try (ObjectOutputStream out =
                             new ObjectOutputStream(new FileOutputStream(file))) {
                    out.writeObject(bookings);
                }
                catch (IOException ioe){
                    System.out.println(ioe.getMessage());
                }
                System.out.println("Car booking has been deleted.");
            } else {
                System.out.println("Car booking not found.");
            }
        } catch (IOException | ClassNotFoundException ioe) {
            throw new RuntimeException("Failed to delete booking", ioe);
        }
    }
    @Override
    public List<Car> allUsersBookedCars() {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File does not exist in the given path" + "_" + filePath);
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            List<CarBooking> bookings =
                    (List<CarBooking>) in.readObject();
            return bookings.stream()
                    .filter(carBooking -> carBooking.getBookingStatus().equals(BookingStatus.ACTIVE))
                    .map(CarBooking::getCars)
                    .toList();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to read car bookings.", e);
        }
    }

    @Override
    public List<Car> getCarBookedByUserId(UUID userId) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File does not exist in the given path" + "_" + filePath);
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            List<CarBooking> bookedCars =
                    (List<CarBooking>) in.readObject();
            return bookedCars.stream()
                    .filter(booking ->
                            booking.getUsers().getId().equals(userId))
                    .map(CarBooking::getCars)
                    .collect(Collectors.toList());

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(
                    "Failed to read existing bookings", e);
        }
    }

    @Override
    public List<CarBooking> getCarBookings() {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File does not exist in the given path" + "_" +filePath);
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof List<?> list) {
                return list.stream()
                        .map(item -> {
                            if (!(item instanceof CarBooking)) {
                                throw new IllegalStateException(
                                        "Invalid object in booking file: "
                                                + item.getClass().getName());
                            }
                            return (CarBooking) item;
                        })
                        .toList();
            }
            throw new IOException(
                    "Expected a List<CarBooking>, but found: "
                            + obj.getClass().getName());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to read booking file", e);
        }
    }
    @Override
    public CarBooking getCarBooking(UUID bookingId) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File does not exist in the given path" + "_" + filePath);
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            List<CarBooking> bookings =
                    (List<CarBooking>) in.readObject();
            return bookings.stream()
                    .filter(booking ->
                            booking.getId().equals(bookingId))
                    .findFirst()
                    .orElse(null);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(
                    "Failed to read existing bookings", e);
        }
    }

}



