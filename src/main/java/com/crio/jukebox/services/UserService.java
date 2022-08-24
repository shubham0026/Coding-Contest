package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;

public class UserService implements IUserService{
    private final IUserRepository userRepository;
    
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String name) {
        User newUser = userRepository.save(new User(name));
        return newUser;
    }
    
}
