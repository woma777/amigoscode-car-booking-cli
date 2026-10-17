package user;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> selectAllUsers(){
        return userDAO.selectAllUsers();
    }
}
