package user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
@Mock
private UserDAO userDAO;
@InjectMocks
    private UserService underTest;
    @Test
    void selectAllUsers() {
        //given
        List<User> users = List.of(
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila"));
        //when
        when(userDAO.selectAllUsers()).thenReturn(users);
        //when
        List<User> actual = underTest.selectAllUsers();
        //then
        verify(userDAO).selectAllUsers();
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(users);
    }

    @Test
    void getUserById() {
        List<User> users = List.of(
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila"));
        //when
        UUID userId = users.getFirst().getId();
        //given
        when(userDAO.getUserByID(userId)).thenReturn(users.getFirst());
        //when
        User actual = underTest.selectUserById(userId);
        //then
        verify(userDAO).getUserByID(userId);
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(users.getFirst());
    }
}