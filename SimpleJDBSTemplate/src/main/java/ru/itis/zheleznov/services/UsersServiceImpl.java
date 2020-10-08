package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByAge(int age) {
        return null;
    }

    @Override
    public List<User> getAllUsersByName(String name) {
        return usersRepository.findAllByName(name);
    }
}
