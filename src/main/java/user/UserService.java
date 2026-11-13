package user;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public List<User> selectAllUsers(){
        return userDAO.selectAllUsers();
    }
    public User selectUserById(UUID id){
        return userDAO.getUserByID(id);
    }

}
