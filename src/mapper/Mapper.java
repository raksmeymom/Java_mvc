package mapper;

import model.User;
import model.dto.UserDto;

import java.util.Random;

public class Mapper {
    public static UserDto fromUserToUserDto(User user){
        if(user==null){
            return null;
        }
        return new UserDto(user.getName(), user.getEmail());
    }

    public static User fromUserDtoToUser(UserDto userDto) {

        if(userDto==null){
            return null;
        }
        return new User(null,userDto.name(), userDto.email());
    }
}
