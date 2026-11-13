package booking;

import car.Brand;
import car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import user.User;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CarBookingFileDataAccessServiceTest {
    private CarBookingFileDataAccessService underTest;
    @TempDir
    Path tempDir;
    @BeforeEach
    void setUp() throws IOException {
        //String filePath = tempDir.resolve("bookings.dat").toString();
        Path filePath = tempDir.resolve("bookings.dat");
        Files.createFile(filePath);
        underTest = new CarBookingFileDataAccessService(filePath.toString());
     //  underTest = new CarBookingFileDataAccessService(filePath);
    }
    @Test
    void itShouldSaveAndRetrieveBooking() throws IOException {
        //given
        List<User> users = List.of(
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila"));

        List<Car> cars =  List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN, Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),
                users.getFirst(),
                cars.getFirst(),
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
        assertThat(carBookings).hasSize(1);
        assertThat(carBookings.getFirst().getId()).isEqualTo(carBooking.getId());
    }
    @Test
    void itShouldDeleteBooking() {
        //given
        List<User> users = List.of(
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila"));

        List<Car> cars =  List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN, Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        CarBooking carBooking = new CarBooking(
                UUID.randomUUID(),
                users.getFirst(),
                cars.getFirst(),
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        underTest.bookCar(carBooking);
        //when
        underTest.deleteBooking(carBooking.getId());
        //then
        assertThat(underTest.getCarBookings()).isEmpty();
    }
}