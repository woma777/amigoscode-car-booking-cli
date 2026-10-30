package user;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    List<User> selectAllUsers();
    User getUserByID(UUID userId);

}
