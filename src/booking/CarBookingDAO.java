package booking;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class CarBookingDAO {
    private static final CarBooking[] carBookings;
    static{
        carBookings= new CarBooking[50];
    }
    public void bookCar(CarBooking carBooking){
        Arrays.fill(carBookings, carBooking);
    }
    public CarBooking[] getCarBookings() {
        return carBookings;
    }
    public Optional<CarBooking> getCarBooking(UUID id){
        for (CarBooking carBooking : carBookings) {
            if (carBooking.getId().equals(id)) {
                return Optional.of(carBooking);
            }
        }
        return Optional.empty();
    }
}
