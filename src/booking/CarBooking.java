package booking;

import car.Car;
import user.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CarBooking implements Serializable {
    private UUID id;
    private List<User> users;
    private List<Car> cars;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private BookingStatus bookingStatus;
    private LocalDateTime bookedAt;

    public CarBooking(UUID id, List<User> users, List<Car> cars,
                      LocalDate startDate, LocalDate endDate,
                      BigDecimal price, BookingStatus bookingStatus,
                      LocalDateTime bookedAt) {
        this.id = id;
        this.users = users;
        this.cars = cars;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.bookingStatus = bookingStatus;
        this.bookedAt = bookedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    @Override
    public String toString() {
        return "CarBooking{" +
                "id=" + id +
                ", users=" + users +
                ", cars=" + cars +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", bookingStatus=" + bookingStatus +
                ", bookedAt=" + bookedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CarBooking that = (CarBooking) o;
        return Objects.equals(id, that.id) && Objects.equals(users, that.users) && Objects.equals(cars, that.cars) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(price, that.price) && bookingStatus == that.bookingStatus && Objects.equals(bookedAt, that.bookedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(users);
        result = 31 * result + Objects.hashCode(cars);
        result = 31 * result + Objects.hashCode(startDate);
        result = 31 * result + Objects.hashCode(endDate);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(bookingStatus);
        result = 31 * result + Objects.hashCode(bookedAt);
        return result;
    }
}
