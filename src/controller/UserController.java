package controller;

import mapper.Mapper;
import model.dao.UserDao;
import model.dto.UserDto;
import model.service.UserService;
import model.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

public class UserController {
    private final UserService userService = new UserServiceImpl();
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }
    public void addNewUser(UserDto userDto){
        userService.addNewUser(userDto);
    }
    public void deleteUserById(Integer id){
        userService.deleteUserById(id);
    }
    public void updateUser(Integer id,UserDto userDto){
        userService.updateUser(id,userDto);
    }
}
