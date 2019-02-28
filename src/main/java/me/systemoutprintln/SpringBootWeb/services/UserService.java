package me.systemoutprintln.SpringBootWeb.services;

import me.systemoutprintln.SpringBootWeb.models.UserDto;
import me.systemoutprintln.SpringBootWeb.models.login.Role;
import me.systemoutprintln.SpringBootWeb.models.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + accountDto.getEmail());
        }
        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());

        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        user.setEmail(accountDto.getEmail());
        user.setRole(new Role(Integer.valueOf(1), user));
        return repository.save(user);
    }
}
