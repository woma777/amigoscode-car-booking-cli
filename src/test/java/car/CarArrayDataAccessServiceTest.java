package car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarArrayDataAccessServiceTest {
    private CarArrayDataAccessService underTest;
    @BeforeEach
    void setUp() {
        underTest = new CarArrayDataAccessService();
    }

    @Test
    void selectAllCars() {
        //given
        List<Car> cars = List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        //when
        List<Car> result = underTest.selectAllCars();
        //then
        assertThat(result).isEqualTo(cars);
    }

    @Test
    void selectCarById() {
        //given
        List<Car> cars = List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        //when
        UUID carId = cars.getFirst().getId();
        //when
        Car actual = underTest.selectCarById(carId);
        //then
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(cars.getFirst());

    }

    @Test
    void selectAllElectricCars() {
        //given
        List<Car> cars = List.of(
                new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),
                        "76hdgc", BigDecimal.TEN,Brand.TESLA,true),
                new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),
                        "123reg",BigDecimal.TEN,Brand.TESLA,false)
        );
        //when
        List<Car> result = underTest.selectAllElectricCars();
        assertThat(result).isNotNull();
        assertThat(result).containsExactly(cars.getFirst());

    }
}