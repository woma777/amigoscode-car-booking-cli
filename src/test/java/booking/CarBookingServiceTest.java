package booking;

import car.Brand;
import car.Car;
import car.CarDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.User;
import user.UserDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarBookingServiceTest {
    @Mock
    private CarDAO carDAO;
    @Mock
    private UserDAO userDAO;
    @Mock
    private CarBookingDAO carBookingDAO;
    @InjectMocks
    private CarBookingService underTest;
    @BeforeEach
    void setUp() {
        underTest= new CarBookingService(carBookingDAO, userDAO, carDAO);
    }
    @Test
    void registerCarBooking() throws IOException {
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
                users,
                cars,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        //when
        //when
        when(carBookingDAO.bookCar(carBooking)).thenReturn(carBooking);
       CarBooking actual = underTest.registerCarBooking(carBooking);
        verify(carBookingDAO).bookCar(carBooking);
        assertThat(actual).isEqualTo(carBooking);
        //then

    }

    @Test
    void getCarBooking() throws IOException {
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
                users,
                cars,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        UUID bookingId = carBooking.getId();
        when(carBookingDAO.getCarBooking(bookingId))
                .thenReturn(carBooking);
        CarBooking actual = underTest.getCarBooking(bookingId);
        verify(carBookingDAO).getCarBooking(bookingId);
        assertThat(actual).isEqualTo(carBooking);
    }

    @Test
    void testGetCarBooking() throws IOException {
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
                users,
                cars,
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        );
        //when
        when(carBookingDAO.getCarBookings()).thenReturn(List.of(carBooking));
        List<CarBooking> actual = underTest.getCarBooking();
        verify(carBookingDAO).getCarBookings();
        assertThat(actual).isEqualTo(List.of(carBooking));
        //then
    }
}