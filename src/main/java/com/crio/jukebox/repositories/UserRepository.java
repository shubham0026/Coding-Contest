package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{
    private final Map<String,User> userMap;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    @Override
    public User save(User entity) {
       
        userMap.put(entity.getId(),entity);
        return entity;
    }
    @Override
    public User getUserById(String userId){
        return userMap.get(userId);
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
}
