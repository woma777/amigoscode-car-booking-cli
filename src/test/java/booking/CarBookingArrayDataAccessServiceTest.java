package booking;

import car.Brand;
import car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CarBookingArrayDataAccessServiceTest {
    private CarBookingArrayDataAccessService underTest;
    @BeforeEach
    void setUp() {
        underTest = new CarBookingArrayDataAccessService();
    }
    @Test
    void getCarBookings() {
        //given
        User user =
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila");

        Car car =
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN, Brand.TESLA,true);
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        underTest.bookCar(carBooking);
        //when
        List<CarBooking> actual = underTest.getCarBookings();
        //then
        assertThat(actual).isNotNull();
        assertThat(actual).containsExactly(carBooking);
    }

    @Test
    void getCarBooking() {
        //given
        User user =
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila");

        Car car =
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN, Brand.TESLA,true);
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        UUID bookingId = carBooking.getId();
        underTest.bookCar(carBooking);
        //when
        CarBooking actual = underTest.getCarBooking(bookingId);
        //then
        assertThat(actual).isEqualTo(carBooking);
    }

    @Test
    void bookCar() {
        //given
        User user =
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila");

        Car car =
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN, Brand.TESLA,true);
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        //when
        underTest.bookCar(carBooking);
        List<CarBooking> carBookings = underTest.getCarBookings();
        //then
        assertThat(carBookings).hasSize(1).containsExactly(carBooking);

    }

    @Test
    void deleteBooking() {
        //given
        User user =
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila");

        Car car =
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN, Brand.TESLA,true);
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        //when
        UUID bookingId = carBooking.getId();
        underTest.bookCar(carBooking);
        //when
        underTest.deleteBooking(bookingId);
        //then
        assertThat(underTest.getCarBookings()).isEmpty();
    }
    @Test
    void allUsersBookedCars() {
        //given
        User user =
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila");
        Car car =
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN, Brand.TESLA,true);
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        //when
        underTest.bookCar(carBooking);
        //when
        List<User> users = underTest.allUsersBookedCars();
        //then
        assertThat(users).isNotNull();
        assertThat(users).containsExactly(user);
    }
}