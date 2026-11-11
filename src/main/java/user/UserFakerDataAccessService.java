package user;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFakerDataAccessService implements UserDAO{
    List<User> users = new ArrayList<>();
    @Override
    public List<User> selectAllUsers() {
        Faker faker = new Faker();
        for(int i=0 ; i<5 ;i++){
            users.add(new User(UUID.randomUUID(),faker.name().fullName()));
        }
        return users;
    }
    @Override
    public User getUserByID(UUID userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
