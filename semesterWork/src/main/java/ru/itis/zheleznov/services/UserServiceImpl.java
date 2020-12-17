package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.UserDto;
import ru.itis.zheleznov.models.SignIn;
import ru.itis.zheleznov.models.SignUp;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAll() {
        return UserDto.from(userRepository.getAll());
    }

    public boolean singIn(SignIn signIn) {
        if (userRepository.getUser(signIn.getEmail()).isPresent()) {
            User user = userRepository.getUser(signIn.getEmail()).get();
            return passwordEncoder.matches(signIn.getPassword(), user.getHashPassword());
        }
        return false;
        //TODO обрабодать верно
    }

    @Override
    public UserDto getUser(Long id) {
        return UserDto.from(userRepository.getById(id).orElse(null));
    }

    @Override
    public UserDto getUser(String email) {
        //TODO обработать
        return UserDto.from(userRepository.getUser(email).orElse(null));
    }


    public boolean signUp(SignUp model) {
        //TODO проверка на уже существуещего пользователя
        return userRepository.save(User.builder()
                .name(model.getName())
                .lastname(model.getLastname())
                .email(model.getEmail())
                .hashPassword(passwordEncoder.encode(model.getPassword()))
                .build()
        );
    }
}
