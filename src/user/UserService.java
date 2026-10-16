package user;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public User[]  selectAllUsers(){
        return userDAO.selectAllUsers();
    }
}
