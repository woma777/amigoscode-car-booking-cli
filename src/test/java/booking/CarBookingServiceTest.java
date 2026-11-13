package booking;
import car.Brand;
import car.Car;
import car.CarDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
    void registerCarBooking() {
        // Given
        User user = new User(
                UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                "James"
        );
        Car car = new Car(
                UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                "76hdgc",
                BigDecimal.TEN,
                Brand.TESLA,
                true
        );
        LocalDate startDate = LocalDate.now();
        UUID uuid = UUID.randomUUID();
        LocalDate endDate = LocalDate.of(2029, Month.MAY, 1);
        //when
        when(userDAO.getUserByID(user.getId())).thenReturn(user);
        when(carDAO.selectCarById(car.getId())).thenReturn(car);
        CarBooking actual = underTest.book(
                uuid,
                user.getId(),
                car.getId(),
                startDate,
                endDate
        );
        //then
        ArgumentCaptor<CarBooking> carBookingArgumentCaptor = ArgumentCaptor.forClass(CarBooking.class);
        verify(carBookingDAO).bookCar(carBookingArgumentCaptor.capture());
        CarBooking actualCarBooking = carBookingArgumentCaptor.getValue();
        assertThat(actualCarBooking).isNotNull();
        assertThat(actualCarBooking.getUsers()).isEqualTo(user);
        assertThat(actualCarBooking.getCars()).isEqualTo(car);
        assertThat(actualCarBooking.getStartDate()).isEqualTo(startDate);
        assertThat(actualCarBooking.getEndDate()).isEqualTo(endDate);
        assertThat(actualCarBooking.getBookingStatus()).isEqualTo(BookingStatus.ACTIVE);
        assertThat(actualCarBooking.getStartDate()).isEqualTo(startDate);
        assertThat(actualCarBooking).isEqualTo(actual);

    }

    @Test
    void getCarBookingById() throws IOException {
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
        UUID bookingId = carBooking.getId();
        //when
        when(carBookingDAO.getCarBooking(bookingId))
                .thenReturn(carBooking);
        CarBooking actual = underTest.getCarBooking(bookingId);
        //then
        verify(carBookingDAO).getCarBooking(bookingId);
        assertThat(actual).isEqualTo(carBooking);
    }

    @Test
    void testGetCarBookings() throws IOException {
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
        List<CarBooking> carBooking = List.of(new CarBooking(
                UUID.randomUUID(),
                users.getFirst(),
                cars.getFirst(),
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        ),new CarBooking(
                UUID.randomUUID(),
                users.getLast(),
                cars.getLast(),
                LocalDate.now(),
                endDate,
                BigDecimal.TEN,
                BookingStatus.ACTIVE,
                LocalDateTime.now()));
        //when
        when(carBookingDAO.getCarBookings()).thenReturn(carBooking);
        List<CarBooking> actual = underTest.getCarBooking();
        //then
        verify(carBookingDAO).getCarBookings();
        assertThat(actual).isEqualTo(carBooking);
    }

    @Test
    void deleteBooking() throws IOException {
        //given
        User user = new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila");
        Car car = new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false);
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
        //when
        underTest.deleteCarBooking(bookingId);
        //then
        verify(carBookingDAO).deleteBooking(bookingId);
        assertThat(underTest.getCarBooking()).isEmpty();
    }

    @Test
    void getAllUsersBookedCar() {
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
        List<CarBooking> carBooking = List.of(new CarBooking(
                UUID.randomUUID(),
                users.getFirst(),
                cars.getFirst(),
                LocalDate.now(),
                endDate,
                BigDecimal.TWO,
                BookingStatus.ACTIVE,
                LocalDateTime.now()
        ),new CarBooking(
                UUID.randomUUID(),
                users.getLast(),
                cars.getLast(),
                LocalDate.now(),
                endDate,
                BigDecimal.TEN,
                BookingStatus.ACTIVE,
                LocalDateTime.now()));
        //when
        when(carBookingDAO.allUsersBookedCars()).thenReturn(cars);
        List<Car> actual = underTest.allUsersBookedCar();
        //then
        verify(carBookingDAO).allUsersBookedCars();
        assertThat(actual).isEqualTo(cars);
    }
}