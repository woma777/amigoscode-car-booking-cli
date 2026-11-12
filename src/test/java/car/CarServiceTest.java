package car;

import booking.CarBookingDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.UserDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarDAO carDAO;
    @Mock
    private CarBookingDAO carBookingDao;
    @InjectMocks
    private CarService underTest;
    @BeforeEach
    void setUp() {
        underTest = new CarService(carBookingDao,carDAO);
    }
    @Test
    void getAllCars() {
        //given
        List<Car> cars = List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
       when(carDAO.selectAllCars()).thenReturn(cars);
       //when
        List<Car> actual = underTest.getAllCars();
        //then
        verify(carDAO).selectAllCars();
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(cars);
    }
    @Test
    void getAllElectricCars() {
        //given
        List<Car> cars = List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        when(carDAO.selectAllElectricCars()).thenReturn(List.of(cars.getFirst()));
        //when
        List<Car> actual = underTest.getAllElectricCars();
        //then
        verify(carDAO).selectAllElectricCars();
        assertThat(actual).isNotNull();
        assertThat(actual).containsExactly(cars.getFirst());
    }
    @Test
    void getAllElectricCars_WhenNoElectricCars_ShouldReturnEmptyList() {
        // given
        List<Car> cars = List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        when(carDAO.selectAllElectricCars())
                .thenReturn(Collections.emptyList());
        // when
        List<Car> actual = underTest.getAllElectricCars();
        // then
        verify(carDAO).selectAllElectricCars();
        assertThat(actual).isEmpty();
    }
    @Test
    void getCarById() {
        //given
        List<Car> cars = List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        UUID id = cars.getFirst().getId();
        when(carDAO.selectCarById(id))
                .thenReturn(cars.getFirst());
        //when
        Car actual = underTest.getCarById(id);
        //then
        verify(carDAO).selectCarById(id);
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(cars.getFirst());
    }
}