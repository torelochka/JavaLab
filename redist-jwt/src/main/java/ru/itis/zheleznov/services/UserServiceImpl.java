package ru.itis.zheleznov.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.UserDto;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final RedisUserService redisUserService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RedisUserService redisUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.redisUserService = redisUserService;
    }

    @Override
    public Optional<UserDto> userByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<UserDto> userByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.map(u -> modelMapper.map(u, UserDto.class));
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserDto> userById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public void banUser(Long id) {
        userRepository.findById(id).ifPresent(redisUserService::addAllTokensToBlackList);
    }
}
