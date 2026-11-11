package car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarDAO {
    List<Car> selectAllCars();
    Car selectCarById(UUID carId);
    List<Car> selectAllElectricCars();
}
