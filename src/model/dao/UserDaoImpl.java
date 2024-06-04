package model.dao;

import controller.UserController;
import model.User;
import model.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private final List<User> userList = new ArrayList<>();

    public UserDaoImpl() {
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        userList.add(new User(1, "KoKo", "koko123@gmail.com"));
        userList.add(new User(2, "KaKa", "kaka123@gmail.com"));
    }

    @Override
    public void addNewUser(User user) {
        userList.add(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userList.removeIf(e -> e.getId().equals(id));
        System.out.println("User with ID " + id + " deleted.");
    }

    @Override
    public void updateUser(User user) {
        System.out.println("User with ID " + user.getId() + " updated: " + user);
        for (User u : userList) {
            if (u.getId().equals(user.getId())) {
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                break;
            }
        }
    }

    @Override
    public List<User> getAllUser() {

        return new ArrayList<>(userList); // Return a copy to prevent direct modification
    }

}
