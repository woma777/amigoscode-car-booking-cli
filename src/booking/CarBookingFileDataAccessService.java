package booking;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
    public List<CarBooking> getCarBookings() {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return List.of();
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof CarBooking booking) {
                System.out.println("this is booked data history:" +booking);
                return List.of(booking);
            }
            throw new IOException("Unexpected object type in bookings file: "
                    + obj.getClass().getName());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(
                    "Failed to read existing bookings", e);
        }
    }

    @Override
    public CarBooking getCarBooking(UUID bookingId) {
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            return null;
        }
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object obj = in.readObject();

            if (obj instanceof CarBooking booking) {
                if (booking.getId().equals(bookingId)) {
                    System.out.println("found booking with id " + bookingId + ":" +booking);
                    return booking;
                }
            }
            if (obj instanceof CarBooking[] bookings) {
                for (CarBooking booking : bookings) {
                    if (booking.getId().equals(bookingId)) {
                        System.out.println("found booking with id " + bookingId + ":" +booking);
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

