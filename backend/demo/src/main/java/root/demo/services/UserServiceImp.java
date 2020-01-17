package root.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.dtos.RegisterUserDto;
import root.demo.model.User;
import root.demo.repository.UserRepository;
import root.demo.utils.TokenUtils;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public RegisterUserDto userLogin(String username, String password) {

        User user = userRepository.findByUsername(username);
        if(user == null)
            return null;

        else if(user.getPassword().equals(password)) {
            String token = tokenUtils.generateToken(user.getUsername(),null);
            return new RegisterUserDto(user.getUsername(),token,user.getRoleEnum());
        }

        return null;
    }
}
