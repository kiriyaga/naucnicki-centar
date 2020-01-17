package root.demo.services;

import root.demo.dtos.RegisterUserDto;
import root.demo.model.User;

public interface UserService {

    public RegisterUserDto userLogin(String username, String password);

}
