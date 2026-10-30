package user;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFakerDataAccessService implements UserDAO{
    @Override
    public List<User> selectAllUsers() {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        for(int i=0 ; i<20 ;i++){
            users.add(new User(UUID.randomUUID(),faker.name().fullName()));
        }
        return users;
    }

    @Override
    public User getUserByID(UUID userId) {
        return null;
    }
}
