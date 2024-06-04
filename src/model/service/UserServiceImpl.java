package model.service;

import mapper.Mapper;
import model.User;
import model.dao.UserDao;
import model.dao.UserDaoImpl;
import model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUser()
                .stream()
                .map(Mapper::fromUserToUserDto)
                .toList();
    }

    @Override
    public void addNewUser(UserDto userDto) {
        // Convert UserDto to User
        User newUser = Mapper.fromUserDtoToUser(userDto);

        // Check if the user already exists (based on email or other unique identifier)
        boolean userExists = userDao.getAllUser().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(newUser.getEmail()));

        if (userExists) {
            throw new IllegalArgumentException("User with the same email already exists.");
        }

        // Generate a new ID (assuming you don't have an auto-increment mechanism)
        Integer newId = userDao.getAllUser().size() + 1;
        newUser.setId(newId);

        // Add the new user
        userDao.addNewUser(newUser);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void updateUser(Integer id,UserDto userDto) {
        // Convert UserDto to User
        User updatedUser = Mapper.fromUserDtoToUser(userDto);
        updatedUser.setId(id);

        // Check if the user exists
        Optional<User> userOptional = userDao.getAllUser().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }

        // Update the user
        userDao.updateUser(updatedUser);
    }
}
