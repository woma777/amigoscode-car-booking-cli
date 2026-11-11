package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
class UserArrayDataAccessServiceTest {
    private UserArrayDataAccessService underTest;
    @BeforeEach
    void setUp() {
        underTest = new UserArrayDataAccessService();
    }
    @Test
    void selectAllUsers() {
        //given
        List<User> users = List.of(
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila"));
        //when
        List<User> actual =underTest.selectAllUsers();
        //then
        assertThat(actual).isEqualTo(users);
    }

    @Test
    void getUserByID() {
        //given
        List<User> users = List.of(
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila"));
        UUID id =users.getFirst().getId();
        //when
        User actual = underTest.getUserByID(id);
        //then
        assertThat(actual).isEqualTo(users.getFirst());
    }
}