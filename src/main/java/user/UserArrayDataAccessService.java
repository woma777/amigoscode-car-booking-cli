package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserArrayDataAccessService  implements UserDAO {
//    private static final List<User>  userList;
//    static {
//        userList = new ArrayList<>(List.of(
//                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
//                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila")));
//    }
//    @Override
//    public List<User> selectAllUsers() {
//        return userList;
//    }
@Override
public List<User> selectAllUsers() {
    List<User> userList = new ArrayList<>();
    InputStream inputStream = getClass()
            .getClassLoader()
            .getResourceAsStream("users.csv");
    if (inputStream == null) {
        throw new IllegalStateException(
                "users.csv not found in classpath"
        );
    }
    try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                    inputStream,
                    StandardCharsets.UTF_8))) {
        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",", 2);

            UUID id = UUID.fromString(data[0]);
            String name = data[1];
            userList.add(new User(id, name));
        }
    } catch (IOException e) {
        throw new RuntimeException(
                "Error reading users.csv", e
        );
    }
    return userList;
}
    @Override
    public User getUserByID(UUID userId) {
        return selectAllUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst().orElse(null);
    }

}
